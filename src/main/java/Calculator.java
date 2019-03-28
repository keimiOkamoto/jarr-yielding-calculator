import domain.PROPERTY_TYPE;
import domain.Report;
import domain.Tank;

import java.util.List;
import java.util.stream.Collectors;

public class Calculator implements JarrCalculatorService {
    private static final double TARGET_TTA = 0.140;
    private static final double TARGET_BRIX = 5.00;

    private List<Tank> tanks;

    public Calculator(List<Tank> tanks) {
        this.tanks = tanks;
    }

    public Report calculate() {
        Report report = new Report();
        if (isAcidic()) {
            report.setWaterAdjustment(getAmountOfWaterNeededToAdjustTTA());
            report.setBrixAdjustment(getAmountOfSugarNeededKg());
            return report;

        } else {
            adjustSugar(report);
            return report;
        }
    }

    private double adjustSugar(Report report) {
        if (getAverageBrixOfAllTanks() > TARGET_BRIX) {
            //water must be added
            //how much is the sugar too high by?]
            double amountExceededInPercentage = (getAverageBrixOfAllTanks() - TARGET_BRIX) / TARGET_BRIX;
            addWater(amountExceededInPercentage, report);
            double newTta = adjustTta(amountExceededInPercentage);

            if (newTta < TARGET_TTA) {
//                double multiplier = 1.104 - newTta;
//                double literes = 1600/multiplier;
//                double increaseNeededPercentage = TARGET_TTA-newTta;
//                double amount = TARGET_TTA/increaseNeededPercentage;
//                (1/amount)*100;
                //Acidifier tank is added

            }


        } else {
            //sugar must be added
        }
        return 0;
    }

    private void addWater(double amountExceededInPercentage, Report report) {
//        double five = getAverageBrixOfAllTanks() - (amountExceededInPercentage * getAverageBrixOfAllTanks());

        report.setWaterAdjustment((amountExceededInPercentage * 100) * 1600 / 100); //should reach five
        getNewTtaValue(amountExceededInPercentage);
    }

    private double getNewTtaValue(double amountExceededInPercentage) {
        return getAverageTtaOfAllTanks() - (amountExceededInPercentage * getAverageTtaOfAllTanks());
    }

    private double adjustTta(double exceededPercentage) {
        if (getAverageTtaOfAllTanks() != TARGET_TTA) {
            double amountToDilute = exceededPercentage * getAverageTtaOfAllTanks();
            return getAverageTtaOfAllTanks() - amountToDilute;
        }
        return 0;
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
