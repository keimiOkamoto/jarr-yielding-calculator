package domain;

import java.util.Objects;

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

    @Override
    public void setVolume(double value) {
        this.volume = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KombuchaTank that = (KombuchaTank) o;
        return Double.compare(that.volume, volume) == 0 &&
                Double.compare(that.brixValue, brixValue) == 0 &&
                Double.compare(that.ttaValue, ttaValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume, brixValue, ttaValue);
    }

    @Override
    public String toString() {
        return "KombuchaTank{" +
                "volume=" + volume +
                ", brixValue=" + brixValue +
                ", ttaValue=" + ttaValue +
                '}';
    }
}
