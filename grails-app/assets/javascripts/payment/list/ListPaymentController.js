//= require helpers/AtlasTableFilterController

function ListPaymentController(reference) {

    let tableReference;
    let atlasTableFilterController;

    const init = function() {
        const alertElements = document.querySelectorAll('.js-flash-alert');

        atlasTableFilterController = new AtlasTableFilterController(reference, {
            initBindTable: true
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

        return tableReference;
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

            if (buttonAction === "excluir") {
                window.location.href = `/payment/delete?id=${id}`;
            }
        });
    };

    init();
}

document.addEventListener('AtlasContentLoaded', function() {
    const reference = document.querySelector('.js-payment-list-panel');

    new ListPaymentController(reference);
});