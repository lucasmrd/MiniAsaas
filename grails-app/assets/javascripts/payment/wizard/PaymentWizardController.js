//= require_tree ./steps/

function PaymentWizardController(reference) {

    const init = function() {
        initStepControllers();
    }

    const initStepControllers = function() {
        const paymentInfoStepReference = reference.querySelector('.js-payment-info-step');
        const paymentInfoStepController =
            new PaymentInfoStepController(paymentInfoStepReference, reference);

        const payerInfoStepReference = reference.querySelector('.js-payer-info-step');
        const payerInfoStepController =
            new PayerInfoStepController(payerInfoStepReference, reference);

        const summaryStepReference = reference.querySelector('.js-summary-step');
        const summaryStepController =
            new SummaryStepController(summaryStepReference, reference);

        reference.addStepController(paymentInfoStepReference.name, paymentInfoStepController);
        reference.addStepController(payerInfoStepReference.name, payerInfoStepController);
        reference.addStepController(summaryStepReference.name, summaryStepController);
    }

    init();
}

let paymentWizardController;

document.addEventListener('AtlasContentLoaded', function() {
    const reference = document.querySelector('#create-payment-wizard');

    paymentWizardController = new PaymentWizardController(reference);
});