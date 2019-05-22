import domain.Tank;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BrewPropertyAdjuster implements Adjuster {

    private Tank blend;
    private double targrtBrix = 5.0;
    private Calculator calculator;
    private Map<TANK_TYPE, List<Tank>> mapOfTanks;

    public BrewPropertyAdjuster(Tank blend, Map<TANK_TYPE, List<Tank>> mapOfTanks) {
        this.mapOfTanks = mapOfTanks;
        this.blend = blend;
    }

    @Override
    public Tank getDefaultBlend() {
        blend.setTtaValue(mapOfTanks.get(TANK_TYPE.BASE).stream().collect(Collectors.averagingDouble(Tank::getTtaValue)));
        blend.setBrixValue(mapOfTanks.get(TANK_TYPE.BASE).stream().collect(Collectors.averagingDouble(Tank::getBrixValue)));
        blend.setVolume(800);
        return blend;
    }

    @Override
    public void addWaterBy(double percentageIncrease) {
        double newTta = recalibrateTta(percentageIncrease);
        double newBrix = recalibrateBrix(percentageIncrease);
        blend.setTtaValue(newTta);
        blend.setBrixValue(newBrix);
    }

    @Override
    public Tank getBlend() {
        return this.blend;
    }

    @Override
    public void addSugarBy(double kgs) {
        double newBrix = ((kgs * 100) / mapOfTanks.get(TANK_TYPE.BASE).stream().mapToDouble(Tank::getVolume).sum()) + blend.getBrixValue();
        blend.setBrixValue(newBrix);
    }

    private double recalibrateTta(double percentage) {
        return convertToTwoDecimalPlaces(blend.getTtaValue() - (percentage * blend.getTtaValue()));
    }

    private double recalibrateBrix(double percentage) {
        return convertToTwoDecimalPlaces(blend.getBrixValue() - (percentage * blend.getBrixValue()));
    }


    private double convertToTwoDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
