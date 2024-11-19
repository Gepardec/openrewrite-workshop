package com.gepardec;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.maven.Assertions.pomXml;

public class Uebung03Test implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipeFromResources("com.gepardec.Uebung03");
    }

    @Test
    public void givenPom_whenNoCollectionDependencies_thenAddInject() {
        rewriteRun(
                pomXml(
                        """
                            <project>
                                <groupId>com.gepardec</groupId>
                                <artifactId>uebung03</artifactId>
                                <version>1.0.0</version>
                            </project>""", """
                            <project>
                                <groupId>com.gepardec</groupId>
                                <artifactId>uebung03</artifactId>
                                <version>1.0.0</version>
                                <dependencies>
                                    <dependency>
                                        <groupId>jakarta.inject</groupId>
                                        <artifactId>jakarta.inject-api</artifactId>
                                        <version>2.0.1</version>
                                        <scope>provided</scope>
                                    </dependency>
                                </dependencies>
                            </project>"""
                )
        );
    }
    @Test
    public void givenPom_whenJakartaEEApiDependency_thenChangeNothing() {
        rewriteRun(
                pomXml(
                        """
                            <project>
                                <groupId>com.gepardec</groupId>
                                <artifactId>uebung03</artifactId>
                                <version>1.0.0</version>
                                <dependencies>
                                    <dependency>
                                        <groupId>jakarta.platform</groupId>
                                        <artifactId>jakarta.jakartaee-api</artifactId>
                                        <version>10.0.0</version>
                                        <scope>provided</scope>
                                    </dependency>
                                </dependencies>
                            </project>"""
                )
        );
    }

    @Test
    public void givenPom_whenJakartaEECoreApiDependency_thenChangeNothing() {
        rewriteRun(
                pomXml(
                        """
                            <project>
                                <groupId>com.gepardec</groupId>
                                <artifactId>uebung03</artifactId>
                                <version>1.0.0</version>
                                <dependencies>
                                    <dependency>
                                        <groupId>jakarta.platform</groupId>
                                        <artifactId>jakarta.jakartaee-core-api</artifactId>
                                        <version>10.0.0</version>
                                        <scope>provided</scope>
                                    </dependency>
                                </dependencies>
                            </project>"""
                )
        );
    }
}
