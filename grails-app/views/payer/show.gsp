<!DOCTYPE html>
<html>
<head>
    <title>Detalhes do Pagador</title>
    <meta name="layout" content="internal"/>
</head>
<body>
    <atlas-form-panel
        class="js-list-payer-form"
        editing
        action="${createLink(controller: 'payer', action: 'update')}"
        method="post"
        header="Detalhes do Pagador - ${payer.name}"
    >
        <atlas-button
            description="Editar"
            icon="pencil"
            data-panel-start-editing
            slot="actions"
        ></atlas-button>
        <g:render template="/payer/templates/index/infoSection" model="${[payer: payer]}"/>
        <g:render template="/payer/templates/index/contactSection" />
        <g:render template="/payer/templates/index/addressSection" />
    </atlas-form-panel>
    <asset:javascript src="payer/UpdatePayerController.js" />
</body>
</html>
