package domain.newsevice.propertycalculators;

import domain.Tank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BrixCalculator implements KombuchaPropertyCalculator {
    private static final double TARGET_BRIX = 5.0;

    @Override
    public double calculate(Tank blend) {
        return convertToThreeDecimalPlaces((getBrixDifferenceFromTarget(blend.getBrixValue()) * blend.getVolume() * 2) / 100);
    }

    private double getBrixDifferenceFromTarget(double brixValue) {
        return (TARGET_BRIX - brixValue);
    }

    private double convertToThreeDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(3, RoundingMode.HALF_DOWN).doubleValue();
    }
}
