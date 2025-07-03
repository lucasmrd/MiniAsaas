function PayerInfoStepController(stepReference, wizardReference) {
    const payerToggle = wizardReference.querySelector('.js-payer-toggle');
    const existingPayerSection = wizardReference.querySelector('.js-existing-payer-section');
    const newPayerSection = wizardReference.querySelector('.js-new-payer-section');
    const payerSelect = wizardReference.querySelector('.js-payer-select');

    const payerNameInput = wizardReference.querySelector('.js-payer-name-field');
    const payerEmailInput = wizardReference.querySelector('.js-payer-email-field');
    const payerCpfCnpjInput = wizardReference.querySelector('.js-cpf-cnpj-field');
    const postalCodeInput = wizardReference.querySelector('.js-postal-code-input');
    const addressInput = wizardReference.querySelector('.js-address-input');
    const addressNumberInput = wizardReference.querySelector('.js-address-number-input');
    const addressComplementInput = wizardReference.querySelector('.js-address-complement-input');
    const cityInput = wizardReference.querySelector('.js-city-input');
    const stateInput = wizardReference.querySelector('.js-state-select');
    const phoneInput = wizardReference.querySelector('.js-phone-input');
    const cellphoneInput = wizardReference.querySelector('.js-cellphone-input');

    const requiredNewPayerFields = [
        payerNameInput,
        payerEmailInput,
        payerCpfCnpjInput,
        postalCodeInput,
        addressInput,
        addressNumberInput,
        addressComplementInput,
        cityInput,
        stateInput
    ];

    this.init = function() {
        if (payerToggle) {
            setupPhoneValidation();
            bindPostalCodeInput();

            payerToggle.addEventListener('atlas-toggle-item-change', handlePayerToggleChange);
            handlePayerToggleChange();
        }
    }

    this.validate = function(resolveValidate) {
        Atlas.loading.showLoading("Validando dados");

        const stepData = Atlas.form.getFormValues(stepReference)

        Atlas.request.post(wizardReference.dataset.payerValidateUrl, stepData)
            .then(function(response) {
                if (response.status === 'SUCCESS') {
                    resolveValidate(true);
                    return
                }
            })
            .catch(function(error) {
                console.error("Validação falhou, backend retornou erro:", error);
                const responseData = error.response || {};
                const errorMessages = Object
                    .values(responseData.errors || {'geral': 'Erro desconhecido'}).join(', ');

                alert("Por favor, corrija os erros: " + errorMessages);

                resolveValidate(false);
            })
            .finally(function() {
                Atlas.loading.closeLoading();
            });
    }

    const setupPhoneValidation = function() {
        if (!phoneInput || !cellphoneInput) return;

        const phoneValidator = {
            name: "is-required-phone-or-mobile-phone-validator",
            status: "error",
            getInvalidMessage: () => "É necessário informar pelo menos um telefone",
            validate: () => {
                if (payerToggle.value !== 'new') {
                    return true;
                }
                return !!(cellphoneInput?.value) || !!(phoneInput?.value);
            }
        };

        phoneInput.addValidator(phoneValidator);
        cellphoneInput.addValidator(phoneValidator);

        const revalidatePhonesOnBlur = () => {
            if (payerToggle.value === 'new') {
                phoneInput.reportValidity();
                cellphoneInput.reportValidity();
            }
        };
        phoneInput.addEventListener("atlas-input-blur", revalidatePhonesOnBlur);
        cellphoneInput.addEventListener("atlas-input-blur", revalidatePhonesOnBlur);
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

    const handlePayerToggleChange = function() {
        const selectedValue = payerToggle.value;
        const isNewPayerSelected = (selectedValue === 'new');

        existingPayerSection.hidden = isNewPayerSelected;
        newPayerSection.hidden = !isNewPayerSelected;

        requiredNewPayerFields.forEach(field => {
            if (field) {
                field.toggleAttribute('required', isNewPayerSelected);
            }
        });
        payerSelect.toggleAttribute('required', !isNewPayerSelected);

        if (isNewPayerSelected) {
            if (payerSelect) payerSelect.value = '';
        } else {
            requiredNewPayerFields.forEach(field => {
                if (field) {
                    field.value = '';
                }
            });

            if (phoneInput) phoneInput.value = '';
            if (cellphoneInput) cellphoneInput.value = '';
        }

        if (!isNewPayerSelected && phoneInput && cellphoneInput) {
            phoneInput.error = null;
            cellphoneInput.error = null;
        }
    }
}