package cucumber.runtime.java8.test;

import static org.junit.Assert.assertEquals;

import cucumber.api.java8.GlueBase;
import cucumber.api.java8.StepdefBody;
import cucumber.runtime.StepDefinition;
import cucumber.runtime.java.Function;
import cucumber.runtime.java.JavaBackend;
import cucumber.runtime.java8.Java8StepDefinition;
import io.cucumber.cucumberexpressions.ParameterTypeRegistry;

public class AnonInnerClassStepdefs implements GlueBase {

    public AnonInnerClassStepdefs() {
        JavaBackend.INSTANCE.get().addStepDefinition(new Function<ParameterTypeRegistry, StepDefinition>() {
            @Override
            public StepDefinition apply(ParameterTypeRegistry parameterTypeRegistry) {
                return new Java8StepDefinition(
                    "I have {int} java7 beans in my {word}", 0, StepdefBody.A2.class,
                    new StepdefBody.A2<Integer, String>() {
                        @Override
                        public void accept(Integer cukes, String what) throws Throwable {
                            assertEquals(42, cukes.intValue());
                            assertEquals("belly", what);
                        }
                    }, parameterTypeRegistry);
            }
        });
    }
}