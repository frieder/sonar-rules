package io.github.frieder.sonar.java.checks;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang.StringUtils;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;

import java.util.List;

/**
 * Whenever an interface or an abstract class has public/protected methods check that neither
 * the parameter types nor the return type are of type vavr.io.*. The reason behind this rule
 * is that we would like to keep the interfaces as generic as possible. Vavr.io is not commonly
 * used so we do not want to force others to use it just to implement public APIs. The use of
 * vavr for internal implementations is still possible.
 */
@Rule(key = "VavrPublicSignatureCheck")
public class VavrPublicSignatureCheck extends IssuableSubscriptionVisitor {
    private static final String MSG = "Invalid use of vavr classes";
    private static final String VAVR_PKG = "io.vavr";
    private boolean isIfaceOrAbstract;


    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.<Tree.Kind>builder()
                .add(Tree.Kind.CLASS)
                .add(Tree.Kind.INTERFACE)
                .add(Tree.Kind.METHOD)
                .build();
    }

    @Override
    public void visitNode(Tree tree) {
        if (tree.is(Tree.Kind.INTERFACE)) {
            ClassTree iface = (ClassTree) tree;
            isIfaceOrAbstract = true;
        } else if (tree.is(Tree.Kind.CLASS)) {
            ClassTree klasse = (ClassTree) tree;
            isIfaceOrAbstract = klasse.symbol().isAbstract();
        } else if (tree.is(Tree.Kind.METHOD)) {
            MethodTree method = (MethodTree) tree;
            boolean isPublicOrProtected = method.symbol().isPublic() || method.symbol().isProtected();

            if (isIfaceOrAbstract && isPublicOrProtected) {
                if (StringUtils.startsWith(method.returnType().symbolType().fullyQualifiedName(), VAVR_PKG)) {
                    reportIssue(tree, MSG);
                }

                method.parameters().forEach(param -> {
                    if (StringUtils.startsWith(param.symbol().type().fullyQualifiedName(), VAVR_PKG)) {
                        reportIssue(tree, MSG);
                    }
                });
            }
        }
    }
}
