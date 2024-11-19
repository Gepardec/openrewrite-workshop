package com.gepardec;

import org.openrewrite.ExecutionContext;
import org.openrewrite.NlsRewrite;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.TreeVisitingPrinter;
import org.openrewrite.java.tree.J;

public class Uebung05 extends Recipe {
    @Override
    public @NlsRewrite.DisplayName String getDisplayName() {
        return "Uebung05";
    }

    @Override
    public @NlsRewrite.Description String getDescription() {
        return "5. Uebung des internen OpenRewrite Trainings.";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new JavaIsoVisitor<>() {
            @Override
            public J.CompilationUnit visitCompilationUnit(J.CompilationUnit cu, ExecutionContext executionContext) {
                System.out.println(TreeVisitingPrinter.printTree(getCursor()));
                return super.visitCompilationUnit(cu, executionContext);
            }
        };
    }
}
