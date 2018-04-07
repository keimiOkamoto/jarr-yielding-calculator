import domain.PROPERTY_TYPE;
import domain.TANK_TYPE;
import domain.Tank;
import domain.TankAnalyser;

public class BrixAndAcidCalculatorService implements JarrCalculatorService {

    private TankAnalyser tankAnalyser;

    public BrixAndAcidCalculatorService(TankAnalyser tankAnalyser) {
        this.tankAnalyser = tankAnalyser;
    }

    @Override
    public double calculate() {

//        if (brix == 5.0) {
//            return brix;
//        } else
//            double tta = tankAnalyser.analyse(PROPERTY_TYPE.TTA);
//            double brix = tankAnalyser.analyse(PROPERTY_TYPE.BRIX);
//
//            calculate();
//
//        return tankAnalyser.analyse(PROPERTY_TYPE.TTA);\
        return 0;
    }

    @Override
    public void add(TANK_TYPE tank_type, Tank tank) {

    }
}
