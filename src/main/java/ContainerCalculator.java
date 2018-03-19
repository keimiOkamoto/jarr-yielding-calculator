public interface ContainerCalculator {

    double getTotalVolume();

    double getAverageBrix();

    double getAverageTta();

    /**
     * Acidity is calculated by minusing the average TTA value from the target TTA value.
     *
     * @return the difference in acidity from the target acidity.
     */
    double getAcidityDifference();

    /**
     * Acidifier TTA level divided by the average of the primary tanks will yield this value,
     *
     * @return the acidifiers TTA level divided by the average TTa level of the primary tanks.
     */
    double getAcidifierTTALevelVsBlends();
}
