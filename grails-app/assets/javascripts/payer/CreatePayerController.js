function CreatePayerController(reference) {

}

document.addEventListener('AtlasContentLoaded', function() {
    const reference = document.querySelector('.js-create-payer-form');

    new CreatePayerController(reference);
});