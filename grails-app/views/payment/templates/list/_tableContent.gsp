<g:each var="payment" in="${paymentList}">
    <atlas-table-row>
        <atlas-table-col>
            ${payment.payer.name}
        </atlas-table-col>
        <atlas-table-col>
            <formatTagLib:formatCurrency value="${payment.value}"/>
        </atlas-table-col>
            <atlas-table-col>
                ${payment.description ?: 'Descrição não informada.'}
            </atlas-table-col>
        <atlas-table-col>
            ${payment.billingType.label}
        </atlas-table-col>
        <atlas-table-col>
            <formatTagLib:formatedDateCreated date="${payment.dueDate}"/>
        </atlas-table-col>
    </atlas-table-row>
</g:each>