import domain.KombuchaTank;
import domain.Report;
import domain.Tank;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BlendGeneratorTest {
    private static List<Tank> tanksThree;
    private static List<Tank> tanksTwo;

    static {
        tanksThree = Arrays.asList(
                new KombuchaTank(500, 5.1, 0.124),
                new KombuchaTank(300, 5.1, 0.108),
                new KombuchaTank(800, 5.3, 0.104));

        tanksTwo = Arrays.asList(
                new KombuchaTank(800, 4.9, 0.188),
                new KombuchaTank(800, 4.8, 0.160));
    }

    private Map<Integer, BlendGenerator> generateBlendWithTankNumberOf;

    @Before
    public void setup() {
        generateBlendWithTankNumberOf = Map.of(
                2, getBlendGenerator(tanksTwo),
                3, getBlendGenerator(tanksThree));
    }

    private BlendGenerator getBlendGenerator(List<Tank> tanks) {
        Map<TANK_TYPE, List<Tank>> mapOfTanks = Map.of(
                        TANK_TYPE.BASE, tanks,
                        TANK_TYPE.BLEND, Collections.singletonList(new KombuchaTank(0.0, 0.0, 0.0)),
                        TANK_TYPE.ACIDIFIER, Collections.singletonList(new KombuchaTank(800, 3.5, 0.18)));

        BlendAnalyser blendAnalyser = new BlendAnalyser(mapOfTanks);
        Report report = new Report();
        return new BlendGenerator(blendAnalyser, report);
    }

    @Test
    public void shouldGetLitresOfWaterNeededToReachTargetTta() {
        double actualLitresOfWaterNeededToReachTargetBrix = generateBlendWithTankNumberOf.get(2).getLitresOfWaterNeededToReachTargetTta();

        assertThat(actualLitresOfWaterNeededToReachTargetBrix, is(312.64));
    }

    @Test
    public void shouldGetLitresOfWaterNeededToReachTargetBrix() {
        double actualLitresOfWaterNeededToReachTargetBrix = generateBlendWithTankNumberOf.get(3).getLitresOfWaterNeededToReachTargetBrix();

        assertThat(actualLitresOfWaterNeededToReachTargetBrix, is(51.61));
    }

}