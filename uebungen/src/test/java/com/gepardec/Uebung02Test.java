package com.gepardec;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.openrewrite.InMemoryExecutionContext;
import org.openrewrite.java.JavaParser;
import org.openrewrite.maven.MavenExecutionContextView;
import org.openrewrite.maven.tree.MavenRepository;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;
import static org.openrewrite.maven.Assertions.pomXml;
import static org.openrewrite.xml.Assertions.xml;

public class Uebung02Test implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipeFromResources("com.gepardec.Uebung02")
                .parser(JavaParser.fromJavaVersion().dependsOn(
                        """
                                package com.ext.printer;
                                                
                                public class HelloWorldPrinter {
                                    public void print() {
                                        System.out.println("Hello world!");
                                    }
                                }
                                """, """
                                package com.gepardec.printer;
                                                
                                public class GepardecPrinter {
                                    public void print() {
                                        System.out.println("Hello Gepardec!");
                                    }
                                }
                                """
                ));
        addLocalRepo(spec);
    }

    @Test
    public void givenJava_whenHelloWorldPrinterUsage_thenChangeToGepardecPrinter() {
        rewriteRun(
                java("""
                    package com.gepardec;
                    
                    import com.ext.printer.HelloWorldPrinter;
                    
                    public class Main {
                        public static void main(String[] args) {
                            HelloWorldPrinter printer = new HelloWorldPrinter();
                            printer.print();
                        }
                    }
                    """, """
                    package com.gepardec;
    
                    import com.gepardec.printer.GepardecPrinter;
    
                    public class Main {
                        public static void main(String[] args) {
                            GepardecPrinter printer = new GepardecPrinter();
                            printer.print();
                        }
                    }
                """
                )
        );
    }

    @Test
    public void givenPom_whenExtDependency_thenChangePrinterDependencyAndManageNewDependency() {
        rewriteRun(
                pomXml(
                        """
                                <project>
                                    <groupId>com.gepardec</groupId>
                                    <artifactId>rewrite-test</artifactId>
                                    <version>1</version>
                                    <dependencies>
                                        <dependency>
                                            <groupId>com.ext</groupId>
                                            <artifactId>printer</artifactId>
                                            <version>2.1.0</version>
                                        </dependency>
                                    </dependencies>
                                </project>
                                """, """
                                <project>
                                    <groupId>com.gepardec</groupId>
                                    <artifactId>rewrite-test</artifactId>
                                    <version>1</version>
                                    <dependencyManagement>
                                        <dependencies>
                                            <dependency>
                                                <groupId>com.gepardec</groupId>
                                                <artifactId>printer</artifactId>
                                                <version>1.0.0</version>
                                            </dependency>
                                        </dependencies>
                                    </dependencyManagement>
                                    <dependencies>
                                        <dependency>
                                            <groupId>com.gepardec</groupId>
                                            <artifactId>printer</artifactId>
                                        </dependency>
                                    </dependencies>
                                </project>
                """
                )
        );
    }

    private  @NotNull RecipeSpec addLocalRepo(RecipeSpec spec) {
        String mavenRepo = "file:" + this.getClass().getResource("/maven-repo/").getPath();
        return spec.executionContext(new MavenExecutionContextView(new InMemoryExecutionContext()).setLocalRepository(new MavenRepository("local-test", mavenRepo, null, null, null, null, null)));
    }
}
