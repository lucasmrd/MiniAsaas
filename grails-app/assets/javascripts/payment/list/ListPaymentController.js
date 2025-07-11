//= require helpers/AtlasTableFilterController
//= require modal/ModalComponent

function ListPaymentController(reference) {

    let tableReference;
    let modalReference;
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
        modalReference = reference.querySelector('.js-modal');

        return modalReference && tableReference;
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
                let modalInstance = new ModalComponent({
                    reference: modalReference,
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
                    reference: modalReference,
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