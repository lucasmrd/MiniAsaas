//= require helpers/ObjectHelpers

/**
 *
 * @constructor AtlasTableFilterController
 * @param {HTMLElement} reference - Referência do elemento que contém a tabela e os filtros
 * @param {Object} config - Objeto de configuração para o controller
 * @param {Boolean} config.initBindTable - Se verdadeiro, a busca e atualização automática dos registros da tabela serão iniciadas assim que o controlador for instanciado.
 * @param {Boolean} config.preventUpdateUrlParamsWithFilters - Se verdadeiro, os parâmetros da URL não serão atualizados com os filtros aplicados.
 *
 * @example
 * var reference = document.querySelector(".js-table-container");
 *
 * new AtlasTableFilterController(reference, { initBindTable: true });
 *
 */

function AtlasTableFilterController(reference, config) {
    var parsedConfig = config || {};
    var initBindTable = parsedConfig.initBindTable;
    var preventUpdateUrlParamsWithFilters = parsedConfig.preventUpdateUrlParamsWithFilters || false;

    /**
     * @type {HTMLElement | null}
     * @description Referência do elemento da tabela
     * @default null
     */
    this.tableReference;

    /**
     * @type {HTMLElement | null}
     * @description Referência do elemento do filtro
     * @default null
     */
    this.searchInputReference;

    /**
     * @type {Function | null}
     * @description Callback executado antes de aplicar os filtros.
     * @default null
     */
    this.beforeApplyFilter = null;

    /**
     * @type {Function | null}
     * @description Callback executado antes de limpar os filtros.
     * @default null
     */
    this.beforeCleanFilter = null;

    /**
     * @type {Function | null}
     * @description Callback executado antes da busca na tabela.
     * @default null
     */
    this.beforeTableSearch = null;

    /**
     * @type {Function | null}
     * @description Callback executado após a busca na tabela.
     * @default null
     */
    this.afterTableSearch = null;

    /**
     * @type {Function | null}
     * @description Callback para personalizar os parâmetros da URL.
     * @default null
     */
    this.customizeParamsForUrl = null;

    /**
     * @type {Function | null}
     * @description Callback para criar dados do filtro.
     * @default null
     */
    this.customBuildFilterData = null;

    var _this = this;

    var lastInputSearch = "";

    var isFirstInputSearchDone = false;

    this.init = function() {
        if (!reference) return;

        getProperties();
        getLastInputSearch();
        bindSearchInput();
        getTableParams();
        bindFilter();
        bindPaginationScroll();

        if (initBindTable) {
            bindTableSearch();
        }
    };

    var bindFilter = function() {
        if (!_this.filterReference) return;

        _this.filterReference.addEventListener("atlas-apply-filter", function() {
            if (typeof _this.beforeApplyFilter === "function") {
                _this.beforeApplyFilter();
            }

            _this.tableReference.fetchRecords(true);
        });

        _this.filterReference.addEventListener("atlas-clean-filter", function() {
            if (typeof _this.beforeCleanFilter === "function") {
                _this.beforeCleanFilter();
            }

            _this.tableReference.fetchRecords(true);
        });
    };

    var bindTableSearch = function() {
        if (!_this.tableReference) return;

        _this.tableReference.addEventListener("atlas-table-before-search", function() {
            if (_this.filterReference) {
                _this.filterReference.disableButtons();
            }

            if (_this.searchInputReference) {
                _this.searchInputReference.readonly = true;
            }

            if (typeof _this.beforeTableSearch === "function") {
                _this.beforeTableSearch();
            }

            var tableParams;

            if (typeof _this.customBuildFilterData === "function") {
                tableParams = _this.customBuildFilterData();
            } else {
                tableParams = buildFilterData();
            }

            _this.tableReference.params = tableParams;
        });

        _this.tableReference.addEventListener("atlas-table-after-search", function(event) {
            if (_this.filterReference) {
                _this.filterReference.enableButtons();
            }

            if (_this.searchInputReference) {
                _this.searchInputReference.readonly = false;
            }

            if (typeof _this.afterTableSearch === "function") {
                _this.afterTableSearch(event);
            }

            if (event.detail.response.footerText) {
                updateFooterText(event.detail.response.footerText);
            }

            if (!preventUpdateUrlParamsWithFilters) {
                updateUrlParamsWithFilters();
            }
        });
    };

    var getProperties = function() {
        _this.tableReference = reference.querySelector("atlas-easy-table");
        _this.searchInputReference = reference.querySelector("atlas-search-input");
        _this.filterReference = reference.querySelector("atlas-filter");
    };

    var getLastInputSearch = function() {
        if (_this.searchInputReference && _this.searchInputReference.getElementValue()) {
            lastInputSearch = _this.searchInputReference.getElementValue();
        }
    };

    var bindSearchInput = function() {
        if (!_this.searchInputReference) return;

        _this.searchInputReference.addEventListener("atlas-input-trigger-search", function() {
            if (isFirstInputSearchDone && lastInputSearch === _this.searchInputReference.getElementValue()) return;

            _this.tableReference.fetchRecords(true);
            lastInputSearch = _this.searchInputReference.getElementValue();
            isFirstInputSearchDone = true;
        });
    };

    var getTableParams = function() {
        _this.tableReference.params = buildFilterData();
    };

    var buildFilterData = function() {
        var params = {};

        if (_this.filterReference) params = _this.filterReference.getFilterData();

        if (_this.searchInputReference) {
            var searchInputName = _this.searchInputReference.name;
            params[searchInputName] = _this.searchInputReference.getElementValue();
        }

        return objectHelpers.removeEmptyProperties(params);
    };

    var updateUrlParamsWithFilters = function() {
        var params = _this.tableReference.getFetchParams();

        if (params.page === 1) delete params.page;

        if (typeof _this.customizeParamsForUrl === "function") {
            params = _this.customizeParamsForUrl(params);
        }

        var filteredParams = objectHelpers.removeEmptyProperties(params);

        Atlas.browser.replaceUrlParamsWithoutReload(filteredParams);
    };

    var updateFooterText = function(footerText) {
        _this.tableReference.footerText = footerText;
    };

    var bindPaginationScroll = function() {
        if (!_this.tableReference) return;

        _this.tableReference.addEventListener("atlas-table-page-change", function() {
            setTimeout(function() {
                browserUtils.scrollPageToElement(_this.tableReference.parentElement, null, "top");
            }, 10);
        });
    };

    this.init();
}
