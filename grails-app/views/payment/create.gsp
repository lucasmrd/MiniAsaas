<!DOCTYPE html>
<html>
<head>
    <title>Criar cobrança</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:link rel="icon" href="asaas.png" type="image/x-ico"/>
    <g:render template="/layouts/templates/atlas" />
</head>
<body>
<atlas-wizard
    header="Criar Cobrança"
    id="create-payment-wizard"
    data-validate-url="${g.createLink(controller: 'payment', action: 'validate')}"
    data-payer-validate-url="${g.createLink(controller: 'payer', action: 'validate')}"
    data-create-url="${g.createLink(controller: 'payment', action: 'save')}"
>
    <atlas-wizard-intro-step header="Recepção" centralized-content hide-header="true">
        <g:render template="/payment/templates/create/introStepSection"/>
    </atlas-wizard-intro-step>
    <atlas-wizard-step header="Dados da cobrança" name="paymentInfo" class="js-payment-info-step">
        <g:render template="/payment/templates/create/paymentInfoSection"/>
        <g:render template="/payment/templates/create/billingTypeSection"/>
    </atlas-wizard-step>

    <atlas-wizard-step header="Dados do cliente" name="payerInfo" class="js-payer-info-step">
        <atlas-layout gap="9">
            <g:render template="/payment/templates/create/existingPayerSection"/>
            <div class="js-new-payer-section" hidden>
                <g:render template="/payment/templates/create/newPayerSection"/>
            </div>
        </atlas-layout>
    </atlas-wizard-step>

    <atlas-wizard-step header="Resumo" name="summary" class="js-summary-step">
        <div id="payment-summary-content">
            <g:render template="/payment/templates/create/summarySection"/>
        </div>
    </atlas-wizard-step>
</atlas-wizard>
<asset:javascript src="payment/wizard/PaymentWizardController.js" />
</body>
</html>