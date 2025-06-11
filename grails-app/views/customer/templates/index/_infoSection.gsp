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