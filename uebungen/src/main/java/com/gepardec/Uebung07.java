package com.gepardec;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.openrewrite.*;
import org.openrewrite.java.JavaIsoVisitor;
import org.openrewrite.java.JavaTemplate;
import org.openrewrite.java.tree.J;
import org.openrewrite.java.tree.Statement;

public class Uebung07 extends Recipe {
    @Option
    String methodName;

    @Option
    String argumentName;

    transient JavaTemplate nullCheck;

    public Uebung07(@JsonProperty("methodName") String methodName, @JsonProperty("argumentName") String argumentName) {
        this.methodName = methodName;
        this.argumentName = argumentName;
        this.nullCheck = JavaTemplate.builder("""
                    if (%s == null) {
                        return;
                    }
                    """.formatted(argumentName))
                .contextSensitive()
                .build();
    }

    @Override
    public @NlsRewrite.DisplayName String getDisplayName() {
        return "";
    }

    @Override
    public @NlsRewrite.Description String getDescription() {
        return "hallo.";
    }

    @Override
    public TreeVisitor<?, ExecutionContext> getVisitor() {
        return new JavaIsoVisitor<>() {
            @Override
            public J.MethodDeclaration visitMethodDeclaration(J.MethodDeclaration method, ExecutionContext executionContext) {
                method = super.visitMethodDeclaration(method, executionContext);
                if (!method.getSimpleName().equals(methodName)) {
                    return method;
                }
                if (method.getParameters()
                        .stream()
                        .map(J.VariableDeclarations.class::cast)
                        .noneMatch(p -> p.getVariables().getFirst().getSimpleName().equals(argumentName))) {
                    return method;
                }

                Statement firstStatement = method.getBody().getStatements().get(0);
                if (firstStatement instanceof J.If) {
                    return method;
                }
                Cursor cursor = new Cursor(getCursor(), firstStatement);
                if (nullCheck.matches(cursor)) {
                    return method;
                }

                return nullCheck.apply(updateCursor(method), method.getBody().getCoordinates().firstStatement());
            }
        };
    }
}
