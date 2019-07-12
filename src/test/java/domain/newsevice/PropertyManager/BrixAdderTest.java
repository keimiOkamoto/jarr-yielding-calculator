package domain.newsevice.PropertyManager;

import domain.KombuchaTank;
import domain.Tank;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BrixAdderTest {

    private BrixAdder brixAdder = new BrixAdder();

    @Test
    public void shouldAddBrixToBlendAndAdjustBrixProperties() {
        double expectedBrix = 5.0;
        double sugarKg = 17.6;

        Tank blend = new KombuchaTank(800, 3.9, 0.174);

        Tank actual = brixAdder.add(sugarKg, blend);

        assertThat(actual.getBrixValue(), is(expectedBrix));
    }
}