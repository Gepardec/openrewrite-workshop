package com.gepardec;

import org.openrewrite.ExecutionContext;
import org.openrewrite.Recipe;
import org.openrewrite.TreeVisitor;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.tree.J;

public class RecipeImplementationExample extends Recipe {
    @Override
    public String getDisplayName() {
        return "RecipeImplementationExample";
    }

    @Override
    public String getDescription() {
        return "Uebung zu Java-Rezeptimplementierungen des OpenRewrite Workshops.";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new JavaIsoVisitor<>() {
            @Override
            public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext executionContext) {
                method = super.visitMethodDeclaration(method, executionContext);
                // Alle Klassen herausfiltern, die nicht "RecipeImplementationExampleTest" heißen

                // Methoden herausfiltern, die die Deprecated Annotation nicht besitzen

                // Annotation entfernen (Liste ohne Deprecated-Annotation setzen)
                return method;
            }
        };
    }
}
