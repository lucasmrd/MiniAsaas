function PaymentInfoStepController(stepReference, wizardReference) {

    this.init = function() {}

    this.validate = function(resolveValidate) {
        Atlas.loading.showLoading("Validando dados");

        const stepData = Atlas.form.getFormValues(stepReference)

        Atlas.request.post(wizardReference.dataset.validateUrl, stepData)
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
}