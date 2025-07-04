<!DOCTYPE html>
<html>
<head>
    <title>Mini Asaas</title>
    <meta name="layout" content="internal"/>
</head>
<body page-title="Cadastrar pagador">
    <atlas-panel class="js-payer-list-panel">
        <g:if test="${ payerList }">
            <atlas-toolbar>
                <atlas-search-input
                    slot="search"
                    name="name"
                    placeholder="Procure por nome ou CPF"
                ></atlas-search-input>
                <atlas-button
                    icon="plus"
                    description="Adicionar pagador"
                    href="${createLink(controller: "payer", action: "index")}"
                    slot="actions"
                ></atlas-button>
            </atlas-toolbar>
            <atlas-easy-table
                url="${createLink(controller:"payer", action:"loadTableContent")}"
                total-records="${ payerList.totalCount }"
                items-per-page="10"
                columns='[{"name":"name","label":"Nome","size":"lg","ellipsis":true,"sortable":true},{"name":"cpfCnpj","label":"CPF/CNPJ","size":"lg","ellipsis":true,"sortable":true},{"name":"email","label":"E-mail","size":"lg","ellipsis":true,"sortable":true}]'
            >
                <atlas-empty-state
                    slot="empty-state-template"
                    illustration="telescope-files-stars"
                    illustration-alt="Ilustração de um avião de papel"
                    header="Nenhum resultado encontrado"
                    description="Tente ajustar os filtros ou realizar uma nova busca."
                >
                </atlas-empty-state>
                <g:render template="/payer/templates/list/tableContent" model="[payerList: payerList]" />
            </atlas-easy-table>
        </g:if>
        <g:else>
            <atlas-empty-state
                illustration="schedule-user-avatar"
                header="Sem pagadores cadastrados"
            >
                Aqui você pode cadastrar os pagadores que deseja utilizar em suas transações.
                <atlas-button
                    icon="plus"
                    description="Adicionar pagador"
                    href="${createLink(controller: "payer", action: "index")}"
                    slot="button"
                ></atlas-button>
            </atlas-empty-state>
        </g:else>
    </atlas-panel>
    <asset:javascript src="payer/ListPayerController.js" />
</body>
</html>
