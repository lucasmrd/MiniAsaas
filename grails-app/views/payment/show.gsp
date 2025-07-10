<%@ page import="com.asaas.mini.enums.PaymentStatus" %>
<%@ page import="com.asaas.mini.enums.BillingType" %>

<!DOCTYPE html>
<html>
<head>
    <title>Detalhes da cobrança</title>
    <meta name="layout" content="internal"/>
</head>
<body>
<atlas-form-panel
    class="js-list-payment-form"
    action="${createLink(controller: 'payment', action: 'update')}"
    method="post"
    header="Detalhes da cobrança"
>
    <atlas-button
        description="Editar"
        icon="pencil"
        data-panel-start-editing
        slot="actions"
    ></atlas-button>

    <atlas-labeled-content label="Situação">
        <g:set var="statusTheme" value="primary"/>

        <g:if test="${payment.status == PaymentStatus.PENDING}"><g:set var="statusTheme" value="warning"/></g:if>

        <g:if test="${payment.status == PaymentStatus.OVERDUE || payment.status == PaymentStatus.REFUNDED}">
            <g:set var="statusTheme" value="danger"/>
        </g:if>

        <g:if test="${payment.status == PaymentStatus.CONFIRMED || payment.status == PaymentStatus.RECEIVED}">
            <g:set var="statusTheme" value="success"/>
        </g:if>

        <atlas-text size="lg" theme="${statusTheme}">
            ${payment.status.label}
        </atlas-text>
    </atlas-labeled-content>
    <g:render template="/payment/templates/create/paymentInfoSection" model="${[payment: payment]}"/>
    <atlas-grid>
        <atlas-row>
            <atlas-col lg="6">
                <atlas-select
                    label="Forma de pagamento"
                    placeholder="Selecione uma opção"
                    name="billingType"
                    required
                    value="${payment?.billingType?.name()}"
                >
                    <g:each var="bt" in="${BillingType.values()}">
                        <atlas-option
                            label="${bt.label}"
                            value="${bt.name()}"
                        ></atlas-option>
                    </g:each>
                </atlas-select>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
    <atlas-grid>
        <atlas-row>
            <atlas-col lg="6">
                <atlas-select
                    label="Cliente"
                    placeholder="Selecione uma opção"
                    class="js-payer-select"
                    name="payerId"
                    required
                    value="${payment?.payer?.id}"
                >
                    <g:each var="payer" in="${payerList}">
                        <atlas-option
                            label="${payer.name}"
                            value="${payer.id}"
                            label-key="${payer.name}"
                        ></atlas-option>
                    </g:each>
                </atlas-select>
            </atlas-col>
        </atlas-row>
    </atlas-grid>

</atlas-form-panel>
</body>
</html>
