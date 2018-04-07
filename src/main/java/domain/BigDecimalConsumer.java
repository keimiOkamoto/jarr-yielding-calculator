package domain;

import java.math.BigDecimal;

@FunctionalInterface
public interface BigDecimalConsumer {

    void accept(BigDecimal value);
}
