package domain.newsevice.kobuchamaker;

import domain.KombuchaTank;
import domain.Tank;
import domain.newsevice.factory.KombuchaBlendFactory;

import java.util.List;

public class KombuchaReportGenerator {

    private static final double TARGET_TTA = 0.14;
    private static final double BRIX = 5.0;
    private KombuchaPropertyValueManager waterPropertyValueManager;
    private Tank blend;

    public KombuchaReportGenerator(List<Tank> tanks, KombuchaPropertyValueManager waterPropertyValueManager) {
        this.blend = KombuchaBlendFactory.INSTANCE.getInitialTank(tanks);
        System.out.println(blend.getTtaValue() + " " + blend.getBrixValue());
        this.waterPropertyValueManager = waterPropertyValueManager;
    }


    public Report generateReport() {
        if (blend.getTtaValue() > TARGET_TTA) {
            Tank tank = waterPropertyValueManager.modifyValue(blend);
            System.out.println(tank.getTtaValue());
        }
        return null;
    }
}
