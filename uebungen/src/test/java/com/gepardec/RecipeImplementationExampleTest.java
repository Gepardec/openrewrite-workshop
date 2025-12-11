package com.gepardec;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;

/**
 * Von allen Methoden der Klasse RecipeImplementationExampleTest, lösche
 * @Deprecated Annotationen
 */

public class RecipeImplementationExampleTest implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new RecipeImplementationExample());
    }

    @Test
    public void givenJava_whenMultipleAnnotationsContainingDeprecated_removeDeprecated() {
        rewriteRun(
                //language=java
                java("""
                        package com.gepardec;
                        
                        public class RecipeImplementationExampleTest {
                            @SuppressWarnings("unchecked") @Deprecated
                            public void method(String argument) {}
                        }
                        """, """
                        package com.gepardec;
                        
                        public class RecipeImplementationExampleTest {
                            @SuppressWarnings("unchecked")
                            public void method(String argument) {}
                        }
                        """
                )
        );
    }

    @Test
    public void givenJava_whenClassNotRecipeImplementationExampleTest_thenDoNothing() {
        rewriteRun(
                //language=java
                java("""
                        package com.gepardec;
                        
                        public class UCantTouchThis {
                            @Deprecated
                            public void test(String argument) {}
                        }
                        """
                )
        );
    }
}
