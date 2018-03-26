import domain.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.text.DecimalFormat;
import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class TankAnalyserImplTest {

    private TankAnalyserImpl tankAnalyser;

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

        tankAnalyser = new TankAnalyserImpl(TankContainer.INSTANCE);
    }

    @Test
    public void shouldBeAbleToCalculateAmountOfTTANeededForOptimalBlend() {
        double expectedTTA = 31.7370892;

        double tTA = tankAnalyser.analyse(PROPERTY_TYPE.TTA);

        double actualTTA = Double.parseDouble(decimalFormat.format(tTA));

        assertThat(actualTTA, is(expectedTTA));
    }

    @Test
    public void shouldBeAbleToCalculateAmountOfBrixNeededForOptimalBlend() {
        double expectedTTA = 2.2478873;

        double brix = tankAnalyser.analyse(PROPERTY_TYPE.BRIX);

        double actualTTA = Double.parseDouble(decimalFormat.format(brix));

        assertThat(actualTTA, is(expectedTTA));
    }


    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void shouldThrowUnsuportedExectionIfTypeDoesNotExist() throws Exception {
        expected.expect(UnsupportedOperationException.class);
        expected.expectMessage("hullo");
        double analyse = tankAnalyser.analyse(null);
    }
}