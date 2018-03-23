
import domain.PROPERTY_TYPE;
import domain.Tank;

import java.util.List;
import java.util.function.BiFunction;

public class Calculator {

    public static BiFunction<List<Tank>, PROPERTY_TYPE, Double> getAverage = (tanks, propertyType) -> tanks.stream()
            .mapToDouble(tank -> tank.getValue(propertyType))
            .average()
            .getAsDouble();



}
