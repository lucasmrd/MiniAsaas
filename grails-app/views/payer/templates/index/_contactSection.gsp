<atlas-section header="Dados de Contato" header-size="h6" hide-optional>
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
                    class="js-phone-input"
                    label="Telefone"
                    name="phone"
                    id="phone"
                    hide-optional
                ></atlas-masked-input>
            </atlas-col>
            <atlas-col lg="3">
                <atlas-masked-input
                    mask-alias="cell-phone"
                    class="js-cellphone-input"
                    label="Celular"
                    name="mobilePhone"
                    id="mobilePhone"
                    hide-optional
                ></atlas-masked-input>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
</atlas-section>