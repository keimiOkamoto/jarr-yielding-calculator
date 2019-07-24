package domain.newsevice.kobuchamaker;

import domain.Tank;
import domain.newsevice.factory.KombuchaBlendFactory;

import java.util.List;

import static domain.newsevice.domain.TargetPropertyValue.*;

public class KombuchaReportGenerator {

    private KombuchaPropertyValueModifier waterPropertyValueManager;
    private Tank blend;

    public KombuchaReportGenerator(List<Tank> tanks, KombuchaPropertyValueModifier waterPropertyValueManager) {
        this.blend = KombuchaBlendFactory.INSTANCE.getInitialTank(tanks);
        this.waterPropertyValueManager = waterPropertyValueManager;
    }


    public Report generateReport() {
        if (blend.getTtaValue() > TTA_TARGET.get()) {
            blend = waterPropertyValueManager.modifyValue(blend);

        } else {

        }

        return null;
    }
}
