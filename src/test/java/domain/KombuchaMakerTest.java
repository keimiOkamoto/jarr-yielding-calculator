//package domain;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mock;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;
//
//public class KombuchaMakerTest {
//    @Mock
//    private KombuchaManager blendPropertyManager = mock(BlendPropertyManager.class);
//
//    @Mock
//    private Calculator calculator = mock(BlendMetricsCalculator.class);
//
//    @Mock
//    private Tank tank = mock(KombuchaTank.class);
//
//
//    static {
//        THREE_TANKS = Arrays.asList(
//                new KombuchaTank(500, 5.1, 0.124),
//                new KombuchaTank(300, 5.1, 0.108),
//                new KombuchaTank(800, 5.3, 0.104));
//
//        TWO_TANKS = Arrays.asList(
//                new KombuchaTank(800, 4.9, 0.188),
//                new KombuchaTank(800, 4.8, 0.160));
//    }
//
//    private static List<Tank> TWO_TANKS;
//    private static List<Tank> THREE_TANKS;
//
//    private Map<TANK_TYPE, List<Tank>> mapOfTanks;
//    public KombuchaMaker kombuchaMaker;
//
//    @Before
//    public void setup() {
//        kombuchaMaker = new KombuchaMaker(blendPropertyManager, calculator, mapOfTanks);
//    }
//
//    private Tank getTank(double volume, double brix, double tta) {
//        return new KombuchaTank(volume, brix, tta);
//    }
//
//
//    @Test
//    public void shouldCreateInitialTank() {
//        when(blendPropertyManager.getDefaultBlend()).thenReturn(tank);
//        kombuchaMaker.createInitialTank();
//
//        verify(blendPropertyManager, times(1)).getDefaultBlend();
//    }
//
//    @Test
//    public void shouldAddWater() {
//        double percentageIncrease = 19.0;
//
//        when(blendPropertyManager.getBlend()).thenReturn(tank);
//        when(calculator.getPercentageWaterNeededToReachTargetBrix(tank)).thenReturn(percentageIncrease);
//
//        kombuchaMaker.addWater();
//
//        verify(blendPropertyManager, times(1)).getBlend();
//        verify(calculator, times(1)).getPercentageWaterNeededToReachTargetTta(tank);
//        verify(blendPropertyManager, times(1)).addWaterBy(percentageIncrease);
//    }
//
//    @Test
//    public void addSugar() {
//        kombuchaMaker.addSugar();
//    }
//
//    @Test
//    public void addAcid() {
//    }
//}