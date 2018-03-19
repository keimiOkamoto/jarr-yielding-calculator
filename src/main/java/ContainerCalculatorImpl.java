import java.util.List;
import java.util.OptionalDouble;

public class ContainerCalculatorImpl implements ContainerCalculator{

    private List<Tank> tanks;

    public ContainerCalculatorImpl() {
        tanks = TankContainer.INSTANCE.getTanks();
    }

    @Override
    public double getTotalVolume() {
        return tanks.stream()
                .mapToDouble(Tank::getVolume)
                .sum();
    }

    @Override
    public OptionalDouble getAverageBrix() {
        return tanks.stream()
                    .mapToDouble(Tank::getBrixValue)
                    .average();
    }

    @Override
    public OptionalDouble getAverageTta() {
        return tanks.stream()
                    .mapToDouble(Tank::getTtaValue)
                    .average();
    }

}
