package domain;

import java.math.BigDecimal;

public interface Tank {

    BigDecimal getVolume();

    BigDecimal getValueOf(PROPERTY_TYPE propertyType);
}
