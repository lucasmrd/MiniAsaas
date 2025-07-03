function SummaryStepController(stepReference, wizardReference) {
    const payerSelect = wizardReference.querySelector('.js-payer-select');

    const BILLING_TYPE_LABELS = {
        pix: 'Pix',
        bank_slip: 'Boleto',
        credit_card: 'Cartão de Crédito'
    };

    const init = function() {}

    this.onShowStep = function() {
        populateSummary();
    };

    this.onSubmitStep = function(resolveSubmit) {
        Atlas.loading.showLoading("Criando cobrança");

        const wizardState = wizardReference.getFullState();

        const finalData = {
            ...wizardState.paymentInfo,
            ...wizardState.payerInfo
        };

        Atlas.request.post(wizardReference.dataset.createUrl, finalData)
            .then(function(response) {
                if (response.status === 'SUCCESS') {
                    alert(response.message || "Cobrança criada com sucesso!");
                    window.location.href = response.redirectUrl;
                    resolveSubmit(true);
                    return
                }
            })
            .catch(function(error) {
                console.error("Criação de cobrança falhou, backend retornou erro:", error);
                const responseData = error.response || {};
                const errorMessages = Object
                    .values(responseData.errors || {'geral': 'Erro desconhecido'}).join(', ');

                alert("Por favor, corrija os erros: " + errorMessages);

                resolveSubmit(false);
            })
            .finally(function() {
                Atlas.loading.closeLoading();
            });
    }

    const populateSummary = function() {
        const wizardState = wizardReference.getFullState();

        const data = {
            ...(wizardState.paymentInfo || {}),
            ...(wizardState.payerInfo || {})
        };

        let finalPayerName = 'Não selecionado';

        if (data.payerMode === 'new') {
            finalPayerName = data.name;
        } else if (data.payerMode === 'created' && payerSelect && data.payerId) {
            const selectedOption = payerSelect.querySelector(`atlas-option[value="${data.payerId}"]`);
            finalPayerName = selectedOption ? selectedOption.label : 'Não informado';
        }

        const displayData = {
            value: data.value || 'N/A',
            dueDate: data.dueDate || 'N/A',
            billingType: BILLING_TYPE_LABELS[data.billingType] || 'Não selecionado',
            payerName: finalPayerName || 'Não selecionado'
        };

        console.log(displayData);

        const valueItem = stepReference.querySelector('[data-state="value"]');
        if (valueItem) valueItem.description = displayData.value;

        const dueDateItem = stepReference.querySelector('[data-state="dueDate"]');
        if (dueDateItem) dueDateItem.description = displayData.dueDate;

        const billingTypeItem = stepReference.querySelector('[data-state="billingType"]');
        if (billingTypeItem) billingTypeItem.description = displayData.billingType;

        const payerNameItem = stepReference.querySelector('[data-state="payerName"]');
        if (payerNameItem) payerNameItem.description = displayData.payerName;
    };
}

document.addEventListener('AtlasContentLoaded', function() {
    const reference = document.querySelector('#create-payment-wizard');

    paymentWizardController = new PaymentWizardController(reference);
});

