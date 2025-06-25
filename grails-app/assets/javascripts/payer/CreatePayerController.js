function CreatePayerController(reference) {
    const postalCodeInput = reference.querySelector('.js-postal-code-input');
    const addressInput = reference.querySelector('.js-address-input');
    const addressComplementInput = reference.querySelector('.js-address-complement-input');
    const cityInput = reference.querySelector('.js-city-input');
    const stateInput = reference.querySelector('.js-state-select');
    const phoneInput = reference.querySelector('.js-phone-input');
    const cellphoneInput = reference.querySelector('.js-cellphone-input');

    const init = function() {
        bindPostalCodeInput();
        isAnyPhoneFilled();
    };

    const bindPostalCodeInput = function() {
        postalCodeInput.addEventListener("atlas-postal-code-change", function() {
            const postalCodeValue = postalCodeInput.getUnmaskedValue();
            const url = `https://viacep.com.br/ws/${postalCodeValue}/json/`;
            Atlas.request.get(url)
                .then(function(response) {
                    addressInput.value = response.logradouro;
                    addressComplementInput.value = response.bairro;
                    cityInput.value = response.localidade;
                    stateInput.value = response.uf;
                })
        });
    };

    const isAnyPhoneFilled = function() {
        const validator = {
            name: "is-required-phone-or-mobile-phone-validator",
            status: "error",
            reportOnChange: false,
            getInvalidMessage: function() {
                return "É necessário ter pelo menos um telefone";
            },
            validate: function() {
                return !!cellphoneInput.getElementValue() || !!phoneInput.getElementValue();
            }
        };

        cellphoneInput.addValidator(validator);
        phoneInput.addValidator(validator);
        cellphoneInput.addEventListener("atlas-input-blur", function() {
            cellphoneInput.reportValidity();
        });
        phoneInput.addEventListener("atlas-input-blur", function() {
            phoneInput.reportValidity();
        });
    };

    init();
}

document.addEventListener('AtlasContentLoaded', function() {
    const reference = document.querySelector('.js-create-payer-form');

    new CreatePayerController(reference);
});