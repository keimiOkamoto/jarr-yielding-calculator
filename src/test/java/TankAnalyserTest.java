import domain.KombuchaTank;
import domain.Tank;
import org.junit.Before;
import org.junit.Test;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TankAnalyserTest {

    private TankAnalyser tankAnalyser;

    private DecimalFormat decimalFormat;

    @Before
    public void buildup() {
        decimalFormat = new DecimalFormat("#.#######");
//        decimalFormat.setRoundingMode(RoundingMode.HALF_DOWN);

        Tank tank1 = new KombuchaTank(800, 5.2, 0.126);
        Tank tank2 = new KombuchaTank(800, 5.1, 0.134);
        Tank acidifierTank = new KombuchaTank(800, 2.2, 0.852);

        List<Tank> priamaryTanks = Arrays.asList(tank1, tank2);
        List<Tank> acidifierTanks = Collections.singletonList(acidifierTank);

        Map<TANK_TYPE, List<Tank>> tanks = new HashMap<>();
        tanks.put(TANK_TYPE.PRIMARY, priamaryTanks);
        tanks.put(TANK_TYPE.ACIDIFIER, acidifierTanks);

        TankContainer.INSTANCE.setTanks(tanks);
        tankAnalyser = new TankAnalyserImpl();
    }

    @Test
    public void shouldBeAbleToGetTotalYieldVolumeOfTanks() {
        double expectedTotalVolume = 1600;

        double actualTotalVolume = tankAnalyser.getTotalVolume();

        assertThat(actualTotalVolume, is(expectedTotalVolume));
    }

    @Test
    public void shouldBeAbleToGetAverageOfBrix() {
        double expectedAverageBrix = 5.15;

        double actualAverageBrix = tankAnalyser.getAverageBrix();

        assertThat(actualAverageBrix, is(expectedAverageBrix));
    }

    @Test
    public void shouldBeAbleToGetAverageOfTta() {
        double expectedAverageTta = 0.13;

        double actualAverageTta = tankAnalyser.getAverageTta();

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

        double PercentageIncreaseOfTTA = tankAnalyser.getPercentageIncreaseOfRequiredTTA();

        double actualPercentageIncreaseOfTTA = Double.parseDouble(decimalFormat.format(PercentageIncreaseOfTTA));

        assertThat(actualPercentageIncreaseOfTTA, is(expectedPercentageIncreaseOfTTA));
    }
}