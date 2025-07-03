<%@ page import="com.asaas.mini.Payment" %>

<%
    BigDecimal minValue = Payment.MIN_VALUE
%>

<atlas-section header="O que cobrar?">
    <atlas-grid>
        <atlas-row>
            <atlas-col lg="3">
                <atlas-money
                    class="js-value-input"
                    label="Valor da cobrança"
                    name="value"
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
                    prevent-past-date
                    required
                ></atlas-date-picker>
            </atlas-col>
        </atlas-row>
        <atlas-row>
            <atlas-col lg="5">
                <atlas-textarea
                    class="js-description-input"
                    label="Descrição da cobrança"
                    name="description"
                ></atlas-textarea>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
</atlas-section>