package domain.newsevice.PropertyManager;

import domain.KombuchaTank;
import domain.Tank;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WaterAdderTest {
    private WaterAdder waterAdder = new WaterAdder();

    @Test
    public void shouldAddBrixToBlendAndAdjustKombuchaProperties() {
        Tank blend = new KombuchaTank(800, 3.9, 0.174);
        Tank actual = waterAdder.add(19.5, blend);

        assertThat(actual.getTtaValue(), is(0.14));
    }
}