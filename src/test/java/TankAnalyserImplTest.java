//import domain.*;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.rules.ExpectedException;
//
//import java.math.BigDecimal;
//import java.math.MathContext;
//import java.text.DecimalFormat;
//import java.util.*;
//
//import static org.hamcrest.CoreMatchers.is;
//import static org.junit.Assert.*;
//
//public class TankAnalyserImplTest {
//
//    private TankAnalyserImpl tankAnalyser;
//
//    private MathContext mathContext;
//
//    @Before
//    public void buildup() {
//        mathContext = new MathContext(4);
//        Tank tank1 = new KombuchaTank(new BigDecimal(800), new BigDecimal(5.2), new BigDecimal(0.126));
//        Tank tank2 = new KombuchaTank(new BigDecimal(800), new BigDecimal(5.1), new BigDecimal(0.134));
//
//        Tank acidifierTank = new KombuchaTank(new BigDecimal(800), new BigDecimal(2.2), new BigDecimal(0.852));
//
//        List<Tank> priamaryTanks = Arrays.asList(tank1, tank2);
//        List<Tank> acidifierTanks = Collections.singletonList(acidifierTank);
//
//        Map<TANK_TYPE, List<Tank>> tanks = new HashMap<>();
//        tanks.put(TANK_TYPE.PRIMARY, priamaryTanks);
//        tanks.put(TANK_TYPE.ACIDIFIER, acidifierTanks);
//
//        TankContainer.INSTANCE.setTanks(tanks);
//
//        tankAnalyser = new TankAnalyserImpl(TankContainer.INSTANCE);
//    }
//
//    @Test
//    public void shouldBeAbleToCalculateAmountOfTTANeededForOptimalBlend() {
//        BigDecimal expectedTTA = new BigDecimal(31.7370892);
//
//        BigDecimal actualTTA = tankAnalyser.calculate(PROPERTY_TYPE.TTA);
//
//        assertThat(actualTTA.round(mathContext), is(expectedTTA.round(mathContext)));
//    }
//
//    @Test
//    public void shouldBeAbleToCalculateAmountOfBrixNeededForOptimalBlend() {
//        BigDecimal expectedBrix = new BigDecimal(2.2478873);
//
//        BigDecimal actualBrix = tankAnalyser.calculate(PROPERTY_TYPE.BRIX);
//
//        assertThat(actualBrix.round(mathContext), is(expectedBrix.round(mathContext)));
//    }
//
//    @Rule
//    public ExpectedException expected = ExpectedException.none();
//
//    @Test
//    public void shouldThrowUnsuportedExectionIfTypeDoesNotExist() throws Exception {
//        expected.expect(UnsupportedOperationException.class);
//        expected.expectMessage("Property type is invalid. Valid types include: TTA, BRIX");
//        tankAnalyser.calculate(null);
//    }
//}