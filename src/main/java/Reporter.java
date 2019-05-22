import domain.Tank;

public class Reporter {

    private Calculator calculator;

    public Reporter(Calculator calculator) {
        this.calculator = calculator;
    }

    public double getWaterToReachTargetTta(Tank blend) {
        return (calculator.getPercentageWaterNeededToReachTargetTta(blend)) * 1600;
    }

    public double getSugarToReachTargetBrix(Tank blend) {
        return calculator.getKilogramsOfSugarNeededToReachTarget(blend);
    }


    public double getWaterToReachTargetBrix(Tank blend) {
        return calculator.getPercentageWaterNeededToReachTargetBrix(blend) * 1600;
    }

    public double getAcidToReachTargetTta(Tank blend) {
        double litres = calculator.getEquivalentAmountOfAcidToBaseTanks();
        return 0;
    }
}
