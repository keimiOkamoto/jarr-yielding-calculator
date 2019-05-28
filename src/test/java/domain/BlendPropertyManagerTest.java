package domain;

import domain.BlendPropertyManager;
import domain.KombuchaTank;
import domain.TANK_TYPE;
import domain.Tank;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BlendPropertyManagerTest {
    static {
        TWO_TANKS = Arrays.asList(
                new KombuchaTank(800, 4.9, 0.188),
                new KombuchaTank(800, 4.8, 0.160));

        THREE_TANKS = Arrays.asList(
                new KombuchaTank(500, 5.1, 0.124),
                new KombuchaTank(300, 5.1, 0.108),
                new KombuchaTank(800, 5.3, 0.104));
    }

    private Map<Integer, BlendPropertyManager> generateBlendWithTankNumberOf;

    private static List<Tank> TWO_TANKS;
    private static List<Tank> THREE_TANKS;

    @Before
    public void setup() {
        generateBlendWithTankNumberOf = Map.of(
                2, getBlendGenerator(TWO_TANKS),
                3, getBlendGenerator(THREE_TANKS));
    }

    private BlendPropertyManager getBlendGenerator(List<Tank> tanks) {
        Map<TANK_TYPE, List<Tank>> mapOfTanks = Map.of(
                TANK_TYPE.BASE, tanks,
                TANK_TYPE.BLEND, Collections.singletonList(new KombuchaTank(0.0, 0.0, 0.0)),
                TANK_TYPE.ACIDIFIER, Collections.singletonList(new KombuchaTank(800, 3.8, 1.104)));

        return new BlendPropertyManager(new KombuchaTank(800, 0.0, 0.0), mapOfTanks);
    }

    @Test
    public void shouldCreateTheInitialBlendFromTheBaseTanks() {
        BlendPropertyManager blendPropertyManager = generateBlendWithTankNumberOf.get(2);
        blendPropertyManager.getDefaultBlend();

        assertThat(blendPropertyManager.getBlend().getTtaValue(), is(0.174));
        assertThat(blendPropertyManager.getBlend().getBrixValue(), is(4.85));
        assertThat(blendPropertyManager.getBlend().getVolume(), is(800.0));
    }

    @Test
    public void shouldAddWaterToBlendAndRecalibrate() {
        BlendPropertyManager blendPropertyManager = generateBlendWithTankNumberOf.get(2);
        blendPropertyManager.getDefaultBlend();
        blendPropertyManager.addWaterBy(0.195);
        Tank blend = blendPropertyManager.getBlend();

        assertThat(blend.getTtaValue(), is(0.14));
        assertThat(blend.getBrixValue(), is(3.90));
    }

    @Test
    public void shouldAddSugarToBlendAndRecalibrate() {
        BlendPropertyManager blendPropertyManager = generateBlendWithTankNumberOf.get(2);
        blendPropertyManager.getDefaultBlend();
        blendPropertyManager.addWaterBy(0.195);
        blendPropertyManager.addSugarBy(17.6);
        Tank blend = blendPropertyManager.getBlend();

        assertThat(blend.getTtaValue(), is(0.14));
        assertThat(blend.getBrixValue(), is(5.0));
    }

    @Test
    public void shouldAddAcidToBlendAndRecalibrate() {
        BlendPropertyManager blendPropertyManager = generateBlendWithTankNumberOf.get(3);
        blendPropertyManager.addAcidBy(34.192);
        assertThat(blendPropertyManager.getBlend().getBrixValue(), is(5.026));

    }
}
