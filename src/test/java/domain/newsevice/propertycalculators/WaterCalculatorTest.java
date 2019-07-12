package domain.newsevice.propertycalculators;

import domain.KombuchaTank;
import domain.Tank;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WaterCalculatorTest {
 private WaterCalculator waterCalculator = new WaterCalculator();

    @Test
    public void shouldCalculatePercentageOfWaterNeededToReachTargetTta() {
        Tank blend = new KombuchaTank(800, 3.9, 0.174);
        double actualLitres = waterCalculator.calculate(blend);

        assertThat(actualLitres, is(19.5));
    }
}