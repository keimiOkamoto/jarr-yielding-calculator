import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalDouble;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ContainerCalculatorTest {


    private ContainerCalculator containerCalculator;

    @Before
    public void buildup() {
        Tank tank1 = new KombuchaTank(800, 5.2, 0.126);
        Tank tank2 = new KombuchaTank(800, 5.1, 0.134);
        List<Tank> tanks = Arrays.asList(tank1, tank2);
        TankContainer.INSTANCE.setTanks(tanks);
        containerCalculator = new ContainerCalculatorImpl();
    }

    @Test
    public void shouldBeAbleToGetTotalYieldVolumeOfTanks() {
        double expectedTotalVolume = 1600;

        double actualTotalVolume = containerCalculator.getTotalVolume();

        assertThat(actualTotalVolume, is(expectedTotalVolume));
    }

    @Test
    public void shouldBeAbleToGetAverageOfBrix() {
        double expectedAverageBrix = 5.15;

        OptionalDouble actualAverageBrix = containerCalculator.getAverageBrix();

        assertThat(actualAverageBrix.getAsDouble(), is(expectedAverageBrix));
    }

    @Test
    public void shouldBeAbleToGetAverageOfTta() {
        double expectedAverageTta = 0.13;

        OptionalDouble actualAverageTta = containerCalculator.getAverageTta();

        assertThat(actualAverageTta.getAsDouble(), is(expectedAverageTta));
    }

}