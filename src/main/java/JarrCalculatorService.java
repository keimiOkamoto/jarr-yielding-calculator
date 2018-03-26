import domain.PROPERTY_TYPE;
import domain.TANK_TYPE;
import domain.Tank;

public interface JarrCalculatorService {

    double calculate(PROPERTY_TYPE propertyType);

    void add(TANK_TYPE tank_type, Tank tank);
}
