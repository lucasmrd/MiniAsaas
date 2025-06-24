<atlas-sidebar
    slot="sidebar"
    home-path="${createLink(controller: "customer", action: "index")}"
>
    <atlas-sidebar-menu slot="body">
        <atlas-sidebar-menu-item
            icon="dashboard"
            value="dashboard"
            text="Resumo"
            href="${createLink(controller: "dashboard", action: "index")}"
            ${ controllerName == "dashboard" && actionName == "index" ? "active" : "" }
        ></atlas-sidebar-menu-item>
    </atlas-sidebar-menu>
</atlas-sidebar>