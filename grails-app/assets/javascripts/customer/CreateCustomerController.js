function CreateCustomerController(reference) {
    const postalCodeInput = reference.querySelector('.js-postal-code-input');
    const addressInput = reference.querySelector('.js-address-input');
    const addressComplementInput = reference.querySelector('.js-address-complement-input');
    const cityInput = reference.querySelector('.js-city-input');
    const stateInput = reference.querySelector('.js-state-select');

    const init = function() {
        bindPostalCodeInput();
    };

    const bindPostalCodeInput = function() {
        postalCodeInput.addEventListener("atlas-postal-code-change", function() {
            const postalCodeValue = postalCodeInput.getUnmaskedValue();
            const url = `https://viacep.com.br/ws/${postalCodeValue}/json/`;
            Atlas.request.get(url)
                .then(function(response) {
                    console.log(response);                    
                    addressInput.value = response.logradouro;
                    addressComplementInput.value = response.bairro;
                    cityInput.value = response.localidade;
                    stateInput.value = response.uf;
                })
        });
    };

    init();
}

document.addEventListener('AtlasContentLoaded', function() {
    const reference = document.querySelector('.js-create-customer-form');

    new CreateCustomerController(reference);
});
