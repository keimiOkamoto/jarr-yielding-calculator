package domain;

public class Report {
    private double ttaAdjustment;
    private double brixAdjustment;
    private double waterAdjustment;

    public Report() {
    }

    public double getTtaAdjustmentNeededInLitres() {
        return ttaAdjustment;
    }

    public void setTtaAdjustmentNeededInLitres(double ttaAdjustment) {
        this.ttaAdjustment = ttaAdjustment;
    }

    public double getBrixAdjustmentNeededInKg() {
        return brixAdjustment;
    }

    public void setBrixAdjustmentNeededInKg(double brixAdjustment) {
        this.brixAdjustment = brixAdjustment;
    }

    public double getWaterAdjustmentNeededInLitres() {
        return waterAdjustment;
    }

    public void setWaterAdjustmentNeededInLitres(double waterAdjustment) {
        this.waterAdjustment = waterAdjustment;
    }

}
