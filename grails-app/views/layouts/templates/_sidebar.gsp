<atlas-sidebar
    slot="sidebar"
    home-path="${createLink(controller: "customer", action: "index")}"
>
    <atlas-sidebar-menu slot="body">
        <atlas-button style="margin-bottom:20px"
            theme="primary"
            description="Criar cobrança"
            size="lg"
            block
            href="${createLink(controller: "payment", action: "create")}"
        >
        </atlas-button>
        <atlas-sidebar-menu-item
            icon="dashboard"
            value="dashboard"
            text="Resumo"
            href="${createLink(controller: "dashboard", action: "index")}"
            ${ controllerName == "dashboard" && actionName == "index" ? "active" : "" }
        ></atlas-sidebar-menu-item>
        <atlas-sidebar-menu-item
            icon="money"
            value="payment"
            text="Cobranças"
        >
            <atlas-sidebar-menu-item
                icon="files"
                value="list-payment"
                text="Todas"
                href="${createLink(controller: "payment", action: "list")}"
            ></atlas-sidebar-menu-item>
            <atlas-sidebar-menu-item
                icon="file-check"
                value="list-payment"
                text="Pagas"
                href="${createLink(controller: "payment", action: "list", params: [status: 'CONFIRMED'])}"
            ></atlas-sidebar-menu-item>
            <atlas-sidebar-menu-item
                icon="trash"
                value="list-payment"
                text="Excluídas"
                href="${createLink(controller: "payment", action: "list", params: [deleted: true])}"
            ></atlas-sidebar-menu-item>
        </atlas-sidebar-menu-item>
    </atlas-sidebar-menu>
</atlas-sidebar>