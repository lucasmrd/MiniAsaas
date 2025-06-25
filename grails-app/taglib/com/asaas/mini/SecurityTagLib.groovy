package com.asaas.mini

class SecurityTagLib {

    static namespace = "securityTagLib"

    def springSecurityService

    def currentUser = { attrs, body ->
        if (springSecurityService.isLoggedIn()) {
            out << springSecurityService.currentUser.username
            return
        }

        out << "Usuário não logado"
    }
}