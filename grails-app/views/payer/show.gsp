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
        <atlas-input
            value="${payer.id}"
            name="id"
            hidden
        >
        </atlas-input>
        <atlas-button slot="actions" description="Editar" data-panel-start-editing></atlas-button>
        <atlas-grid>
            <atlas-row>
                <atlas-col>
                    <atlas-input
                        label="Nome"
                        name="name"
                        required="true"
                        value="${payer.name}"
                    >
                    </atlas-input>
                </atlas-col>
            </atlas-row>
            <atlas-row>
                <atlas-col lg="6">
                    <atlas-masked-input
                        label="Email"
                        name="email"
                        mask-alias="email"
                        value="${payer.email}"
                        required="true"
                    >
                    </atlas-masked-input>
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-input
                        label="Data de criação"
                        name="dateCreated"
                        value="${formatTagLib.formatedDateCreated(date:payer.dateCreated)}"
                        required="true"
                        readonly
                    >
                    </atlas-input>
                </atlas-col>
            </atlas-row>
            <atlas-row>
                <atlas-col lg="6">
                    <atlas-input
                        label="Id do Cliente vinculado"
                        name="customerId"
                        required="true"
                        readonly
                        value="${payer.customer.id}"
                    >
                    </atlas-input>
                </atlas-col>
                <atlas-col lg="6">
                    <atlas-input
                        label="Nome do Cliente vinculado"
                        name="customerName"
                        required="true"
                        readonly
                        value="${payer.customer.name}"
                    >
                    </atlas-input>
                </atlas-col>
            </atlas-row>
        </atlas-grid>
    </atlas-form-panel>
    <asset:javascript src="payer/UpdatePayerController.js" />
</body>
</html>
