import domain.Tank;

import java.util.List;

public abstract class TankAnalyser {

    protected List<Tank> primaryTanks;

    protected Tank acidifierTanks;

    public TankAnalyser(List<Tank> primaryTanks, Tank acidifierTanks) {
        this.primaryTanks = primaryTanks;
        this.acidifierTanks = acidifierTanks;
    }

    public double getTotalVolume() {
        return primaryTanks.stream()
                .mapToDouble(Tank::getVolume)
                .sum();
    }

    /**
     * Given a type of property that needs to analyse
     * @return
     */
    abstract double calculate();
}
