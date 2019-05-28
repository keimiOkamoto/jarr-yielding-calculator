package domain;

public interface KombuchaManager {

    Tank getDefaultBlend();

    void addWaterBy(double percentageIncrease);

    Tank getBlend();

    void addSugarBy(double kgs);

    void addAcidBy(double litres);
}
