package domain.newsevice.domain;

import domain.Tank;

public class TankReport {
    private Tank newTank;
    private double litresOfWater;
    private double kgsOfWater;
    private double litresOfAcidifier;

    public TankReport(Tank newTank, double litresOfWater, double kgsOfWater, double litresOfAcidifier) {
        this.newTank = newTank;
        this.litresOfWater = litresOfWater;
        this.kgsOfWater = kgsOfWater;
        this.litresOfAcidifier = litresOfAcidifier;
    }

    public Tank getNewTank() {
        return newTank;
    }

    public void setNewTank(Tank newTank) {
        this.newTank = newTank;
    }

    public double getLitresOfWater() {
        return litresOfWater;
    }

    public void setLitresOfWater(double litresOfWater) {
        this.litresOfWater = litresOfWater;
    }

    public double getKgsOfWater() {
        return kgsOfWater;
    }

    public void setKgsOfWater(double kgsOfWater) {
        this.kgsOfWater = kgsOfWater;
    }

    public double getLitresOfAcidifier() {
        return litresOfAcidifier;
    }

    public void setLitresOfAcidifier(double litresOfAcidifier) {
        this.litresOfAcidifier = litresOfAcidifier;
    }
}
