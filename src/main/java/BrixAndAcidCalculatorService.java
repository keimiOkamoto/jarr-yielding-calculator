import domain.PROPERTY_TYPE;
import domain.TankAnalyser;

public class BrixAndAcidCalculatorService {

    private TankAnalyser tankAnalyser;


    public BrixAndAcidCalculatorService(TankAnalyser tankAnalyser){
        this.tankAnalyser = tankAnalyser;
    }

    public double calculate(PROPERTY_TYPE propertyType){
        return tankAnalyser.calculate(propertyType);
    }
}
