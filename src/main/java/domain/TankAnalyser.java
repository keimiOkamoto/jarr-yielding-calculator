package domain;

import domain.PROPERTY_TYPE;

public interface TankAnalyser {

    /**
     * Calculates the amount needed to match target amount.
     *
     * @return the amount needed to match targer amount
     */
    double calculate(PROPERTY_TYPE propertyType);
}