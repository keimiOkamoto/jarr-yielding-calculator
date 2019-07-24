package domain.newsevice.propertycalculators;

import domain.Tank;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static domain.newsevice.domain.TargetPropertyValue.BRIX_TARGET;

public class BrixCalculator implements KombuchaPropertyCalculator {

    @Override
    public double calculate(Tank blend) {
        return convertToThreeDecimalPlaces((getBrixDifferenceFromTarget(blend.getBrixValue()) * blend.getVolume() * 2) / 100);
    }

    private double getBrixDifferenceFromTarget(double brixValue) {
        return (BRIX_TARGET.get() - brixValue);
    }

    private double convertToThreeDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(3, RoundingMode.HALF_DOWN).doubleValue();
    }
}
