<atlas-section header="Qual será a forma de pagamento?">
    <atlas-grid>
        <atlas-row>
            <atlas-col lg="6">
              <atlas-element-group gap="6" class="js-element-group" required-fields="1">
                    <atlas-selection-card
                        type="radio"
                        label="Boleto"
                        name="billingType"
                        value="bank_slip"
                        gap="4"
                    >
                        Taxa de R$ 0,99 por cobrança recebida.
                        Receba em 1 dia útil após o pagamento.
                    </atlas-selection-card>
                    <atlas-selection-card
                        type="radio"
                        label="Pix"
                        name="billingType"
                        value="pix"
                    >
                        Taxa de R$ 0,99 por Pix recebido.
                        Receba em poucos segundos após o pagamento.
                    </atlas-selection-card>
                    <atlas-selection-card
                        type="radio"
                        label="Cartão de Crédito"
                        name="billingType"
                        value="credit_card"
                    >
                        Taxa de 1,99% sobre o valor da cobrança + R$ 0,49
                        Receba em 32 dias após o pagamento de cada parcela.
                    </atlas-selection-card>
                </atlas-element-group>
            </atlas-col>
        </atlas-row>
    </atlas-grid>
</atlas-section>