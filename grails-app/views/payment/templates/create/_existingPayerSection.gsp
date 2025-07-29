<%@ page import="com.asaas.mini.PaymentController" %>

<atlas-grid>
    <atlas-row>
        <atlas-col>
            <atlas-toggle value="created" class="js-payer-toggle" name="payerMode">
                <atlas-toggle-item
                    label="Cliente cadastrado"
                    value="created"
                ></atlas-toggle-item>
                <atlas-toggle-item
                    label="Novo cliente"
                    value="new"
                ></atlas-toggle-item>
            </atlas-toggle>
        </atlas-col>
    </atlas-row>
    <div class="js-existing-payer-section">
        <atlas-row>
            <atlas-col lg="6">
                <atlas-select
                    label="Cliente"
                    placeholder="Selecione uma opção"
                    class="js-payer-select"
                    name="payerId"
                    required
                >
                    <g:each var="payer" in="${payerList}">
                        <atlas-option
                            label="${payer.name}"
                            value="${payer.id}"
                            label-key="${payer.name}"
                            extra-keys="${payer.email}"
                            data-name="${payer.name}"
                        ></atlas-option>
                    </g:each>
                </atlas-select>
            </atlas-col>
        </atlas-row>
    </div>
</atlas-grid>