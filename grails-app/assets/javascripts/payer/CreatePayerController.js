function CreatePayerController(reference) {
    const postalCodeInput = reference.querySelector('.js-postal-code-input');
    const addressInput = reference.querySelector('.js-address-input');
    const addressComplementInput = reference.querySelector('.js-address-complement-input');
    const cityInput = reference.querySelector('.js-city-input');
    const stateInput = reference.querySelector('.js-state-select');
    const personTypeSelect = reference.querySelector('.js-person-type-select');

    const init = function() {
        bindPostalCodeInput();
        bindPayerTypeSelect();
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

    const bindPayerTypeSelect = function() {

        personTypeSelect.addEventListener("atlas-select-change", function() {
            const selectedValue = personTypeSelect.value;
            const naturalPersonTypeFields = reference.querySelectorAll('.js-natural-person-type-fields');
            const legalPersonTypeFields = reference.querySelectorAll('.js-legal-person-type-fields');
            const isNaturalPerson = selectedValue == 'NATURAL';
            const cpfCnpj = reference.querySelector('.js-cpf-cnpj-field');

            naturalPersonTypeFields.forEach((field) => {
                field.toggleAttribute('required', isNaturalPerson);
                if (isNaturalPerson) {
                    field.showElement();
                } else {
                    field.hideElement();
                }
            });

            legalPersonTypeFields.forEach((field) => {
                field.toggleAttribute('required', !isNaturalPerson);
                if (isNaturalPerson) {
                    field.hideElement();
                } else {
                    field.showElement();
                }
            });

            cpfCnpj.setAttribute('label', isNaturalPerson ? "CPF" : "CNPJ");
            cpfCnpj.setAttribute('mask-alias', isNaturalPerson ? "cpf" : "cnpj");

        });
    };

    init();
}

document.addEventListener('AtlasContentLoaded', function() {
    const reference = document.querySelector('.js-create-payer-form');

    new CreatePayerController(reference);
});