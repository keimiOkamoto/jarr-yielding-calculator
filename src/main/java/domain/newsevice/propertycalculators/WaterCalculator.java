package domain.newsevice.propertycalculators;

import domain.Tank;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static domain.newsevice.domain.TargetPropertyValue.TTA_TARGET;

public class WaterCalculator implements KombuchaPropertyCalculator {

    @Override
    public double calculate(Tank blend) {
        return convertToThreeDecimalPlaces((blend.getTtaValue() - TTA_TARGET.get()) / blend.getTtaValue()) * 100;
    }

    private double convertToThreeDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(3, RoundingMode.HALF_DOWN).doubleValue();
    }
}
