package domain.newsevice.factory;

import domain.KombuchaTank;
import domain.Tank;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class KombuchaBlendFactoryTest {

    @Test
    public void shouldCreateADefaultBlend() {
        List<Tank> input = Arrays.asList(new KombuchaTank(800, 4.9, 0.188),
                                         new KombuchaTank(800, 4.8, 0.160));
        Tank initialTank = KombuchaBlendFactory.INSTANCE.getInitialTank(input);

        assertThat(initialTank, is(new KombuchaTank(800, 4.85, 0.174)));
    }
}