import domain.Tank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BlendMetricsCalculator implements Calculator {
    private double targetTta = 0.14;
    private double targetBrix = 5.0;


    public BlendMetricsCalculator() {
    }

    @Override
    public double getPercentageWaterNeededToReachTargetTta(Tank blend) {
        return convertToTwoDecimalPlaces((blend.getTtaValue() - targetTta) / blend.getTtaValue());

    }

    @Override
    public double getKilogramsOfSugarNeededToReachTarget(Tank blend) {
        return convertToTwoDecimalPlaces((getBrixDifferenceFromTarget(blend.getBrixValue()) * 1600) / 100);
    }

    @Override
    public double getPercentageWaterNeededToReachTargetBrix(Tank blend) {
        return convertToTwoDecimalPlaces((blend.getBrixValue() - targetBrix) / blend.getBrixValue());
    }

    @Override
    public double getEquivalentAmountOfAcidToBaseTanks() {
        return 0;
    }

    private double getBrixDifferenceFromTarget(double brixValue) {
        return (targetBrix - brixValue);
    }

    private double convertToTwoDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }
}
