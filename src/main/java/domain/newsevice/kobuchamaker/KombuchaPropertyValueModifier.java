package domain.newsevice.kobuchamaker;

import domain.Tank;
import domain.newsevice.PropertyManager.KombuchaPropertyAdder;
import domain.newsevice.converters.ValueConverter;
import domain.newsevice.propertycalculators.KombuchaPropertyCalculator;

public class KombuchaPropertyValueModifier implements KombuchaPropertyManager {
    private KombuchaPropertyCalculator kombuchaPropertyCalculator;
    private KombuchaPropertyAdder kombuchaPropertyAdder;
    private ValueConverter valueConverter;

    public KombuchaPropertyValueModifier(KombuchaPropertyCalculator kombuchaPropertyCalculator, KombuchaPropertyAdder kombuchaPropertyAdder) {
        this.kombuchaPropertyCalculator = kombuchaPropertyCalculator;
        this.kombuchaPropertyAdder = kombuchaPropertyAdder;
        this.valueConverter = valueConverter;
    }

    public Tank modifyValue(Tank blend) {
        double calculate = kombuchaPropertyCalculator.calculate(blend);
        return kombuchaPropertyAdder.add(calculate, blend);
    }
}
