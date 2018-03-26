package domain;

public interface TankAnalyser {

    /**
     * Calculates the amount needed to match target amount.
     *
     * @return the amount needed to match targer amount
     */
    double analyse(PROPERTY_TYPE propertyType);
}
