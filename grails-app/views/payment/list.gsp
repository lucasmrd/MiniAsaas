<!DOCTYPE html>
<html>
<head>
    <title>Lista de cobranças</title>
    <meta name="layout" content="internal"/>
</head>
<body>
<atlas-panel class="js-payment-list-panel">
    <g:if test="${paymentList}">
        <atlas-toolbar>
            <atlas-search-input
                slot="search"
                name="name"
                placeholder="Procurar por nome ou email do cliente"
            ></atlas-search-input>
            <atlas-button
                icon="plus"
                description="Adicionar cobrança"
                href="${createLink(controller: "payment", action: "create")}"
                slot="actions"
            ></atlas-button>
        </atlas-toolbar>
        <atlas-easy-table
            class="js-payment-list-table"
            url="${createLink(controller:"payment", action:"loadTableContent")}"
            has-actions
            total-records="${paymentList.totalCount}"
            items-per-page="10"
            columns='[{"name":"name","label":"Nome","size":"md","ellipsis":true,"sortable":true},
            {"name":"value","label":"Valor","size":"md","ellipsis":true,"sortable":true},
            {"name":"description","label":"Descrição","size":"md","ellipsis":true,"sortable":true},
            {"name":"billingType","label":"Forma de pagamento","size":"md","ellipsis":true,"sortable":true},
            {"name":"dueDate","label":"Data de vencimento","size":"lg","ellipsis":true,"sortable":true}]'
        >
            <atlas-empty-state
                slot="empty-state-template"
                illustration="telescope-files-stars"
                illustration-alt="Ilustração de um avião de papel"
                header="Nenhum resultado encontrado"
                description="Tente ajustar os filtros ou realizar uma nova busca."
            >
            </atlas-empty-state>
            <g:render template="/payment/templates/list/tableContent" model="[paymentList: paymentList]" />
        </atlas-easy-table>
    </g:if>
    <g:else>
        <atlas-empty-state
            illustration="schedule-user-avatar"
            header="Sem cobranças cadastradas"
        >
            Aqui você pode cadastrar as cobranças que deseja realizar.
            <atlas-button
                icon="plus"
                description="Adicionar cobrança"
                href="${createLink(controller: "payment", action: "create")}"
                slot="button"
            ></atlas-button>
        </atlas-empty-state>
    </g:else>
</atlas-panel>
<asset:javascript src="payment/list/ListPaymentController.js"/>
</body>
