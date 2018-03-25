import domain.PROPERTY_TYPE;
import domain.TANK_TYPE;
import domain.Tank;
import domain.TankAnalyser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jarr-calculator")
public class BrixAndAcidCalculatorController implements JarrCalculatorService {

    private TankAnalyser tankAnalyser;

    public BrixAndAcidCalculatorController(TankAnalyser tankAnalyser) {
        this.tankAnalyser = tankAnalyser;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/calculate")
    public double calculate(PROPERTY_TYPE propertyType){
        return tankAnalyser.calculate(propertyType);
    }

    @Override
    public void add(TANK_TYPE tank_type, Tank tank) {

    }
}
