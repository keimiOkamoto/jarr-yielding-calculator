import domain.Report;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BlendGenerator {
    private KombuchaAnalyser brewAnalyser;
    private Report report;


    public BlendGenerator(KombuchaAnalyser brewAnalyser, Report report) {
        this.brewAnalyser = brewAnalyser;
        this.report = report;
    }

    public Report createBlend() {
        if (brewAnalyser.getTTaValue() == 0.14 && brewAnalyser.getBrixValue() == 5.0) {
            return report;
        } else {
            if (brewAnalyser.isTooAcidic()) {
                double litres = (brewAnalyser.getTotalVolume() - getLitresOfWaterNeededToReachTargetTta()) / brewAnalyser.getTankCount();
            } else {
                //Add acid
            }

            if (brewAnalyser.tooMuchBrix()) {
                getLitresOfWaterNeededToReachTargetBrix();
            } else {
                getAmountOfSugarNeededKg();
            }
            createBlend();
        }
        return report;
    }

    public double getLitresOfWaterNeededToReachTargetTta() {
        double percentageCalculationOfTank = getPercentageOfWaterNeededToReachTargetTta() * brewAnalyser.getTotalVolume();

        adjustTtaValue(getPercentageOfWaterNeededToReachTargetTta());
        adjustBrixValue(getPercentageOfWaterNeededToReachTargetBrix());

        return convertToTwoDecimalPlaces(percentageCalculationOfTank);
    }

    public double getLitresOfWaterNeededToReachTargetBrix() {
        double litresOfWater = getPercentageOfWaterNeededToReachTargetBrix() * brewAnalyser.getTotalVolume();

        adjustTtaValue(getPercentageOfWaterNeededToReachTargetTta());
        adjustBrixValue(getPercentageOfWaterNeededToReachTargetBrix());

        return convertToTwoDecimalPlaces(litresOfWater);
    }

    private double getPercentageOfWaterNeededToReachTargetTta() {
        return brewAnalyser.getDifferenceBetweenTtaValueAndTargetValue() / brewAnalyser.getTTaValue();
    }

    private double getPercentageOfWaterNeededToReachTargetBrix() {
        return brewAnalyser.getDifferenceFromBrixValueToTargetValue() / brewAnalyser.getBrixValue();
    }

    private void adjustTtaValue(double percentage) {
        double newTta = convertToTwoDecimalPlaces(brewAnalyser.getTTaValue() - (percentage * brewAnalyser.getTTaValue()));
        brewAnalyser.setTtaValue(newTta);
    }

    private void adjustBrixValue(double percentage) {
        double newBrixValue = convertToTwoDecimalPlaces(brewAnalyser.getBrixValue() - (percentage * brewAnalyser.getBrixValue()));
        brewAnalyser.setBrixValue(newBrixValue);
    }

    private double getAmountOfSugarNeededKg() {
        return brewAnalyser.getDifferenceFromBrixTargetToValue() * 10;
    }

    private double convertToTwoDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}