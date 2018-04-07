import domain.TANK_TYPE;
import domain.Tank;

public interface JarrCalculatorService {

    double calculate();

    void add(TANK_TYPE tank_type, Tank tank);
}
