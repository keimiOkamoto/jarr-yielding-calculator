package domain.newsevice.propertycalculators;

import domain.Tank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WaterCalculator implements KombuchaPropertyCalculator {
    private double TARGET_TTA = 0.14;

    @Override
    public double calculate(Tank blend) {
        return convertToThreeDecimalPlaces((blend.getTtaValue() - TARGET_TTA) / blend.getTtaValue()) * 100;
    }

    private double convertToThreeDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(3, RoundingMode.HALF_DOWN).doubleValue();
    }
}
