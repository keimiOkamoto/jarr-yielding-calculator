import domain.KombuchaTank;
import domain.Tank;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class BlendAnalyserTest {

    private BlendAnalyser blendAnalyser;
    private static List TWO_TANKS;

    static {
        TWO_TANKS = Arrays.asList(
                new KombuchaTank(800, 4.9, 0.188),
                new KombuchaTank(800, 4.8, 0.160));
    }

    @Before
    public void setup() {
        Map tanks = Map.of(
                TANK_TYPE.BASE, TWO_TANKS,
                TANK_TYPE.BLEND, Collections.singletonList(new KombuchaTank(0.0, 0.0, 0.0)),
                TANK_TYPE.ACIDIFIER, Collections.singletonList(new KombuchaTank(800, 3.5, 0.18))
        );

        blendAnalyser = new BlendAnalyser(tanks);

    }
    @Test
    public void shouldReturnTrueIfBlendIsTooAcidic() {
    }

    @Test
    public void shouldReturnTrueIfBrixCountIsTooHigh() {
    }

    @Test
    public void shouldGetTotalVolumeOfTanks() {
    }

    @Test
    public void shouldGetDifferenceBetweenTtaValueAndTargetValue() {
    }

    @Test
    public void shouldGetDifferenceFromBrixValueToTargetValue() {
    }

    @Test
    public void shouldGetDifferenceFromBrixTargetToValue() {
    }

    @Test
    public void shouldGetTotalNumberOfTanks() {
    }

    @Test
    public void shouldGetAverageTtaOfAllTanksWhenInitialised() {
    }

    @Test
    public void shouldGetAverageBrixOfAllTanksWhenInitialised() {
    }


    @Test
    public void getAcidTank() {
        Optional<Tank> actualAcidTank = blendAnalyser.getAcidTank();
        assertThat(actualAcidTank.get(), is(new KombuchaTank(800, 3.5, 0.18)));
    }
}