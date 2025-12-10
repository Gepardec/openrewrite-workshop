package com.gepardec;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;

public class RecipeWithArgumentsExampleTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        // Auskommentieren, sobald das Rezept implementiert ist:
        //spec.recipe(new RecipeWithArgumentsExample("test", "argument"));
    }

    @Test
    public void givenJava_whenMethodNameAndParameterNameMatchRecipeParams_thenAddNullCheck() {
        rewriteRun(
                //language=java
                java("""
                        package com.gepardec;
                        
                        public class RecipeWithArgumentsExampleTest {
                            public void test(String argument) {
                                argument = "Input was: " + argument;
                                System.out.println(argument);
                            }
                        }
                        """, """
                        package com.gepardec;
                        
                        public class RecipeWithArgumentsExampleTest {
                            public void test(String argument) {
                                if (argument == null) {
                                    return;
                                }
                                argument = "Input was: " + argument;
                                System.out.println(argument);
                            }
                        }
                        """
                )
        );
    }

    @Test
    public void givenJava_whenMethodNameDoesntMatch_thenDoNothing() {
        rewriteRun(
                //language=java
                java("""
                        package com.gepardec;
                        
                        public class RecipeWithArgumentsExampleTest {
                            @Deprecated
                            public void notSearched(String argument) {
                                argument = "Input was: " + argument;
                                System.out.println(argument);
                            }
                        }
                        """
                )
        );
    }
    @Test
    public void givenJava_whenParameterNameDoesntMatch_thenDoNothing() {
        rewriteRun(
                //language=java
                java("""
                        package com.gepardec;
                        
                        public class RecipeWithArgumentsExampleTest {
                            @Deprecated
                            public void test(String notSearched) {
                                notSearched = "Input was: " + notSearched;
                                System.out.println(notSearched);
                            }
                        }
                        """
                )
        );
    }
}
