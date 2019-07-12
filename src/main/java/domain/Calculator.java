package domain;

public interface Calculator {

//    double getPercentageWaterNeededToReachTargetTta(Tank blend);

//    double getKilogramsOfSugarNeededToReachTarget(Tank blend);

    double getPercentageWaterNeededToReachTargetBrix(Tank blend);

    double getEquivalentAmountOfAcidInLitres(Tank Acid, Tank blend, int totalVolume);

    double getPercentageWaterNeededToReachTargetBrix(Tank blend, double targetBrix);

}
