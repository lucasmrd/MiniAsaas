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
        <atlas-table-col>
            <atlas-button-group>
                <atlas-icon-button
                    icon="check-circle"
                    theme="primary"
                    tooltip="Confirmar pagamento"
                    description="confirmar"
                    data-id="${payment.id}"
                ></atlas-icon-button>
                <atlas-icon-button
                    icon="pencil"
                    theme="primary"
                    tooltip="Editar cobrança"
                    description="editar"
                    data-id="${payment.id}"
                ></atlas-icon-button>
                <atlas-icon-button
                    class="js-open-modal-button"
                    icon="trash"
                    theme="danger"
                    tooltip="Excluir cobrança"
                    description="excluir"
                    data-id="${payment.id}"
                ></atlas-icon-button>
            </atlas-button-group>
        </atlas-table-col>
    </atlas-table-row>
</g:each>