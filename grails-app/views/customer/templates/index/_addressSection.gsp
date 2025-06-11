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
                    class="js-state-select"
                    id="stateSelect"
                    label="Estado"
                    search-on-render
                    value-key="sigla"
                    label-key="nome"
                    search-url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados"
                    search-once
                ></atlas-select>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
</atlas-section>