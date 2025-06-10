<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Mini Asaas</title>
    <asset:link rel="icon" href="asaas.png" type="image/x-ico"/>
    <link defer rel="stylesheet" href="https://atlas.asaas.com/v26.2.0/atlas.css" crossorigin="anonymous">
    <script defer src="https://atlas.asaas.com/v26.2.0/atlas.js" crossorigin="anonymous"></script>
</head>
<body >    
    <atlas-screen fullscreen>    
        <atlas-page container>                       
                <atlas-page-content slot="content">
                    <atlas-form-panel 
                        class="js-create-customer-form" 
                        editing 
                        action="${createLink(controller: 'customer', action: 'saveCustomer')}" 
                        method="post"
                        header="Criar Conta"
                    >
                        <atlas-section header="Dados do Usuário" header-size="h6">
                            <atlas-grid>
                                <atlas-row>
                                    <atlas-col lg="6">
                                        <atlas-input
                                            label="Nome"
                                            id="name"
                                            type="text"
                                            placeholder="John Doe"
                                            required
                                        ></atlas-input>
                                    </atlas-col>
                                    <atlas-col lg="6">
                                        <atlas-masked-input
                                            mask-alias="email"
                                            label="E-mail"
                                            id="email"                                    
                                            placeholder="john.doe@example.com"
                                            required
                                        ></atlas-masked-input>         
                                    </atlas-col>
                                    <atlas-col lg="4">
                                        <atlas-masked-input
                                            mask-alias="cpf-cnpj"
                                            label="CPF/CNPJ"
                                            id="cpfCnpj"
                                            placeholder="123.456.789-00"
                                            required
                                        ></atlas-masked-input>
                                    </atlas-col>
                                    <atlas-col lg="4">
                                        <atlas-masked-input
                                            mask-alias="cellphone"
                                            label="Telefone"
                                            id="phone"
                                            mask="(99) 99999-9999"
                                            placeholder="(11) 91234-5678"
                                            required
                                        ></atlas-masked-input>
                                    </atlas-col>
                                    <atlas-col lg="4">
                                        <atlas-masked-input
                                            label="Data de Nascimento"
                                            id="birthDate"
                                            type="date"
                                            required
                                        ></atlas-masked-input>
                                    </atlas-col>
                                </atlas-row>
                            </atlas-grid>
                        </atlas-section>
                        <atlas-section header="Dados de Endereço" header-size="h6">
                            <atlas-grid>
                                <atlas-row>
                                    <atlas-col lg="3">
                                        <atlas-postal-code label="CEP"
                                            class="js-postal-code-input"
                                            id="postalCode"
                                            required>
                                        </atlas-postal-code>
                                    </atlas-col>
                                    <atlas-col lg="3">
                                        <atlas-input 
                                            label="Rua"
                                            class="js-address-input"
                                            id="address"
                                            type="text"
                                            placeholder="Rua Exemplo">
                                        </atlas-input>
                                    </atlas-col>
                                    <atlas-col lg="3">
                                        <atlas-input
                                            label="Bairro"
                                            class="js-address-complement-input"
                                            id="addressComplement"
                                            type="text"
                                            placeholder="Bairro Exemplo">
                                        </atlas-input>
                                    </atlas-col>
                                    <atlas-col lg="3">
                                        <atlas-input
                                            label="Número"                                            
                                            id="addressNumber"
                                            type="number"
                                            placeholder="123">
                                        </atlas-input>
                                    </atlas-col>
                                    <atlas-col lg="6">
                                        <atlas-input
                                            label="Cidade"
                                            class="js-city-input"
                                            id="city"
                                            type="text"
                                            placeholder="Campo Grande">
                                        </atlas-input>
                                    </atlas-col>
                                    <atlas-col lg="6">
                                        <atlas-select 
                                            class="js-state-input"
                                            id="state"
                                            label="Estado" 
                                            placeholder="Selecione o estado"
                                            >
                                            <atlas-option label="AC" id="uf" value="AC">Acre</atlas-option>
                                            <atlas-option label="AL" id="uf" value="AL">Alagoas</atlas-option>
                                            <atlas-option label="AM" id="uf" value="AM">Amazonas</atlas-option>
                                            <atlas-option label="AP" id="uf" value="AP">Amapá</atlas-option>
                                            <atlas-option label="BA" id="uf" value="BA">Bahia</atlas-option>
                                            <atlas-option label="CE" id="uf" value="CE">Ceará</atlas-option>
                                            <atlas-option label="DF" id="uf" value="DF">Distrito Federal</atlas-option>
                                            <atlas-option label="ES" id="uf" value="ES">Espírito Santo</atlas-option>
                                            <atlas-option label="GO" id="uf" value="GO">Goiás</atlas-option>
                                            <atlas-option label="MA" id="uf" value="MA">Maranhão</atlas-option>
                                            <atlas-option label="MG" id="uf" value="MG">Minas Gerais</atlas-option>
                                            <atlas-option label="MS" id="uf" value="MS">Mato Grosso do Sul</atlas-option>
                                            <atlas-option label="MT" id="uf" value="MT">Mato Grosso</atlas-option>
                                            <atlas-option label="PA" id="uf" value="PA">Pará</atlas-option>
                                            <atlas-option label="PB" id="uf" value="PB">Paraíba</atlas-option>
                                            <atlas-option label="PE" id="uf" value="PE">Pernambuco</atlas-option>
                                            <atlas-option label="PI" id="uf" value="PI">Piauí</atlas-option>
                                            <atlas-option label="PR" id="uf" value="PR">Paraná</atlas-option>
                                            <atlas-option label="RJ" id="uf" value="RJ">Rio de Janeiro</atlas-option>
                                            <atlas-option label="RN" id="uf" value="RN">Rio Grande do Norte</atlas-option>
                                            <atlas-option label="RO" id="uf" value="RO">Rondônia</atlas-option>
                                            <atlas-option label="RR" id="uf" value="RR">Roraima</atlas-option>
                                            <atlas-option label="RS" id="uf" value="RS">Rio Grande do Sul</atlas-option>
                                            <atlas-option label="SC" id="uf" value="SC">Santa Catarina</atlas-option>
                                            <atlas-option label="SE" id="uf" value="SE">Sergipe</atlas-option>
                                            <atlas-option label="SP" id="uf" value="SP">São Paulo</atlas-option>
                                            <atlas-option label="TO" id="uf" value="TO">Tocantins</atlas-option>
                                        </atlas-select>
                                    </atlas-col>
                                </atlas-row>                                
                            </atlas-grid>
                        </atlas-section>
                    </atlas-panel>
                </atlas-page-content>
        </atlas-page>
    </atlas-screen>
    <asset:javascript src="customer/CreateCustomerController.js" />
</body>
</html>