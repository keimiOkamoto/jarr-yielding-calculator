import domain.Tank;

public interface Calculator {

    double getPercentageWaterNeededToReachTargetTta(Tank blend);

    double getKilogramsOfSugarNeededToReachTarget(Tank blend);

    double getPercentageWaterNeededToReachTargetBrix(Tank blend);

    double getEquivalentAmountOfAcidToBaseTanks();
}
