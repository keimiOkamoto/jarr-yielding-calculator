import domain.KombuchaTank;
import domain.TANK_TYPE;
import domain.Tank;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TTATankAnalyserTest {

    private TTAAnalyser tankAnalyser;

    private DecimalFormat decimalFormat;

    @Before
    public void buildup() {
        decimalFormat = new DecimalFormat("#.#######");

        Tank tank1 = new KombuchaTank(800, 5.2, 0.126);
        Tank tank2 = new KombuchaTank(800, 5.1, 0.134);
        Tank acidifierTank = new KombuchaTank(800, 2.2, 0.852);

        List<Tank> priamaryTanks = Arrays.asList(tank1, tank2);
        List<Tank> acidifierTanks = Collections.singletonList(acidifierTank);

        Map<TANK_TYPE, List<Tank>> tanks = new HashMap<>();
        tanks.put(TANK_TYPE.PRIMARY, priamaryTanks);
        tanks.put(TANK_TYPE.ACIDIFIER, acidifierTanks);

        TankContainer.INSTANCE.setTanks(tanks);
        tankAnalyser = new TTAAnalyser(priamaryTanks, acidifierTank);
    }

    @Test
    public void shouldBeAbleToGetTotalYieldVolumeOfTanks() {
        double expectedTotalVolume = 1600;

        double actualTotalVolume = tankAnalyser.getTotalVolume();

        assertThat(actualTotalVolume, is(expectedTotalVolume));
    }

    @Test
    public void shouldBeAbleToGetAverageOfTta() {
        double expectedAverageTta = 0.13;

        double actualAverageTta = tankAnalyser.getAverage();

        assertThat(actualAverageTta, is(expectedAverageTta));
    }

    @Test
    public void shouldBeAbleToGetAcidity() {
        double expectedAcidity = 0.01;

        double acidity = tankAnalyser.getAcidityDifference();

        String actualAcidity = decimalFormat.format(acidity);

        assertThat(Double.parseDouble(actualAcidity), is(expectedAcidity));
    }

    @Test
    public void shouldBeAbleToGetAcidifierTTAFactorVsBlend() {
        double expectedAcidifierTTALevelVsBlends = 6.5538462;

        double acidifierTTALevelVsBlends = tankAnalyser.getAcidifierTTALevelVsBlends();

        double actualAcidifierTTALevelVsBlends = Double.parseDouble(decimalFormat.format(acidifierTTALevelVsBlends));

        assertThat(actualAcidifierTTALevelVsBlends, is(expectedAcidifierTTALevelVsBlends));
    }

    @Test
    public void shouldBeAleToGetAcidifierTTAFactorVsBlend() {
        double expectedAcidifierTTALevelVsBlends = 244.1314554;

        double litresOfAcidifierTTAEquivalentToTTAofPrimary = tankAnalyser.getLitresOfAcidifierEquivalentToTTAofBlend();

        double actualLitresOfAcidifierTTAEquivalentToTTAofPrimary = Double.parseDouble(decimalFormat.format(litresOfAcidifierTTAEquivalentToTTAofPrimary));

        assertThat(actualLitresOfAcidifierTTAEquivalentToTTAofPrimary, is(expectedAcidifierTTALevelVsBlends));
    }

    @Test
    public void shouldBeAbleToGetPercentageIncreaseNeededOfTTA() {
        double expectedPercentageIncreaseOfTTA = 13;

        double percentageIncreaseOfTTA = tankAnalyser.getPercentageIncreaseOfRequiredTTA();

        double actualPercentageIncreaseOfTTA = Double.parseDouble(decimalFormat.format(percentageIncreaseOfTTA));

        assertThat(actualPercentageIncreaseOfTTA, is(expectedPercentageIncreaseOfTTA));
    }

    @Test
    public void shouldBeAbleToGetLitresOfAcidifierRequiredToMatchBaseAcidity() {
        double expectedLitresOfAcidifierRequiredToMatchBaseAcidity = 31.7370892;

        double litresOfAcidifierRequiredToMatchBaseAcidity = tankAnalyser.getLitresOfAcidifierRequiredToMatchBaseAcidity();

        double actualLitresOfAcidifierRequiredToMatchBaseAcidity = Double.parseDouble(decimalFormat.format(litresOfAcidifierRequiredToMatchBaseAcidity));

        assertThat(actualLitresOfAcidifierRequiredToMatchBaseAcidity, is(expectedLitresOfAcidifierRequiredToMatchBaseAcidity));
    }

    @Test
    public void shouldBeAbleToCalculateAmountOfTTANeededForOptimalBlend() {
        double expectedTTA = 31.7370892;

        double tTA = tankAnalyser.calculate();

        double actualTTA = Double.parseDouble(decimalFormat.format(tTA));

        assertThat(actualTTA, is(expectedTTA));
    }
}