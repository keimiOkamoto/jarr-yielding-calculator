package domain;

import domain.BlendMetricsCalculator;
import domain.Calculator;
import domain.KombuchaTank;
import domain.Tank;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BlendMetricsCalculatorTest {

    static {
        THREE_TANKS = Arrays.asList(
                new KombuchaTank(500, 5.1, 0.124),
                new KombuchaTank(300, 5.1, 0.108),
                new KombuchaTank(800, 5.3, 0.104));

        TWO_TANKS = Arrays.asList(
                new KombuchaTank(800, 4.9, 0.188),
                new KombuchaTank(800, 4.8, 0.160));
    }

    private static List<Tank> TWO_TANKS;
    private static List<Tank> THREE_TANKS;
    private Calculator calculator;

    @Before
    public void setup() {
        calculator = new BlendMetricsCalculator();
    }

    @Test
    public void shouldCalculatePercentageOfWaterNeededToReachTargetTta() {
        double actualPercentage = calculator.getPercentageWaterNeededToReachTargetTta(getBlend(TWO_TANKS));
        assertThat(actualPercentage, is(0.195));
    }

    @Test
    public void shouldCalculateKilogramsOfSugarNeededToReachTargetBrix() {
        double actualKilograms = calculator.getKilogramsOfSugarNeededToReachTarget(new KombuchaTank(800, 3.9, 0.14));
        assertThat(actualKilograms, is(17.6));
    }

    @Test
    public void shouldCalculateEquivalentAmountOfAcidToBaseTanks() {
        double actualAcid = calculator.getEquivalentAmountOfAcidInLitres(getTank(800, 3.8, 1.104), getTank(800, 5.0, 0.109), 1600);
        assertThat(actualAcid, is(34.22));
    }

    @Test
    public void shouldCalculatePercentageOfWaterNeededToReachTargetBrix() {
        double actualPercentage = calculator.getPercentageWaterNeededToReachTargetBrix(getBlend(THREE_TANKS), 5.026);
        assertThat(actualPercentage, is(0.027));
    }

    private Tank getBlend(List<Tank> tanks) {
        Double ttaValue = tanks.stream().collect(Collectors.averagingDouble(Tank::getTtaValue));
        Double brixValue = tanks.stream().collect(Collectors.averagingDouble(Tank::getBrixValue));
        return new KombuchaTank(800, brixValue, ttaValue);
    }

    private Tank getTank(double volume, double brix, double tta) {
        return new KombuchaTank(volume, brix, tta);
    }
}
