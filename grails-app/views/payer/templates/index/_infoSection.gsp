<%@ page import="com.asaas.mini.enums.CompanyType" %>
<%@ page import="com.asaas.mini.enums.PersonType" %>
<%@ page import="com.asaas.mini.utils.DateUtil" %>

<atlas-section header="Dados do Pagador" header-size="h6">
    <atlas-grid>
        <atlas-row>
            <atlas-col lg="12">
                <atlas-select
                    name="personType"
                    id="personType"
                    class="js-person-type-select"
                    label="Tipo de Pessoa"
                    placeholder="Selecione uma opção"
                    value="${PersonType.NATURAL}"
                    required>
                    <g:each var="personType" in="${ PersonType.values() }">
                        <atlas-option label="${personType.getLabel()}" value="${personType}"> </atlas-option>
                    </g:each>
                </atlas-select>
            </atlas-col>
        </atlas-row>
        <atlas-row>
            <atlas-col lg="6">
                <atlas-input
                    label="Nome Completo"
                    class="js-natural-person-type-fields"
                    name="name"
                    id="name"
                    type="text"
                    placeholder="Digite seu nome"
                    required
                ></atlas-input>
                <atlas-input
                    label="Nome da Empresa"
                    class="js-legal-person-type-fields"
                    name="companyName"
                    id="companyName"
                    type="text"
                    placeholder="Digite o nome da empresa"
                    hidden
                ></atlas-input>
            </atlas-col>
            <atlas-col lg="3">
                <atlas-masked-input
                    mask-alias="cpf"
                    class="js-cpf-cnpj-field"
                    label="CPF"
                    id="cpf"
                    name="cpfCnpj"
                    required
                ></atlas-masked-input>
            </atlas-col>
            <atlas-col lg="3">
                <atlas-date-picker
                    label="Data de Nascimento"
                    class="js-natural-person-type-fields"
                    name="birthDate"
                    id="birthDate"
                    max-date="${}"
                    prevent-later-date
                    required
                ></atlas-date-picker>
                <atlas-select
                    name="companyType"
                    id="companyType"
                    class="js-legal-person-type-fields"
                    label="Tipo de Empresa"
                    placeholder="Selecione o tipo de empresa"
                    hidden
                >
                    <g:each var="companyType" in="${ CompanyType.values() }">
                        <atlas-option label="${companyType.getLabel()}" value="${companyType}"> </atlas-option>
                    </g:each>
                </atlas-select>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
</atlas-section>