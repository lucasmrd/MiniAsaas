<atlas-section header="Dados de Endereço" header-size="h6">
    <atlas-grid>
        <atlas-row>
            <atlas-col lg="3">
                <atlas-postal-code label="CEP"
                    name="postalCode"
                    class="js-postal-code-input"
                    id="postalCode"
                    required                    
                >
                </atlas-postal-code>
            </atlas-col>
            <atlas-col lg="3">
                <atlas-input 
                    label="Rua"
                    class="js-address-input"
                    name="address"
                    id="address"
                    type="text"
                    placeholder="Rua"
                    required
                >
                </atlas-input>
            </atlas-col>
            <atlas-col lg="3">
                <atlas-input
                    label="Bairro"
                    name="addressComplement"
                    class="js-address-complement-input"
                    id="addressComplement"
                    type="text"
                    placeholder="Bairro"
                    required
                >
                </atlas-input>
            </atlas-col>
            <atlas-col lg="3">
                <atlas-input
                    label="Número"
                    name="addressNumber"
                    id="addressNumber"
                    type="number"
                    placeholder="123"
                    required
                >
                </atlas-input>
            </atlas-col>
            <atlas-col lg="6">
                <atlas-input
                    label="Cidade"
                    name="city"
                    class="js-city-input"
                    id="city"
                    type="text"
                    placeholder="Cidade"
                    required
                >
                </atlas-input>
            </atlas-col>
            <atlas-col lg="6">
                <atlas-select
                    class="js-state-select"
                    id="stateSelect"
                    name="state"
                    label="Estado"
                    placeholder="Selecione um estado"
                    search-on-render
                    value-key="sigla"
                    label-key="nome"
                    search-url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados"
                    search-once
                    required
                ></atlas-select>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
</atlas-section>