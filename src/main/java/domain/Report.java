package domain;

public class Report {
    private double ttaAdjustment;
    private double brixAdjustment;
    private double waterAdjustment;

    public double getTtaAdjustment() {
        return ttaAdjustment;
    }

    public void setTtaAdjustment(double ttaAdjustment) {
        this.ttaAdjustment = ttaAdjustment;
    }

    public double getBrixAdjustment() {
        return brixAdjustment;
    }

    public void setBrixAdjustment(double brixAdjustment) {
        this.brixAdjustment = brixAdjustment;
    }

    public double getWaterAdjustment() {
        return waterAdjustment;
    }

    public void setWaterAdjustment(double waterAdjustment) {
        this.waterAdjustment = waterAdjustment;
    }
}
