<%@ page import="com.asaas.mini.enums.CompanyType" %>
<%@ page import="com.asaas.mini.enums.PersonType" %>
<%@ page import="com.asaas.mini.utils.DateUtil" %>

<atlas-section header="Dados do Pagador" header-size="h6">
    <atlas-grid>
        <atlas-row>
            <atlas-col lg="6">
                <atlas-input
                    class="js-payer-name-field"
                    label="Nome"
                    name="name"
                    value="${payer?.name}"
                    id="name"
                    type="text"
                    placeholder="Digite seu nome"
                    required
                ></atlas-input>
            </atlas-col>
            <atlas-col lg="6">
                <atlas-masked-input
                    mask-alias="cpf-cnpj"
                    class="js-cpf-cnpj-field"
                    label="CPF/CNPJ"
                    id="cpf"
                    name="cpfCnpj"
                    value="${payer?.cpfCnpj}"
                    required
                ></atlas-masked-input>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
</atlas-section>
