package domain;

import java.math.BigDecimal;

public interface TankAnalyser {

    /**
     * Calculates the amount needed to match target amount.
     *
     * @return the amount needed to match targer amount
     */
    BigDecimal calculate(PROPERTY_TYPE propertyType);
}
