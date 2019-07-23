package domain.newsevice.kobuchamaker;

import domain.Tank;
import domain.newsevice.PropertyManager.BrixAdder;
import domain.newsevice.PropertyManager.KombuchaPropertyAdder;
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
    private KombuchaPropertyAdder kombuchaPropertyAdder = mock(BrixAdder.class);

    @Mock
    private Tank tank = mock(Tank.class);
    private KombuchaPropertyValueManager brixValueModifier = new KombuchaPropertyValueManager(kombuchaPropertyCalculator, kombuchaPropertyAdder);

//    @Test
//    public void shouldBeAbleToGetAmountOfBrixNeededToReachTarget() {
//        Tank tank = brixValueModifier.modifyValue();
//        assertThat(tank.getBrixValue(), is(5.0));
//    }
}