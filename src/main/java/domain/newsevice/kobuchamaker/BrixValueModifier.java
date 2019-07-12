package domain.newsevice.kobuchamaker;

import domain.Tank;
import domain.newsevice.PropertyManager.KombuchaPropertyAdder;
import domain.newsevice.factory.KombuchaBlendFactory;
import domain.newsevice.propertycalculators.KombuchaPropertyCalculator;

import java.util.List;

public class BrixValueModifier implements KombuchaModifier {
    private Tank blend;
    private KombuchaPropertyCalculator kombuchaPropertyCalculator;
    private KombuchaPropertyAdder kombuchaPropertyAdder;

    public BrixValueModifier(List<Tank> blend, KombuchaPropertyCalculator kombuchaPropertyCalculator, KombuchaPropertyAdder kombuchaPropertyAdder) {
        this.blend = KombuchaBlendFactory.INSTANCE.getInitialTank(blend);
        this.kombuchaPropertyCalculator = kombuchaPropertyCalculator;
        this.kombuchaPropertyAdder = kombuchaPropertyAdder;
    }

    public Tank modifyValue() {
        double kgs = kombuchaPropertyCalculator.calculate(blend);
        return kombuchaPropertyAdder.add(kgs, blend);
    }
}
