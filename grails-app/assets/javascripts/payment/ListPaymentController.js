//= require helpers/AtlasTableFilterController

function ListPaymentController(reference) {
    let atlasTableFilterController;
    const init = function() {
        atlasTableFilterController = new AtlasTableFilterController(reference, {
            initBindTable: true
        });
    }
    init();
}
document.addEventListener('AtlasContentLoaded', function() {
    const reference = document.querySelector('.js-payment-list-panel');
    new ListPaymentController(reference);
});