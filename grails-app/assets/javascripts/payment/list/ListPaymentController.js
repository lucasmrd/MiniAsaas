//= require helpers/AtlasTableFilterController
//= require modal/ModalComponent

function ListPaymentController(reference) {

    let tableReference;
    let deleteModalReference;
    let restoreModalReference;
    let atlasTableFilterController;

    const init = function() {
        const alertElements = document.querySelectorAll('.js-flash-alert');

        atlasTableFilterController = new AtlasTableFilterController(reference, {
            initBindTable: true,

            customBuildFilterData: function() {
                const baseParams = tableReference.params || {};

                const searchInput = reference.querySelector('atlas-search-input');
                if (searchInput && searchInput.value) {
                    baseParams.term = searchInput.value;
                }

                return baseParams;
            }
        });

        alertElements.forEach(alertElement => {
            setTimeout(() => {
                alertElement.destroy();
            }, 3000);
        });

        let isReferencesLoaded = initReferences();
        if (!isReferencesLoaded) return;

        bindPayerListTable();
    };

    const initReferences = function() {
        tableReference = reference.querySelector('.js-payment-list-table');
        deleteModalReference = reference.querySelector('.js-delete-modal');
        restoreModalReference = reference.querySelector('.js-restore-modal');

        return deleteModalReference && restoreModalReference && tableReference;
    };

    const bindPayerListTable = function() {
        tableReference.addEventListener("atlas-table-action-click", function(e) {
            var button = e.detail.button;
            var buttonAction = button.description;
            var id = button.dataset.id;

            if (buttonAction === "confirmar") {
                window.location.href = `/payment/confirm?id=${id}`;
            }

            if (buttonAction === "editar") {
                window.location.href = `/payment/show?id=${id}`;
            }

            if (buttonAction === "exibir") {
                window.location.href = `/payment/show?id=${id}`;
            }

            if (buttonAction === "excluir") {
                let modalInstance = new ModalComponent({
                    reference: deleteModalReference,
                    confirmationCallback: function() {
                        window.location.href = `/payment/delete?id=${id}`;
                    },
                    cancelationCallback: function() {},
                    confirmButtonTheme: "danger",
                    confirmButtonLabel: "Excluir",
                    htmlContent: `Deseja realmente excluir a cobrança?`,
                    autoCloseOnConfirmation: true,
                });

                modalInstance.open();
            }

            if (buttonAction === "restaurar") {
                let modalInstance = new ModalComponent({
                    reference: restoreModalReference,
                    confirmationCallback: function() {
                        window.location.href = `/payment/restore?id=${id}`;
                    },
                    cancelationCallback: function() {},
                    confirmButtonTheme: "primary",
                    confirmButtonLabel: "Restaurar",
                    htmlContent: `Deseja restaurar essa cobrança?`,
                    autoCloseOnConfirmation: true,
                });

                modalInstance.open();
            }
        });
    };

    init();
}

document.addEventListener('AtlasContentLoaded', function() {
    const reference = document.querySelector('.js-payment-list-panel');

    new ListPaymentController(reference);
});