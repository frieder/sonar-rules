package io.github.frieder.sonar.java.checks;

import org.apache.commons.lang.StringUtils;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.AnnotationTree;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MethodTree;

/**
 * Whenever a return value is annotated with @Nullable show a hint suggesting to use Optional/Option instead.
 */
@Rule(key = "NullableReturnCheck")
public class NullableReturnCheck extends BaseTreeVisitor implements JavaFileScanner {
    private static final String NULLABLE = "org.checkerframework.checker.nullness.qual.Nullable";
    private static final String DEF_MSG = "Consider using Optional/Option instead of nullable";
    private JavaFileScannerContext context;
    private String methodName = StringUtils.EMPTY;

    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    @Override
    public void visitMethod(MethodTree tree) {
        // mark when you enter a method by storing the method's name locally
        methodName = tree.simpleName().name();
        super.visitMethod(tree);
        methodName = StringUtils.EMPTY;
    }

    @Override
    public void visitIdentifier(IdentifierTree tree) {
        // only check identifiers until the identifier for the method's name is found
        if (StringUtils.equals(methodName, tree.name())) {
            methodName = StringUtils.EMPTY;
        } else {
            super.visitIdentifier(tree);
        }
    }

    @Override
    public void visitAnnotation(AnnotationTree tree) {
        // as long as within a method and the method's name identifier is not reached
        // check the annotations
        if (StringUtils.isNotBlank(methodName) &&
                StringUtils.equals(NULLABLE, tree.annotationType().symbolType().fullyQualifiedName())) {
            context.reportIssue(this, tree, DEF_MSG);
        }
    }
}
