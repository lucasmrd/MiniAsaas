<!DOCTYPE html>
<html>
<head>    
    <title>Mini Asaas</title>
    <meta name="layout" content="external"/>
</head>
<body>
    <atlas-form-panel
        class="js-create-customer-form" 
        editing 
        action="${createLink(controller: 'customer', action: 'save')}" 
        method="post"
        header="Criar Conta"
    >
        <g:render template="/customer/templates/index/infoSection" />
        <g:render template="/customer/templates/index/contactSection" />
        <g:render template="/customer/templates/index/addressSection" />
    </atlas-form-panel>
    <asset:javascript src="customer/CreateCustomerController.js" />
</body>
</html>