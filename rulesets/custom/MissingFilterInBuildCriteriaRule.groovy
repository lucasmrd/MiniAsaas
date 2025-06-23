package custom

import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.CastExpression
import org.codehaus.groovy.ast.expr.ClosureExpression
import org.codehaus.groovy.ast.expr.ConstantExpression
import org.codehaus.groovy.ast.expr.Expression
import org.codehaus.groovy.ast.expr.ListExpression
import org.codehaus.groovy.ast.expr.MapEntryExpression
import org.codehaus.groovy.ast.expr.MapExpression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.stmt.BlockStatement
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.stmt.IfStatement
import org.codehaus.groovy.ast.stmt.ReturnStatement
import org.codehaus.groovy.ast.stmt.Statement
import org.codenarc.rule.AbstractAstVisitorRule
import org.codenarc.rule.AbstractAstVisitor

import java.util.regex.Matcher
import java.util.regex.Pattern

@SuppressWarnings('CompileStatic')
class MissingFilterInBuildCriteriaRule extends AbstractAstVisitorRule {

    String name = "MissingFilterInBuildCriteriaRule"
    int priority = 1
    Class astVisitorClass = MissingFilterInBuildCriteriaAstVisitor
}

@SuppressWarnings('CompileStatic')
class MissingFilterInBuildCriteriaAstVisitor extends AbstractAstVisitor {

    private final List<String> filtersImplementedByDefault = ["deletedOnly", "includeDeleted", "id"]
    private final List<String> allowedFilters = []
    private final List<IfStatement> ifStatementsInBuildCriteria = []
    private final List<String> filtersInRequiredAny = []

    @Override
    void visitMethodEx(MethodNode methodNode) {
        if (methodNode.name == 'listAllowedFilters') {
            extractAllowedFiltersFromListMethod(methodNode)
        } else if (methodNode.name == 'buildCriteria') {
            extractIfStatementsFromBuildCriteria(methodNode)
        }

        super.visitMethodEx(methodNode)
    }

    @Override
    void visitField(FieldNode fieldNode) {
        if (fieldNode.name != 'requiredAnyFiltersList') return

        Expression castExpression = fieldNode.initialExpression
        if (castExpression instanceof CastExpression) {
            castExpression = ((CastExpression) castExpression).expression
        }

        if (!(castExpression instanceof ListExpression)) return
        ListExpression listExpression = (ListExpression) castExpression

        for (Expression element : listExpression.expressions) {
            if (!(element instanceof MapExpression)) continue

            MapExpression mapExpression = (MapExpression) element
            for (MapEntryExpression entry : mapExpression.mapEntryExpressions) {
                if (entry.keyExpression.text != 'searchKey') continue

                Expression value = entry.valueExpression
                if (value instanceof ConstantExpression && value.value instanceof String) {
                    this.filtersInRequiredAny.add((String) value.value)
                }
            }
        }

        super.visitField(fieldNode)
    }

    @Override
    void visitClassComplete(ClassNode classNode) {
        Set<String> filtersUsedInSearchChecks = new HashSet<>()
        this.allowedFilters.removeAll(this.filtersInRequiredAny)
        this.allowedFilters.removeAll(this.filtersImplementedByDefault)

        for (IfStatement ifStatement : this.ifStatementsInBuildCriteria) {
            String conditionText = ifStatement.booleanExpression.expression.text
            for (String filterName : this.allowedFilters) {
                if (conditionHasFilterText(conditionText, filterName)) {
                    filtersUsedInSearchChecks.add(filterName)
                }
            }
        }

        for (String filterName : this.allowedFilters) {
            if (!filtersUsedInSearchChecks.contains(filterName)) {
                addViolation(classNode,
                        "Filtro '${filterName}' está em listAllowedFilters() mas não está implementado nos critérios de busca no buildCriteria().")
            }
        }

        super.visitClassComplete(classNode)
    }

    private void extractAllowedFiltersFromListMethod(MethodNode method) {
        if (!(method.code instanceof BlockStatement)) return

        List<Statement> statements = ((BlockStatement) method.code).statements
        List<String> extracted = statements
                .findAll { it instanceof ReturnStatement }
                .collect { ((ReturnStatement) it).expression }
                .findAll { it instanceof ListExpression }
                .collectMany { ((ListExpression) it).expressions }
                .findAll { it instanceof ConstantExpression && ((ConstantExpression) it).value instanceof String }
                .collect { (String) ((ConstantExpression) it).value }

        this.allowedFilters.addAll(extracted)
    }

    private void extractIfStatementsFromBuildCriteria(MethodNode method) {
        if (!(method.code instanceof BlockStatement)) return

        List<Statement> statements = ((BlockStatement) method.code).statements
        for (Statement statement : statements) {
            if (!(statement instanceof ExpressionStatement)) continue

            Expression expression = ((ExpressionStatement) statement).expression
            if (!(expression instanceof MethodCallExpression)) continue

            MethodCallExpression methodCall = (MethodCallExpression) expression
            if (methodCall.methodAsString != 'addCriteria') continue

            if (!(methodCall.arguments instanceof ArgumentListExpression)) return

            Expression firstArg = ((ArgumentListExpression) methodCall.arguments).expressions.get(0)
            if (!(firstArg instanceof ClosureExpression)) return

            ClosureExpression closure = (ClosureExpression) firstArg
            collectIfStatementsFromClosure(closure)
        }
    }

    private void collectIfStatementsFromClosure(ClosureExpression closureExpression) {
        if (!(closureExpression.code instanceof BlockStatement)) return

        List<Statement> statements = ((BlockStatement) closureExpression.code).statements
        for (Statement statement : statements) {
            collectIfStatementRecursive(statement)
        }
    }

    private void collectIfStatementRecursive(Statement statement) {
        if (!(statement instanceof IfStatement)) return

        IfStatement ifStatement = (IfStatement) statement
        this.ifStatementsInBuildCriteria.add(ifStatement)

        Statement elseBlock = ifStatement.elseBlock
        if (elseBlock instanceof IfStatement) {
            collectIfStatementRecursive(elseBlock)
        }
    }

    private boolean conditionHasFilterText(String conditionText, String filterName) {
        if (filterName.contains("[")) {
            return conditionText.contains(filterName)
        }
        String pattern = /\b${Pattern.quote(filterName)}\b/
        Matcher containsDelimitedWord = conditionText =~ pattern
        return containsDelimitedWord.find()
    }
}