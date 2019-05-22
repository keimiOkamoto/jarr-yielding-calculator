import domain.KombuchaTank;
import domain.Tank;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ReporterTest {
    static {
        TWO_TANKS = Arrays.asList(
                new KombuchaTank(800, 4.9, 0.188),
                new KombuchaTank(800, 4.8, 0.160));

        THREE_TANKS = Arrays.asList(
                new KombuchaTank(500, 5.1, 0.124),
                new KombuchaTank(300, 5.1, 0.108),
                new KombuchaTank(800, 5.3, 0.104));
    }

    private Map<Integer, BrewPropertyAdjuster> generateBlendWithTankNumberOf;

    private static List<Tank> TWO_TANKS;
    private static List<Tank> THREE_TANKS;

    private Reporter reporter = new Reporter(new BlendMetricsCalculator());

    @Test
    public void shouldBeAbleToGetAmountOfWaterNeededToReachTargetTta() {
        double litres = reporter.getWaterToReachTargetTta(getBlendWith(TWO_TANKS));
        assertThat(litres, is(312.0));
    }

    @Test
    public void shouldBeAbleToGetAmountOfSugarNeededToReachTargetBrix() {
        double litres = reporter.getSugarToReachTargetBrix(getBlendWith(TWO_TANKS));
        assertThat(litres, is(17.6));
    }

    @Test
    public void shouldBeAbleToGetAmountOfWaterNeededToReachTargetBrix() {
        double litres = reporter.getWaterToReachTargetBrix(getBlendWith(THREE_TANKS));
        assertThat(litres, is(51.20));
    }

    @Test
    public void shouldBeAbleToGetAmoundOfAcidNeededToReachTargetTta() {
        double litres = reporter.getAcidToReachTargetTta(getBlendWith(THREE_TANKS));
        assertThat(litres, is(0.0));
    }

    private Tank getBlendWith(List<Tank> tanks) {
        Double ttaValue = tanks.stream().collect(Collectors.averagingDouble(Tank::getTtaValue));
        Double brixValue = tanks.stream().collect(Collectors.averagingDouble(Tank::getBrixValue));
        return new KombuchaTank(800, brixValue, ttaValue);
    }

}
