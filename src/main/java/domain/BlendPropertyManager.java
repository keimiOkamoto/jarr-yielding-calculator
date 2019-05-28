package domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlendPropertyManager implements KombuchaManager {

    private Tank blend;
    private Map<TANK_TYPE, List<Tank>> mapOfTanks;

    public BlendPropertyManager(Tank blend, Map<TANK_TYPE, List<Tank>> mapOfTanks) {
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
    public void addSugarBy(double kgs) {
        double newBrix = ((kgs * 100) / mapOfTanks.get(TANK_TYPE.BASE).stream().mapToDouble(Tank::getVolume).sum()) + blend.getBrixValue();
        blend.setBrixValue(newBrix);
    }

    @Override
    public void addAcidBy(double litres) {
        Tank acid = mapOfTanks.get(TANK_TYPE.ACIDIFIER).get(0);
        double sugarKgs =  litres * (acid.getBrixValue() / 100);

        double volume = mapOfTanks.get(TANK_TYPE.BASE).stream().mapToDouble(Tank::getVolume).sum();
        double litreDifference = volume - litres;
        double sugarNeededForBlend = getTotalSugarKgsOfBaseTanks() - sugarKgs;
        double brixNeededForBlend = convertToThreeDecimalPlaces((sugarNeededForBlend / litreDifference) * 100);

//        double brixDifference = getDefaultBlend().getBrixValue() - brixNeededForBlend;
//        double newPercetageWaterNeeded = (brixDifference / getDefaultBlend().getBrixValue()) * 100;
        blend.setBrixValue(brixNeededForBlend);
    }



    private double getTotalSugarKgsOfBaseTanks() {
        return (mapOfTanks.get(TANK_TYPE.BASE).stream().mapToDouble(Tank::getVolume).sum() * 5.0) / 100;
    }

    @Override
    public Tank getBlend() {
        return this.blend;
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

        private double convertToThreeDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }
}
