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

    private TankAnalyser containerCalculator;

    @Before
    public void buildup() {
        Tank tank1 = new KombuchaTank(800, 5.2, 0.126);
        Tank tank2 = new KombuchaTank(800, 5.1, 0.134);
        Tank acidifierTank = new KombuchaTank(800, 2.2, 0.852);

        List<Tank> priamaryTanks = Arrays.asList(tank1, tank2);
        List<Tank> acidifierTanks = Collections.singletonList(acidifierTank);

        Map<TANK_TYPE, List<Tank>> tanks = new HashMap<>();
        tanks.put(TANK_TYPE.PRIMARY, priamaryTanks);
        tanks.put(TANK_TYPE.ACIDIFIER, acidifierTanks);

        TankContainer.INSTANCE.setTanks(tanks);
        containerCalculator = new TankAnalyserImpl();
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

        double actualAverageBrix = containerCalculator.getAverageBrix();

        assertThat(actualAverageBrix, is(expectedAverageBrix));
    }

    @Test
    public void shouldBeAbleToGetAverageOfTta() {
        double expectedAverageTta = 0.13;

        double actualAverageTta = containerCalculator.getAverageTta();


        assertThat(actualAverageTta, is(expectedAverageTta));
    }

    @Test
    public void shouldBeAbleToGetAcidity() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        decimalFormat.setRoundingMode(RoundingMode.HALF_DOWN);

        double expectedAcidity = 0.01;

        double acidity = containerCalculator.getAcidityDifference();

        String actualAcidity = decimalFormat.format(acidity);

        assertThat(Double.parseDouble(actualAcidity), is(expectedAcidity));
    }

    @Test
    public void shouldBeAbleToGetAcidifierTTAFactorVsBlend() {
        DecimalFormat decimalFormat = new DecimalFormat("#.########");
        decimalFormat.setRoundingMode(RoundingMode.HALF_DOWN);

        double expectedAcidifierTTALevelVsBlends = 6.55384615;

        double acidifierTTALevelVsBlends = containerCalculator.getAcidifierTTALevelVsBlends();

        String actualAcidifierTTALevelVsBlends = decimalFormat.format(acidifierTTALevelVsBlends);

        assertThat(Double.parseDouble(actualAcidifierTTALevelVsBlends), is(expectedAcidifierTTALevelVsBlends));
    }

}