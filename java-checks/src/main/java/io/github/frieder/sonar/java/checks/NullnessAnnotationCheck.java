package io.github.frieder.sonar.java.checks;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.*;

import javax.annotation.Nullable;
import java.util.*;

/**
 * The goal of this rule is to mark any @NonNull/@Nullable annotation invalid except the annotations
 * provided by the Checker Framework. Currently the following annotations are covered by this rule.
 * <ul>
 * <li>javax.validation.constraints.NotNull
 * <li>javax.annotation.Nonnull
 * <li>javax.annotation.Nullable
 * <li>edu.umd.cs.findbugs.annotations.NonNull
 * <li>edu.umd.cs.findbugs.annotations.Nullable
 * <li>org.jetbrains.annotations.NotNull
 * <li>org.jetbrains.annotations.Nullable
 * <li>org.eclipse.jdt.annotation.NonNull
 * <li>org.eclipse.jdt.annotation.Nullable
 * <li>lombok.NonNull
 * </ul>
 */
@Rule(key = "NullnessAnnotationCheck")
public class NullnessAnnotationCheck extends IssuableSubscriptionVisitor {
    private static final Set<String> BLACKLISTED_ANNOTATIONS = ImmutableSet.of(
            "javax.validation.constraints.NotNull",
            "javax.annotation.Nonnull",
            "javax.annotation.Nullable",
            "edu.umd.cs.findbugs.annotations.NonNull",
            "edu.umd.cs.findbugs.annotations.Nullable",
            "org.jetbrains.annotations.NotNull",
            "org.jetbrains.annotations.Nullable",
            "org.eclipse.jdt.annotation.NonNull",
            "org.eclipse.jdt.annotation.Nullable",
            "lombok.NonNull"
    );
    private static final String MSG_ANNO = "Invalid nullable annotation found";
    private static final String MSG_IMPORT = "Invalid nullable annotation import found";

    /*
     * Extracted from org.sonar.java.checks.helpers.ExpressionsHelper to prevent
     * NoClassDefFoundError
     */
    private static String concatenate(@Nullable ExpressionTree tree) {
        if (tree == null) {
            return "";
        } else {
            Deque<String> pieces = new LinkedList();

            ExpressionTree expr;
            MemberSelectExpressionTree mse;
            for (expr = tree; expr.is(new Tree.Kind[]{Tree.Kind.MEMBER_SELECT}); expr = mse.expression()) {
                mse = (MemberSelectExpressionTree) expr;
                pieces.push(mse.identifier().name());
                pieces.push(".");
            }

            if (expr.is(new Tree.Kind[]{Tree.Kind.IDENTIFIER})) {
                IdentifierTree idt = (IdentifierTree) expr;
                pieces.push(idt.name());
            }

            StringBuilder sb = new StringBuilder();
            Iterator var4 = pieces.iterator();

            while (var4.hasNext()) {
                String piece = (String) var4.next();
                sb.append(piece);
            }

            return sb.toString();
        }
    }

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.<Tree.Kind>builder()
                .add(Tree.Kind.ANNOTATION)
                .add(Tree.Kind.IMPORT)
                .build();
    }

    @Override
    public void visitNode(Tree tree) {
        if (tree.is(Tree.Kind.ANNOTATION)) {
            if (BLACKLISTED_ANNOTATIONS.contains(((AnnotationTree) tree).annotationType().symbolType().fullyQualifiedName())) {
                reportIssue(tree, MSG_ANNO);
            }
        } else if (tree.is(Tree.Kind.IMPORT)) {
            if (BLACKLISTED_ANNOTATIONS.contains(NullnessAnnotationCheck.concatenate((ExpressionTree) ((ImportTree) tree).qualifiedIdentifier()))) {
                reportIssue(tree, MSG_IMPORT);
            }
        }
    }

}
