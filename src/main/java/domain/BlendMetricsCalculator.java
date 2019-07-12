package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BlendMetricsCalculator implements Calculator {
    private double targetTta = 0.14;
    private double targetBrix = 5.0;

//    @Override
//    public double getPercentageWaterNeededToReachTargetTta(Tank blend) {
//        return convertToThreeDecimalPlaces((blend.getTtaValue() - targetTta) / blend.getTtaValue());
//    }

//    @Override
//    public double getKilogramsOfSugarNeededToReachTarget(Tank blend) {
//        return convertToThreeDecimalPlaces((getBrixDifferenceFromTarget(blend.getBrixValue()) * 1600) / 100);
//    }

    @Override
    public double getPercentageWaterNeededToReachTargetBrix(Tank blend) {
        return convertToThreeDecimalPlaces((blend.getBrixValue() - targetBrix) / blend.getBrixValue());
    }

    @Override
    public double getEquivalentAmountOfAcidInLitres(Tank acid, Tank blend, int totalVolume) {
        double litres = totalVolume / ((acid.getTtaValue() / blend.getTtaValue()));
        double equivalentAmountOfAcid = convertToTwoDecimalPlaces((litres / 100) * getPercentageIncreaseOfTtaFromTarget(blend));

        double percentageOfTtaNeededRegardlessOfVolume = getPercentageOfTtaNeededRegardlessOfVolume(equivalentAmountOfAcid, totalVolume);
        return convertToTwoDecimalPlaces((totalVolume / 100) * percentageOfTtaNeededRegardlessOfVolume);
    }

    @Override
    public double getPercentageWaterNeededToReachTargetBrix(Tank blend, double targetBrix) {
        return convertToThreeDecimalPlaces((blend.getBrixValue() - targetBrix) / blend.getBrixValue());
    }

    private double getPercentageOfTtaNeededRegardlessOfVolume(double equivalentAmountOfAcid, int totalVolume) {
        return equivalentAmountOfAcid / ((totalVolume + equivalentAmountOfAcid) / 100);
    }

    private double getPercentageIncreaseOfTtaFromTarget(Tank blend) {
        return convertToTwoDecimalPlaces(getPercentageOfTarget(targetTta / (targetTta - blend.getTtaValue())));
    }

    private double getPercentageOfTarget(double value) {
        return (1 / value) * 100;
    }

//    private double getBrixDifferenceFromTarget(double brixValue) {
//        return (targetBrix - brixValue);
//    }

    private double convertToTwoDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private double convertToThreeDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(3, RoundingMode.HALF_DOWN).doubleValue();
    }
}
