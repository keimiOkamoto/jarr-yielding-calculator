package domain;

public class KombuchaTank implements Tank {

    private double volume;
    private double brixValue;
    private double ttaValue;

    public KombuchaTank(double volume, double brixValue, double ttaValue) {
        this.volume = volume;
        this.brixValue = brixValue;
        this.ttaValue = ttaValue;
    }

    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public double getValue(PROPERTY_TYPE propertyType) {
        return PROPERTY_TYPE.BRIX.equals(propertyType) ? getBrixValue() : getTtaValue();
    }

    private double getBrixValue() {
        return brixValue;
    }

    private double getTtaValue() {
        return ttaValue;
    }

}
