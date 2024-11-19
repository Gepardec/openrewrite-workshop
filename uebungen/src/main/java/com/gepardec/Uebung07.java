package com.gepardec;

import org.openrewrite.*;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;

public class Uebung07 extends Recipe {

    @Override
    public @NlsRewrite.DisplayName String getDisplayName() {
        return "Uebung07";
    }

    @Override
    public @NlsRewrite.Description String getDescription() {
        return "7. Uebung des internen OpenRewrite Workshops.";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new JavaIsoVisitor<>() {
            @Override
            public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext executionContext) {
                // Methoden, die nicht den gegebenen Namen haben, herausfiltern

                // Methoden, die nicht den angegebenen Parameter haben, herausfiltern

                // Methoden, dessen erste Anweisung bereits der Null-Check ist, herausfiltern

                // Null-Check hinzufügen

                return super.visitMethodDeclaration(method, executionContext);
            }
        };
    }
}
