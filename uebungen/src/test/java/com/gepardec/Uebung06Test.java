package com.gepardec;

import org.junit.jupiter.api.Test;
import org.openrewrite.test.RecipeSpec;
import org.openrewrite.test.RewriteTest;

import static org.openrewrite.java.Assertions.java;

public class Uebung06Test implements RewriteTest {

    @Override
    public void defaults(RecipeSpec spec) {
        spec.recipe(new Uebung06());
    }

    @Test
    public void givenJava_whenMultipleAnnotationsContainingDeprecated_removeDeprecated() {
        rewriteRun(
                //language=java
                java("""
                        package com.gepardec;
                        
                        public class Uebung06Test {
                            @SuppressWarnings("unchecked") @Deprecated
                            public void method(String argument) {}
                        }
                        """, """
                        package com.gepardec;
                        
                        public class Uebung06Test {
                            @SuppressWarnings("unchecked")
                            public void method(String argument) {}
                        }
                        """
                )
        );
    }

    @Test
    public void givenJava_whenClassNotUebung06Test_thenDoNothing() {
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
