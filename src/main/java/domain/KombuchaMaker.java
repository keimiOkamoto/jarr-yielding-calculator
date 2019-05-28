package domain;

import java.util.List;
import java.util.Map;

public class KombuchaMaker {
    private KombuchaManager blendPropertyManager;
    private Calculator calculator;
    private Map<TANK_TYPE, List<Tank>> mapOfTanks;

    public KombuchaMaker(KombuchaManager blendPropertyManager, Calculator calculator, Map<TANK_TYPE, List<Tank>> mapOfTanks) {
        this.blendPropertyManager = blendPropertyManager;
        this.calculator = calculator;
        this.mapOfTanks = mapOfTanks;
    }

    Tank createInitialTank() {
        return blendPropertyManager.getDefaultBlend();
    }

    void addWater() {
        double percentageIncrease = calculator.getPercentageWaterNeededToReachTargetTta(blendPropertyManager.getBlend());
        blendPropertyManager.addWaterBy(percentageIncrease);
    }

    void addSugar() {
        double kilogramsOfSugarNeededToReachTarget = calculator.getKilogramsOfSugarNeededToReachTarget(blendPropertyManager.getBlend());
        blendPropertyManager.addSugarBy(kilogramsOfSugarNeededToReachTarget);
    }

    void addAcid() {
        double equivalentAmountOfAcidInLitres = calculator.getEquivalentAmountOfAcidInLitres(mapOfTanks.get(TANK_TYPE.ACIDIFIER).get(0), blendPropertyManager.getBlend(), mapOfTanks.get(TANK_TYPE.BASE).size());
        blendPropertyManager.addAcidBy(equivalentAmountOfAcidInLitres);
    }
}
