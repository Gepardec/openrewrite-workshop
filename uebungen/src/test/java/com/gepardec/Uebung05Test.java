package com.gepardec;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;

public class Uebung05Test implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new Uebung05());
    }

    @Test
    public void testCodeForLSTPrint() {
        rewriteRun(
                //language=java
                java("""
                        package com.gepardec;
                        
                        public class Uebung05Test {
                            @Deprecated
                            public void test(String argument) {
                                boolean test = argument.isBlank();
                                if (test) {
                                    System.out.println(argument);
                                } else {
                                    System.out.println("test");
                                }
                            }
                        }
                        """
                )
        );
    }
}
