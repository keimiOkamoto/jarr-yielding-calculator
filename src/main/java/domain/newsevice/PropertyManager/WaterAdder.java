package domain.newsevice.PropertyManager;

import domain.Tank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class WaterAdder implements KombuchaPropertyAdder {

    @Override
    public Tank add(double percentageIncrease, Tank blend) {
        blend.setTtaValue(recalibrateTta(percentageIncrease, blend));
        blend.setBrixValue(recalibrateBrix(percentageIncrease, blend));
        return blend;
    }

    private double recalibrateTta(double percentage, Tank blend) {
        return convertToTwoDecimalPlaces(blend.getTtaValue() - ((percentage * blend.getTtaValue()) / 100));
    }

    private double recalibrateBrix(double percentage, Tank blend) {
        return convertToTwoDecimalPlaces(blend.getBrixValue() - (percentage * blend.getBrixValue()));
    }

    private double convertToTwoDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
