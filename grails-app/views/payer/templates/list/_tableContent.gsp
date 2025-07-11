<g:each var="payer" in="${ payerList }">
    <atlas-table-row>
        <atlas-table-col>
            ${payer.name}
        </atlas-table-col>
        <atlas-table-col>
            <formatTagLib:cpfCnpj cpfCnpj="${payer?.cpfCnpj}"/>
        </atlas-table-col>
        <atlas-table-col>
            ${payer.email}
        </atlas-table-col>
        <atlas-table-col>
            <atlas-button-group size="sm">
                <atlas-icon-button
                    icon="pencil"
                    theme="primary"
                    size="lg"
                    description="update"
                    data-id="${payer.id}"
                ></atlas-icon-button>
                <atlas-icon-button
                    icon="trash"
                    theme="danger"
                    size="sm"
                    description="delete"
                    data-id="${payer.id}"
                ></atlas-icon-button>
            </atlas-button-group>
        </atlas-table-col>
    </atlas-table-row>
</g:each>
