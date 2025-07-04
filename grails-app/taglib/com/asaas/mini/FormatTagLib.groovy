package com.asaas.mini

import com.asaas.mini.utils.Validator
import javax.swing.text.MaskFormatter

class FormatTagLib {
    static namespace = "formatTagLib"

    def formatedDateCreated = { attrs, body ->
        Date dateCreated = attrs.date

        if (dateCreated) {
            out << g.formatDate(format: "dd/MM/yyyy", date: dateCreated)
        }
    }

    def cpfCnpj = { attrs, body ->
        println attrs
        String cpfCnpj = attrs.cpfCnpj
        if (!cpfCnpj) {
            return
        }

        cpfCnpj = cpfCnpj.replaceAll("\\D+","")
        MaskFormatter maskFormatter

        if (Validator.isValidCpf(cpfCnpj)) {
            maskFormatter = new MaskFormatter("###.###.###-##")
        } else if (Validator.isValidCnpj(cpfCnpj)) {
            maskFormatter = new MaskFormatter("##.###.###/####-##")
        } else {
            out << cpfCnpj
            return
        }

        maskFormatter.setValueContainsLiteralCharacters(false)
        out << maskFormatter.valueToString(cpfCnpj)
    }
}
