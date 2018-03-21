import domain.Tank;

import java.util.List;

public class BrixAnalyser extends TankAnalyser {

    private static final double TARGET_BRIX = 5.0;

    private double ttaCalculation;

    public BrixAnalyser(double ttaCalculation, List<Tank> primaryTanks, Tank acidifierTank) {
        super(primaryTanks, acidifierTank);
        this.ttaCalculation = ttaCalculation;
    }

    @Override
    public double calculate() {
        return getAmountOfSugarToBeAddedOrTakenAway();
    }

    public double getAverage() {
        return primaryTanks.stream()
                    .mapToDouble(Tank::getBrixValue)
                    .average()
                    .getAsDouble();
    }

    /**
     * Gets the difference in brix value between the average brix value of the
     * primary primaryTanks and the target brix value.
     *
     * @return the difference in value between the average brix and target brix
     */
    public double getBrixDifference() {
        return getAverage() - TARGET_BRIX;
    }

    /**
     *
     * @return
     */
    public double getTargetKGOfSugarInTotalYield() {
        return (TARGET_BRIX / 100) * getTotalVolume();
    }

    public double getQuantityOfSugarInPrimaryTankYield() {
        return (getTotalVolume() - ttaCalculation) * (primaryTanks.get(0).getBrixValue() / 100);
    }

    public double getQuantityOfSugarInAcidifier() {
        return (acidifierTanks.getBrixValue() / 100) * ttaCalculation;
    }

    public double getTotalQuantityOfSugarInYield() {
        return getQuantityOfSugarInPrimaryTankYield() + getQuantityOfSugarInAcidifier();
    }

    public double getAmountOfSugarToBeAddedOrTakenAway() {
        return getTotalQuantityOfSugarInYield() - getTargetKGOfSugarInTotalYield();
    }

}
