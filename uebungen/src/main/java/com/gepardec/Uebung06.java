package com.gepardec;

import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;

import java.util.List;

public class Uebung06 extends Recipe {
    @Override
    public String getDisplayName() {
        return "Uebung06";
    }

    @Override
    public String getDescription() {
        return "6. Uebung des internen OpenRewrite Workshops.";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new JavaIsoVisitor<>() {
            @Override
            public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext executionContext) {
                method = super.visitMethodDeclaration(method, executionContext);
                if (!getCursor().firstEnclosingOrThrow(J.ClassDeclaration.class).getSimpleName().equals("Uebung06Test")) {
                    return method;
                }

                List<J.Annotation> filteredAnnotations = method.getLeadingAnnotations().stream()
                        .filter(a -> !a.getSimpleName().equals("Deprecated"))
                        .toList();
                if (method.getLeadingAnnotations().size() == filteredAnnotations.size()) {
                    return method;
                }
                return method.withLeadingAnnotations(filteredAnnotations);
            }
        };
    }
}
