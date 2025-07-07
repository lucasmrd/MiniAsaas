<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="external"/>
    <title>Login</title>
</head>
<body>

<g:if test="${error}">
    <atlas-alert
        message="Usuário ou senha inválidos"
        type="error"
    ></atlas-alert>
</g:if>

<atlas-grid style="margin-top: 9rem">
    <atlas-row alignment="center">
        <atlas-col lg="5">
            <atlas-layout gap="6">
                <atlas-heading size="h4">
                    Acesse sua conta
                </atlas-heading>
                <atlas-form action="${postUrl}" method="post" id="loginForm">
                    <atlas-layout gap="6">
                        <atlas-masked-input
                            width="lg"
                            mask-alias="email"
                            label="E-mail"
                            id="username"
                            name="username"
                            value='${username}'
                            placeholder="Digite seu e-mail"
                            required>
                        </atlas-masked-input>
                        <atlas-password-input
                            width="lg"
                            label="Senha"
                            name="password"
                            id="password"
                            placeholder="Digite sua senha"
                            required>
                        </atlas-password-input>
                        <atlas-checkbox
                            width="lg"
                            name="remember-me"
                            id="remember_me"
                        >
                            Continuar conectado
                        </atlas-checkbox>

                        <atlas-layout gap="4">
                            <atlas-button
                                description="Entrar"
                                theme="success"
                                submit
                            ></atlas-button>
                            <atlas-button
                                description="Cadastrar"
                                href="${createLink(controller: 'user', action: 'index')}">
                            </atlas-button>
                        </atlas-layout>
                    </atlas-layout>
                </atlas-form>
            </atlas-layout>
        </atlas-col>
        <atlas-col lg="7" class="illustration-col">
            <atlas-illustration name="computer-key" size="xlg"></atlas-illustration>
        </atlas-col>
    </atlas-row>
</atlas-grid>
</body>
</html>