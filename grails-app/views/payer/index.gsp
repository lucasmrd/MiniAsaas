<!DOCTYPE html>
<html>
<head>
    <title>Mini Asaas</title>
    <meta name="layout" content="external"/>
</head>
<body>
    <atlas-form-panel
        class="js-create-payer-form"
        editing
        action="${createLink(controller: 'payer', action: 'save')}"
        method="post"
        header="Criar Conta"
    >
        <g:render template="/payer/templates/index/infoSection" />
        <g:render template="/payer/templates/index/contactSection" />
        <g:render template="/payer/templates/index/addressSection" />
    </atlas-form-panel>
    <asset:javascript src="payer/CreatePayerController.js" />
</body>
</html>