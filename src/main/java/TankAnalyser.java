import domain.Tank;

import java.util.List;

public abstract class TankAnalyser {

    protected List<Tank> primaryTanks;

    protected Tank acidifierTanks;

    protected TankAnalyser(List<Tank> primaryTanks, Tank acidifierTanks) {
        this.primaryTanks = primaryTanks;
        this.acidifierTanks = acidifierTanks;
    }

    protected double getTotalVolume() {
        return primaryTanks.stream()
                .mapToDouble(Tank::getVolume)
                .sum();
    }



    /**
     * Calculates the amount needed to match target amount.
     *
     * @return the amount needed to match targer amount
     */
    protected abstract double calculate();
}
