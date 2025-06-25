<!doctype html>
<html lang="pt-br">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Mini Asaas"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="asaas.png" type="image/x-ico"/>
    <g:render template="/layouts/templates/atlas" />
    <g:layoutHead/>
</head>

<body class="has-atlas">
    <atlas-screen fullscreen>
        <g:render template="/layouts/templates/navbar" />
        <g:render template="/layouts/templates/sidebar" />
        <atlas-page>
            <atlas-page-header page-name="${message(code:"page.name.${controllerName}.${actionName}")}" slot="header">
            </atlas-page-header>
            <atlas-page-content slot="content">
                <g:layoutBody/>
            </atlas-page-content>
        </atlas-page>
    </atlas-screen>
</body>
</html>
