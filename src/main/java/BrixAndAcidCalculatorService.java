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
    public double calculate(PROPERTY_TYPE propertyType){
        return tankAnalyser.analyse(propertyType);
    }

    @Override
    public void add(TANK_TYPE tank_type, Tank tank) {

    }
}
