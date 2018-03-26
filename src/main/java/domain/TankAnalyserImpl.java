package domain;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class TankAnalyserImpl implements TankAnalyser {

    private static final double TARGET_BRIX = 5.0;

    private static final double TARGET_TTA = 0.14;

    private static final int ONE_HUNDRED = 100;

    private static final BiFunction<List<Tank>, PROPERTY_TYPE, Double> GET_AVERAGE = (tanks, propertyType) -> tanks.stream()
                                                                                                                    .mapToDouble(tank -> tank.getValueOf(propertyType))
                                                                                                                    .average()
                                                                                                                    .getAsDouble();

    private static final BiFunction<Double, Double, Double> FIND_WHOLE_OF_PERCENT =  ((num1, num2) -> (num1 / ONE_HUNDRED) * num2);
    private static final Function<List<Tank>, Double> GET_TOTAL_VOLUME = tank -> tank.stream().mapToDouble(Tank::getVolume).sum();
    private static final BinaryOperator<Double> SUBTRACT = (x, y) -> x - y;
    private static final BinaryOperator<Double> DIVIDE = (x, y) -> x / y;
    private static final BinaryOperator<Double> ADD = (x,y) -> x + y;

    private List<Tank> primaryTanks;

    private Tank acidifierTank;

    public TankAnalyserImpl(TankContainer tankContainer) {
        this.primaryTanks = tankContainer.getTanks(TANK_TYPE.PRIMARY);
        this.acidifierTank = tankContainer.getTanks(TANK_TYPE.ACIDIFIER).get(0);
    }

    @Override
    public double analyse(PROPERTY_TYPE propertyType) {

        if (PROPERTY_TYPE.TTA.equals(propertyType)) {
            return getLitresOfAcidifierRequiredToMatchBaseAcidity();
        } else if (PROPERTY_TYPE.BRIX.equals(propertyType)) {
            return getAmountOfSugarToBeAddedOrTakenAway();
        } else {
            throw new UnsupportedOperationException(String.format("Property type is invalid. Valid types include: %s, %s", PROPERTY_TYPE.TTA, PROPERTY_TYPE.BRIX));
        }
    }

    private double getBrixDifference() {
        return SUBTRACT.apply(GET_AVERAGE.apply(primaryTanks, PROPERTY_TYPE.BRIX), TARGET_BRIX);
    }

    private double getTargetKGOfSugarInTotalYield() {
        return FIND_WHOLE_OF_PERCENT.apply(TARGET_BRIX , GET_TOTAL_VOLUME.apply(primaryTanks));
    }

    private double getQuantityOfSugarInPrimaryTankYield() {
        double brixOrFirstTank = primaryTanks.get(0)
                                             .getValueOf(PROPERTY_TYPE.BRIX);
        return FIND_WHOLE_OF_PERCENT.apply(brixOrFirstTank, SUBTRACT.apply(GET_TOTAL_VOLUME.apply(primaryTanks), getLitresOfAcidifierRequiredToMatchBaseAcidity()));
    }

    private double getQuantityOfSugarInAcidifier() {
        return FIND_WHOLE_OF_PERCENT.apply(acidifierTank.getValueOf(PROPERTY_TYPE.BRIX), getLitresOfAcidifierRequiredToMatchBaseAcidity());
    }

    private double getTotalQuantityOfSugarInYield() {
        return ADD.apply(getQuantityOfSugarInPrimaryTankYield(), getQuantityOfSugarInAcidifier());
    }

    private double getAmountOfSugarToBeAddedOrTakenAway() {
        return SUBTRACT.apply(getTotalQuantityOfSugarInYield(), getTargetKGOfSugarInTotalYield());
    }

    private double getAcidityDifference() {
        return SUBTRACT.apply(TARGET_TTA, GET_AVERAGE.apply(primaryTanks, PROPERTY_TYPE.TTA));
    }

    private double getAcidifierTTALevelVsBlends() {
        return DIVIDE.apply(acidifierTank.getValueOf(PROPERTY_TYPE.TTA), GET_AVERAGE.apply(primaryTanks, PROPERTY_TYPE.TTA));
    }

    private double getLitresOfAcidifierEquivalentToTTAofBlend() {
        return GET_TOTAL_VOLUME.apply(primaryTanks) / getAcidifierTTALevelVsBlends();
    }

    private double getPercentageIncreaseOfRequiredTTA() {
        return DIVIDE.apply(GET_AVERAGE.apply(primaryTanks, PROPERTY_TYPE.TTA), getAcidityDifference());
    }

    private double getLitresOfAcidifierRequiredToMatchBaseAcidity() {
        return FIND_WHOLE_OF_PERCENT.apply(getLitresOfAcidifierEquivalentToTTAofBlend(), getPercentageIncreaseOfRequiredTTA());
    }
}
