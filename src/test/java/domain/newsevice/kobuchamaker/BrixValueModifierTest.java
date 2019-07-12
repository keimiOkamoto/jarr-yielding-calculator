package domain.newsevice.kobuchamaker;

import domain.Tank;
import domain.newsevice.propertycalculators.BrixCalculator;
import domain.newsevice.propertycalculators.KombuchaPropertyCalculator;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class BrixValueModifierTest {
    @Mock
    private KombuchaPropertyCalculator kombuchaPropertyCalculator = mock(BrixCalculator.class);

    private BrixValueModifier brixValueModifier = new BrixValueModifier(kombuchaPropertyCalculator);
    @Test
    public void shouldBeAbleToGetAmountOfBrixNeededToReachTarget() {
        Tank tank = brixValueModifier.modifyValue();
        assertThat(tank.getBrixValue(), is(5.0));
    }
}