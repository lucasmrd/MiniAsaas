package custom

import org.codehaus.groovy.ast.AnnotationNode
import org.codehaus.groovy.ast.ClassHelper
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codenarc.rule.AbstractAstVisitorRule
import org.codenarc.rule.AbstractAstVisitor

@SuppressWarnings('CompileStatic')
class MissingSensitiveDataObjectAnnotationPropertyRule extends AbstractAstVisitorRule {

    String name = 'MissingSensitiveDataObjectAnnotationPropertyRule'
    int priority = 2
    Class astVisitorClass = MissingSensitiveDataObjectAnnotationPropertyAstVisitor
}

@SuppressWarnings('CompileStatic')
class MissingSensitiveDataObjectAnnotationPropertyAstVisitor extends AbstractAstVisitor {

    @Override
    void visitClassEx(ClassNode classNode) {
        validateSensitiveDataObjectAnnotation(classNode)

        super.visitClassEx(classNode)
    }

    private void validateSensitiveDataObjectAnnotation(ClassNode classNode) {
        List<AnnotationNode> SensitiveDataObjectAnnotationList = classNode.annotations.findAll { it.classNode?.name == 'SensitiveDataObject' }
        if (!SensitiveDataObjectAnnotationList) return

        final List<String> keyNameToValidateList = ["customerIdFieldName", "resourceIdFieldName"]

        for (AnnotationNode annotation : SensitiveDataObjectAnnotationList) {
            annotation.members.each { String key, Expression member ->
                if (!keyNameToValidateList.contains(key)) return
                if (!(member instanceof ConstantExpression) || member.type != ClassHelper.STRING_TYPE) return

                String fieldName = member.value.contains('.') ? member.value.split("\\.")[0] : member.value
                if (fieldName == "id") return

                boolean fieldExistsInClass = classNode.fields.any { it.name == fieldName }
                if (fieldExistsInClass) return

                boolean fieldEndsWithId = fieldName.endsWith("Id") && fieldName.length() > 2
                if (fieldEndsWithId) {
                    String fieldNameWithoutIdSuffix = fieldName[0..-3]
                    fieldExistsInClass = classNode.fields.any { it.name == fieldNameWithoutIdSuffix }
                }

                if (!fieldExistsInClass) {
                    addViolation(classNode, "A propriedade: '${member.value}' da chave: '${key}', especificada na anotação @SensitiveDataObject, não existe na classe: '${classNode.name}'. Verifique se a propriedade é herdada de uma superclasse; se sim, este alerta pode ser ignorado.")
                }
            }
        }
    }
}
