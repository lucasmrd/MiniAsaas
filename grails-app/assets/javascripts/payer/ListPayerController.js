//= require helpers/AtlasTableFilterController

function ListPayerController(reference) {

    let tableReference;
    let atlasTableFilterController;

    const init = function() {
        atlasTableFilterController = new AtlasTableFilterController(reference, {
            initBindTable: true
        });

        let isReferencesLoaded = initReferences();
        if (!isReferencesLoaded) return;
        bindPayerListTable();
        updatePayerListTable();
    };

    const initReferences = function() {
        tableReference = reference.querySelector('.js-payer-list-panel');
        return tableReference;
    };

    const bindPayerListTable = function() {
        tableReference.addEventListener("atlas-table-action-click", function(event) {
            var button = event.detail.button;
            var buttonAction = button.description;
            var id = button.dataset.id;

            if (buttonAction === "delete") {
                window.location.href = `/payer/delete?id=${id}`;
                alert('Cliente excluído com sucesso');
                window.location.reload();
            }
        });
    };

    const updatePayerListTable = function() {
        if (!tableReference) return;

        tableReference.addEventListener("atlas-table-action-click", function(event) {
            var button = event.detail.button;
            var buttonAction = button.description;
            var id = button.dataset.id;

            if (buttonAction === "update") {
                window.location.href = `/payer/show?id=${id}`;
            }
        });
    };

    init();
}

document.addEventListener('AtlasContentLoaded', function() {
    const reference = document.querySelector('.js-payer-list-panel');

    new ListPayerController(reference);
});
