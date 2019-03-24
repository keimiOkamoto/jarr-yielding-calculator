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
        return getAverageTtaOfAllTanks() > TARGET_TTA;
    }

    public double getAmountOfWaterNeededToAdjustTTA() {
        double percentageOfWaterNeeded = (getAverageTtaOfAllTanks() - TARGET_TTA) / getAverageTtaOfAllTanks();
        double totalVolume = getTotalVolumeOfTanks();
        double percentageCalculationOfTank = percentageOfWaterNeeded * totalVolume;
        double litersOfWaterNeeded = (totalVolume - percentageCalculationOfTank) / tanks.size();
        return litersOfWaterNeeded;
    }

    private double getAmountOfSugarNeededKg() {
        double percentOfBrix = getAverageBrixOfAllTanks() * getPercentageNeeded();
        double kgsSugar = (TARGET_BRIX - (getAverageBrixOfAllTanks() - percentOfBrix)) * 10;
        double totalVolume = getTotalVolumeOfTanks();

        return kgsSugar * totalVolume / 1000;
    }

    private double getPercentageNeeded() {
        return (getAverageTtaOfAllTanks() - TARGET_TTA) / getAverageTtaOfAllTanks();
    }

    private double getTotalVolumeOfTanks() {
        return tanks.stream().mapToDouble(Tank::getVolume).sum();
    }

    private double getAverageTtaOfAllTanks() {
        return tanks.stream().collect(Collectors.averagingDouble(tank -> tank.getValueOf(PROPERTY_TYPE.TTA)));
    }

    private double getAverageBrixOfAllTanks() {
        return tanks.stream().collect(Collectors.averagingDouble(tank -> tank.getValueOf(PROPERTY_TYPE.BRIX)));
    }
}
