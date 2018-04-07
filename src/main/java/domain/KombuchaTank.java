package domain;

import java.math.BigDecimal;

public class KombuchaTank implements Tank {

    private BigDecimal volume;
    private BigDecimal brixValue;
    private BigDecimal ttaValue;

    public KombuchaTank(BigDecimal volume, BigDecimal brixValue, BigDecimal ttaValue) {
        this.volume = volume;
        this.brixValue = brixValue;
        this.ttaValue = ttaValue;
    }

    @Override
    public BigDecimal getVolume() {
        return volume;
    }

    @Override
    public BigDecimal getValueOf(PROPERTY_TYPE propertyType) {
        return PROPERTY_TYPE.BRIX.equals(propertyType) ? getBrixValue() : getTtaValue();
    }

    private BigDecimal getBrixValue() {
        return brixValue;
    }

    private BigDecimal getTtaValue() {
        return ttaValue;
    }

}
