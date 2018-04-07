package domain;

import java.math.BigDecimal;

public class BigDecimalAverager implements BigDecimalConsumer {

    private BigDecimal total = BigDecimal.ZERO;
    private BigDecimal count = BigDecimal.ZERO;

    public BigDecimal average() {
        return total.divide(count, 4,1);
    }

    @Override
    public void accept(BigDecimal i) {
        total = total.add(i);
        count = count.add(BigDecimal.ONE);
    }

    public void combine(BigDecimalAverager other) {
        total = total.add(other.total);
        count = count.add(other.count);
    }

}
