<%@ page import="com.asaas.mini.Payment" %>

<%
    BigDecimal minValue = Payment.MIN_VALUE
%>

<atlas-grid>
    <atlas-row>
        <atlas-col lg="3">
            <atlas-money
                class="js-value-input"
                label="Valor da cobrança"
                name="value"
                value="${payment?.value}"
                min-value="${minValue}"
                min-value-error-message="O valor mínimo é R$ ${minValue}"
                required
            ></atlas-money>
        </atlas-col>
        <atlas-col lg="3">
            <atlas-date-picker
                class="js-due-date-input"
                label="Vencimento da cobrança"
                name="dueDate"
                value="${formatTagLib.formatedDateCreated(date: payment?.dueDate)}"
                prevent-past-date
                required
            ></atlas-date-picker>
        </atlas-col>
    </atlas-row>
    <atlas-row>
        <atlas-col lg="6">
            <atlas-textarea
                class="js-description-input"
                label="Descrição da cobrança"
                value="${payment?.description}"
                name="description"
            ></atlas-textarea>
        </atlas-col>
    </atlas-row>
</atlas-grid>