import domain.TANK_TYPE;
import domain.Tank;

import java.util.List;

public class TTAAnalyser extends TankAnalyser {

    private static final double TARGET_TTA = 0.14;



    public TTAAnalyser(List<Tank> primaryTanks, Tank acidifierTanks) {
        super(primaryTanks, acidifierTanks);
    }

    @Override
    public double calculate() {
        return getLitresOfAcidifierRequiredToMatchBaseAcidity();
    }


    public double getAverage() {
        return primaryTanks.stream()
                .mapToDouble(Tank::getTtaValue)
                .average()
                .getAsDouble();
    }



    /**
     * Acidity is calculated by minusing the average TTA value from the target TTA value.
     *
     * @return the difference in acidity from the target acidity.
     */
    public double getAcidityDifference() {
        return TARGET_TTA - getAverage();
    }

    /**
     * Acidifier TTA level divided by the average of the primary primaryTanks will yield this value,
     *
     * @return the acidifiers TTA level divided by the average TTa level of the primary primaryTanks.
     */
    public double getAcidifierTTALevelVsBlends() {
        return acidifierTanks.getTtaValue() / getAverage();
    }

    /**
     *  The total volume of the primary primaryTanks divided by the acidifier TTA level divided by the
     *  average of the primary tank.
     *
     * @return the total amound of acid per litre equivalent to the acidifier.
     */
    public double getLitresOfAcidifierEquivalentToTTAofBlend() {
        double sum = primaryTanks.stream()
                .mapToDouble(Tank::getVolume)
                .sum();

        return sum / getAcidifierTTALevelVsBlends();
    }

    /**
     * Average TTA of the primary primaryTanks divided by the acidity
     *
     * @return percentage increase of TTA needed
     */
    public double getPercentageIncreaseOfRequiredTTA() {
        return getAverage() / getAcidityDifference();
    }

    /**
     * Get litres of acidifier required to match the base acidity.
     *
     * @return litres if acidifier needed.
     */
    public double getLitresOfAcidifierRequiredToMatchBaseAcidity() {
        return (getLitresOfAcidifierEquivalentToTTAofBlend() / 100) * getPercentageIncreaseOfRequiredTTA();
    }
}
