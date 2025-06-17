package custom

import org.codenarc.rule.AbstractRule
import org.codenarc.rule.Violation
import org.codenarc.source.SourceCode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.FieldNode

@SuppressWarnings('CompileStatic')
class DefaultEncodeAsHTMLNotFoundRule extends AbstractRule {

    String name = 'DefaultEncodeAsHTMLNotFoundRule'

    int priority = 1

    String applyToFileNames = '*TagLib.groovy'

    void applyTo(SourceCode sourceCode, List<Violation> violations) {
        for (ClassNode clazz : sourceCode.ast.classes) {
            if (this.validateClassHasDefaultEncodeAsHtml(clazz)) continue

            violations.add(buildViolation(sourceCode, clazz))
        }
    }

    private Violation buildViolation(SourceCode sourceCode, ClassNode clazz) {
        return createViolation(
                sourceCode,
                clazz,
                "TagLibs devem possuir a propriedade `static defaultEncodeAs` com valor `html` para garantia de codificação das saídas em HTML e minimização de risco de Cross-Site Scripting (XSS). [Saiba mais](https://github.com/asaasdev/livro-de-elite/blob/master/best-practices/asaas.md#encode-nas-taglibs)"
        )
    }

    private Boolean validateClassHasDefaultEncodeAsHtml(ClassNode clazz) {
        for (FieldNode field : clazz.fields) {
            if (this.validateFieldIsDefaultEncodeAsHTML(field)) return true
        }

        return false
    }

    private Boolean validateFieldIsDefaultEncodeAsHTML(FieldNode field) {
        if (!field.isStatic()) return false
        if (field.getName() != "defaultEncodeAs") return false
        if (field.getInitialValueExpression().text != "html") return false

        return true
    }
}
