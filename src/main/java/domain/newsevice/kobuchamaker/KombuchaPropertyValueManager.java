package domain.newsevice.kobuchamaker;

import domain.Tank;
import domain.newsevice.PropertyManager.KombuchaPropertyAdder;
import domain.newsevice.propertycalculators.KombuchaPropertyCalculator;

public class KombuchaPropertyValueManager implements KombuchaPropertyManager {
    private KombuchaPropertyCalculator kombuchaPropertyCalculator;
    private KombuchaPropertyAdder kombuchaPropertyAdder;

    public KombuchaPropertyValueManager(KombuchaPropertyCalculator kombuchaPropertyCalculator, KombuchaPropertyAdder kombuchaPropertyAdder) {
        this.kombuchaPropertyCalculator = kombuchaPropertyCalculator;
        this.kombuchaPropertyAdder = kombuchaPropertyAdder;
    }

    public Tank modifyValue(Tank blend) {
        return kombuchaPropertyAdder.add(kombuchaPropertyCalculator.calculate(blend), blend);
    }
}
