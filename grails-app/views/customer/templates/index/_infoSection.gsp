<atlas-section header="Dados do Usuário" header-size="h6">
    <atlas-grid>
        <atlas-row>
            <atlas-col lg="6">
                <atlas-input
                    label="Nome"
                    name="name"
                    id="name"
                    type="text"
                    placeholder="Digite seu nome"
                    required
                ></atlas-input>
            </atlas-col>
            <atlas-col lg="6">
                <atlas-masked-input
                    mask-alias="email"
                    label="E-mail"
                    id="email"
                    name="email"
                    placeholder="Digite seu e-mail"
                    required
                ></atlas-masked-input>         
            </atlas-col>
            <atlas-col lg="3">
                <atlas-masked-input
                    mask-alias="cpf-cnpj"
                    label="CPF/CNPJ"
                    id="cpfCnpj"
                    name="cpfCnpj"
                    required
                ></atlas-masked-input>
            </atlas-col>
            <atlas-col lg="3">
                <atlas-masked-input
                    mask-alias="phone"
                    label="Telefone"
                    name="phone"
                    id="phone"
                    mask="(99) 99999-9999"
                    required
                ></atlas-masked-input>
            </atlas-col>
            <atlas-col lg="3">
                <atlas-masked-input
                    mask-alias="cellphone"
                    label="Celular"
                    name="mobilePhone"
                    id="mobilePhone"
                    mask="(99) 99999-9999"
                    required
                ></atlas-masked-input>
            </atlas-col>
            <atlas-col lg="3">
                <atlas-date-picker
                    label="Data de Nascimento"
                    name="birthDate"
                    id="birthDate"
                    prevent-later-date
                    required
                ></atlas-date-picker>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
</atlas-section>