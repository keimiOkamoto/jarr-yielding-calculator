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

    public double getVolume() {
        return volume;
    }

    public double getBrixValue() {
        return brixValue;
    }

    public double getTtaValue() {
        return ttaValue;
    }

    public void setBrixValue(double value) {
        this.brixValue = value;
    }

    public void setTtaValue(double value) {
        this.ttaValue = value;
    }

}
