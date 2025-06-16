<atlas-section header="Dados de Contato" header-size="h6">
    <atlas-grid>
        <atlas-row>
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
                    mask-alias="phone"
                    label="Telefone"
                    name="phone"
                    id="phone"
                    required
                ></atlas-masked-input>
            </atlas-col>
            <atlas-col lg="3">
                <atlas-masked-input
                    mask-alias="cell-phone"
                    label="Celular"
                    name="mobilePhone"
                    id="mobilePhone"
                    required
                ></atlas-masked-input>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
</atlas-section>