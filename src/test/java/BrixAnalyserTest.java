import domain.KombuchaTank;
import domain.TANK_TYPE;
import domain.Tank;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BrixAnalyserTest {

    private BrixAnalyser tankAnalyser;

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
        TTAAnalyser ttaAnalyser = new TTAAnalyser(priamaryTanks, acidifierTank);
        tankAnalyser = new BrixAnalyser(ttaAnalyser.calculate(), priamaryTanks, acidifierTank);
    }


    @Test
    public void shouldBeAbleToGetAverageOfBrix() {
        double expectedAverageBrix = 5.15;

        double actualAverageBrix = tankAnalyser.getAverage();

        assertThat(actualAverageBrix, is(expectedAverageBrix));
    }

    @Test
    public void shouldBeAbleToGetBrixAverageMinusTargetBrix() {
        double expectedBrix = 0.15;

        double brix = tankAnalyser.getBrixDifference();

        String actualBrix = decimalFormat.format(brix);

        assertThat(Double.parseDouble(actualBrix), is(expectedBrix));
    }

    @Test
    public void shouldBeAbleToGetTargetKGOfSugarInTotalYield() {
        double expectedTargetKGOfSugarInTotalYield = 80;
        double actualTargetKGOfSugarInTotalYield = tankAnalyser.getTargetKGOfSugarInTotalYield();

        assertThat(actualTargetKGOfSugarInTotalYield, is(expectedTargetKGOfSugarInTotalYield));
    }

    @Test
    public void shouldBeAbleToGetQuantityOfSugarInPrimaryTankYield() {
        double expected = 81.5496714;
        double quantityOfSugarInPrimaryTankYield = tankAnalyser.getQuantityOfSugarInPrimaryTankYield();

        double actualQuantityOfSugarInPrimaryTankYield = Double.parseDouble(decimalFormat.format(quantityOfSugarInPrimaryTankYield));

        assertThat(actualQuantityOfSugarInPrimaryTankYield, is(expected));
    }

    @Test
    public void shouldBeAbleToGetQuantityOfSugarInAcidifier() {
        double expected = 0.698216;
        double quantityOfSugarInAcidifier = tankAnalyser.getQuantityOfSugarInAcidifier();

        double actualQuantityOfSugarInAcidifier = Double.parseDouble(decimalFormat.format(quantityOfSugarInAcidifier));

        assertThat(actualQuantityOfSugarInAcidifier, is(expected));
    }

    @Test
    public void shouldBeAbleToGetTotalQuantityOfSugarInYield() {
        double expected = 82.2478873;
        double totalQuantityOfSugarInYield = tankAnalyser.getTotalQuantityOfSugarInYield();

        double actualTotalQuantityOfSugarInYield = Double.parseDouble(decimalFormat.format(totalQuantityOfSugarInYield));

        assertThat(actualTotalQuantityOfSugarInYield, is(expected));
    }

    @Test
    public void shouldBeAbleToGetAmountOfSugarToBeAddedOrTakenAway() {
        double expected = 2.2478873;
        double amountOfSugarToBeAddedOrTakenAway = tankAnalyser.getAmountOfSugarToBeAddedOrTakenAway();

        double actualAmountOfSugarToBeAddedOrTakenAway = Double.parseDouble(decimalFormat.format(amountOfSugarToBeAddedOrTakenAway));

        assertThat(actualAmountOfSugarToBeAddedOrTakenAway, is(expected));
    }
}