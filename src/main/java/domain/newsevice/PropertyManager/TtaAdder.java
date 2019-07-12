package domain.newsevice.PropertyManager;

import domain.Tank;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TtaAdder implements KombuchaPropertyAdder {
    private Tank acidifier;

    public TtaAdder(Tank acidifier) {
        this.acidifier = acidifier;
    }

    @Override
    public Tank add(double litres, Tank blend) {
        double sugarKgs = litres * (acidifier.getBrixValue() / 100);

        double volume = blend.getVolume() * 2;
        double litreDifference = volume - litres;
        double sugarNeededForBlend = getTotalSugarKgsOfBaseTanks(blend) - sugarKgs;
        double brixNeededForBlend = convertToThreeDecimalPlaces((sugarNeededForBlend / litreDifference) * 100);

//        double brixDifference = getDefaultBlend().getBrixValue() - brixNeededForBlend;
//        double newPercetageWaterNeeded = (brixDifference / getDefaultBlend().getBrixValue()) * 100;
        blend.setBrixValue(brixNeededForBlend);
        return blend;
    }

    private double getTotalSugarKgsOfBaseTanks(Tank blend) {
        return (blend.getVolume() * 2 * 5.0) / 100;
    }

     private double convertToTwoDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private double convertToThreeDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }
}
