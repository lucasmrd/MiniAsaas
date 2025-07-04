//= require helpers/AtlasTableFilterController

function ListPayerController(reference) {

    let atlasTableFilterController;

    const init = function() {
        atlasTableFilterController = new AtlasTableFilterController(reference, {
            initBindTable: true
        });
    }

    init();
}

document.addEventListener('AtlasContentLoaded', function() {
    const reference = document.querySelector('.js-payer-list-panel');

    new ListPayerController(reference);
});
