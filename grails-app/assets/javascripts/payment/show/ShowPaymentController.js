function ShowPaymentController() {

    const init = function() {
        const alertElements = document.querySelectorAll('.js-flash-alert');

        alertElements.forEach(alertElement => {
            setTimeout(() => {
                alertElement.destroy();
            }, 3000);
        });
    };

    init();
}


document.addEventListener('AtlasContentLoaded', function() {
    new ShowPaymentController();
});