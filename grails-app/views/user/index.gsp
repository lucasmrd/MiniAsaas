<!DOCTYPE html>
<html>
<head>
    <title>Criar conta</title>
    <meta name="layout" content="external"/>
</head>
<body>
    <atlas-form-panel
        action="${createLink(controller: 'user', action: 'validate')}"
        method="post"
        editing
        header="Criar Conta"
    >
        <atlas-row>
            <atlas-col lg="3">
                <atlas-masked-input
                    width="lg"
                    mask-alias="email"
                    label="E-mail"
                    id="username"
                    name="username"
                    placeholder="Digite seu e-mail"
                    required
                ></atlas-masked-input>
            </atlas-col>
            <atlas-col lg="3">
                <atlas-password-input
                    width="lg"
                    label="Senha"
                    name="password"
                    id="password"
                    placeholder="Digite sua senha"
                    required
                ></atlas-password-input>
            </atlas-col>
            <atlas-col lg="3">
                <atlas-password-input
                    width="lg"
                    label="Confirmar senha"
                    name="passwordConfirmation"
                    id="passwordConfirmation"
                    placeholder="Confirme sua senha"
                    required
                ></atlas-password-input>
            </atlas-col>
        </atlas-row>
    </atlas-form-panel>
</body>
</html>