package com.asaas.mini

class FormatTagLib {
    static namespace = "formatTagLib"

    def formatedDateCreated = { attrs, body ->
        Date dateCreated = attrs.date

        if (dateCreated) {
            out << g.formatDate(format: "dd/MM/yyyy", date: dateCreated)
        }
    }
}
