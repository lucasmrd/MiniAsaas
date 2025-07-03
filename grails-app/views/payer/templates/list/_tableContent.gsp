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
    </atlas-table-row>
</g:each>
