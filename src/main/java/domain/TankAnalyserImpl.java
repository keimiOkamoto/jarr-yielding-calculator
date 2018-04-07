package domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;

public class TankAnalyserImpl implements TankAnalyser {

    private static final BigDecimal TARGET_BRIX = new BigDecimal(5.0);

    private static final BigDecimal TARGET_TTA = new BigDecimal(0.14);

    private static final BigDecimal ONE_HUNDRED = new BigDecimal(100);

    private static final BiFunction<List<Tank>, PROPERTY_TYPE, BigDecimal> GET_AVERAGE = (tanks, propertyType) -> tanks.stream()
                                                                                                                       .map(tank -> tank.getValueOf(propertyType))
                                                                                                                       .collect(BigDecimalAverager::new, BigDecimalAverager::accept, BigDecimalAverager::combine).average();


    private static final BiFunction<BigDecimal, BigDecimal, BigDecimal> FIND_WHOLE_OF_PERCENT =  ((num1, num2) -> (num1.divide(ONE_HUNDRED)).multiply(num2));

    private static final Function<List<Tank>, Optional<BigDecimal>> GET_TOTAL_VOLUME = tank -> tank.stream().map(Tank::getVolume).reduce(BigDecimal::add);

    private List<Tank> primaryTanks;

    private Tank acidifierTank;

    public TankAnalyserImpl(TankContainer tankContainer) {
        this.primaryTanks = tankContainer.getTanks(TANK_TYPE.PRIMARY);
        this.acidifierTank = tankContainer.getTanks(TANK_TYPE.ACIDIFIER).get(0);
    }

    @Override
    public BigDecimal calculate(PROPERTY_TYPE propertyType) {
        if (PROPERTY_TYPE.TTA.equals(propertyType)) {
            return getLitresOfAcidifierRequiredToMatchBaseAcidity();
        } else if (PROPERTY_TYPE.BRIX.equals(propertyType)) {
            return getAmountOfSugarToBeAddedOrTakenAway();
        } else {
            throw new UnsupportedOperationException(String.format("Property type is invalid. Valid types include: %s, %s", PROPERTY_TYPE.TTA, PROPERTY_TYPE.BRIX));
        }
    }

    private BigDecimal getBrixDifference() {
        return GET_AVERAGE.apply(primaryTanks, PROPERTY_TYPE.BRIX).subtract(TARGET_BRIX);
    }

    private BigDecimal getTargetKGOfSugarInTotalYield() {
        return FIND_WHOLE_OF_PERCENT.apply(TARGET_BRIX, GET_TOTAL_VOLUME.apply(primaryTanks).get());
    }

    private BigDecimal getQuantityOfSugarInPrimaryTankYield() {
        BigDecimal brixOrFirstTank = primaryTanks.get(0)
                                             .getValueOf(PROPERTY_TYPE.BRIX);
        return FIND_WHOLE_OF_PERCENT.apply(brixOrFirstTank, GET_TOTAL_VOLUME.apply(primaryTanks).get().subtract(getLitresOfAcidifierRequiredToMatchBaseAcidity()));
//        return FIND_WHOLE_OF_PERCENT.apply(brixOrFirstTank, SUBTRACT.apply(GET_TOTAL_VOLUME.apply(primaryTanks), getLitresOfAcidifierRequiredToMatchBaseAcidity()));
    }

    private BigDecimal getQuantityOfSugarInAcidifier() {
        return FIND_WHOLE_OF_PERCENT.apply(acidifierTank.getValueOf(PROPERTY_TYPE.BRIX), getLitresOfAcidifierRequiredToMatchBaseAcidity());
    }

    private BigDecimal getTotalQuantityOfSugarInYield() {
        return getQuantityOfSugarInPrimaryTankYield().add(getQuantityOfSugarInAcidifier());
    }

    private BigDecimal getAmountOfSugarToBeAddedOrTakenAway() {
        return getTotalQuantityOfSugarInYield().subtract(getTargetKGOfSugarInTotalYield());
    }

    private BigDecimal getAcidityDifference() {
        return TARGET_TTA.subtract(GET_AVERAGE.apply(primaryTanks, PROPERTY_TYPE.TTA));
    }

    private BigDecimal getAcidifierTTALevelVsBlends() {
        return acidifierTank.getValueOf(PROPERTY_TYPE.TTA).divide(GET_AVERAGE.apply(primaryTanks, PROPERTY_TYPE.TTA), 4, 1);
    }

    private BigDecimal getLitresOfAcidifierEquivalentToTTAofBlend() {
        return GET_TOTAL_VOLUME.apply(primaryTanks).get().divide(getAcidifierTTALevelVsBlends(), 4 ,1);
    }

    private BigDecimal getPercentageIncreaseOfRequiredTTA() {
        return GET_AVERAGE.apply(primaryTanks, PROPERTY_TYPE.TTA).divide(getAcidityDifference(), 4, 1);
    }

    private BigDecimal getLitresOfAcidifierRequiredToMatchBaseAcidity() {
        return FIND_WHOLE_OF_PERCENT.apply(getLitresOfAcidifierEquivalentToTTAofBlend(), getPercentageIncreaseOfRequiredTTA());
    }
}
