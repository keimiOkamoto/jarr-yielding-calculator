import domain.Tank;

public interface Adjuster {

    Tank getDefaultBlend();

    void addWaterBy(double percentageIncrease);

    Tank getBlend();

    void addSugarBy(double kgs);
}
