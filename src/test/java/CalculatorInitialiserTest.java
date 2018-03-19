import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CalculatorInitialiserTest {

    private static final double BRIX = 5;
    private static final double TTA = 0.14;


    @Test
    public void shouldBeAbleToSetGoldenDataForBrix() {
        CalculatorInitialiser calculatorInitialiser = CalculatorInitialiser.getInstance(5, 0.14);
        double brixBaseValue = calculatorInitialiser.getBrixBaseValue();
        double ttaBaseValue = calculatorInitialiser.getTtaBaseValue();

        assertThat(brixBaseValue, is(BRIX));
        assertThat(ttaBaseValue, is(TTA));
    }

    public void shouldBeAbleToGetTanksInContainer() {

    }

}
