package domain.newsevice.propertycalculators;

import domain.KombuchaTank;
import domain.Tank;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BrixCalculatorTest {

    private BrixCalculator brixCalculator = new BrixCalculator();

    @Test
    public void shouldCalculateKilogramsOfSugarNeededToReachTarget() {
        Tank blend = new KombuchaTank(800, 3.9, 0.174);
        double actualKgs = brixCalculator.calculate(blend);

        assertThat(actualKgs, is(17.6));
    }
}