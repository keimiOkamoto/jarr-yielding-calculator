import domain.PROPERTY_TYPE;
import domain.Report;
import domain.Tank;

import java.util.List;
import java.util.stream.Collectors;

public class Calculator implements JarrCalculatorService {
    private static final double TARGET_TTA = 0.140;
    private static final double TARGET_BRIX = 5.0;

    private List<Tank> tanks;

    public Calculator(List<Tank> tanks) {
        this.tanks = tanks;
    }

    public Report calculate() {
        if (isAcidic()) {
            Report report = new Report();
            report.setWaterAdjustment(getAmountOfWaterNeededToAdjustTTA());
            report.setBrixAdjustment(getAmountOfSugarNeededKg());
            return report;

        } else {
            //needs acidifier
            return null;
        }
    }

    private boolean isAcidic() {
        return getAverageTTAof(tanks) > TARGET_TTA;
    }

    public double getAmountOfWaterNeededToAdjustTTA() {
        double percentageOfWaterNeeded = (getAverageTTAof(tanks) - TARGET_TTA) / getAverageTTAof(tanks);
        double totalVolume = tanks.stream().mapToDouble(Tank::getVolume).sum();
        double percentageCalculationOfTank = percentageOfWaterNeeded * totalVolume;
        double litersOfWaterNeeded = (totalVolume - percentageCalculationOfTank) / tanks.size();
        return litersOfWaterNeeded;
    }

    private double getPercentageNeeded() {
        return (getAverageTTAof(tanks) - TARGET_TTA) / getAverageTTAof(tanks);
    }

    private double getAmountOfSugarNeededKg() {
        double percentOfBrix = getBrixAAverage(tanks) * getPercentageNeeded();
        double kgsSugar = (TARGET_BRIX - (getBrixAAverage(tanks) - percentOfBrix)) * 10;
        double totalVolume = tanks.stream().mapToDouble(Tank::getVolume).sum();

        return kgsSugar * totalVolume / 1000;
    }

    private static double getAverageTTAof(List<Tank> tanks) {
        return tanks.stream().collect(Collectors.averagingDouble(tank -> tank.getValueOf(PROPERTY_TYPE.TTA)));
    }

    private static double getBrixAAverage(List<Tank> tanks) {
        return tanks.stream().collect(Collectors.averagingDouble(tank -> tank.getValueOf(PROPERTY_TYPE.BRIX)));
    }
}
