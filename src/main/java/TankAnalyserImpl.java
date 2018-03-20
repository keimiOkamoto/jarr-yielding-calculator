import domain.Tank;

import java.util.List;
import java.util.Map;

public class TankAnalyserImpl implements TankAnalyser {

    private final Map<TANK_TYPE, List<Tank>> tanks;

    private static final double TARGET_TTA = 0.14;

    TankAnalyserImpl() {
        tanks = TankContainer.INSTANCE.getTanks();
    }

    @Override
    public double getTotalVolume() {
        return tanks.get(TANK_TYPE.PRIMARY).stream()
                .mapToDouble(Tank::getVolume)
                .sum();
    }

    @Override
    public double getAverageBrix() {
        return tanks.get(TANK_TYPE.PRIMARY).stream()
                    .mapToDouble(Tank::getBrixValue)
                    .average()
                    .getAsDouble();
    }

    @Override
    public double getAverageTta() {
        return tanks.get(TANK_TYPE.PRIMARY).stream()
                    .mapToDouble(Tank::getTtaValue)
                    .average()
                    .getAsDouble();
    }

    @Override
    public double getAcidityDifference() {
        return TARGET_TTA - getAverageTta();
    }

    @Override
    public double getAcidifierTTALevelVsBlends() {
        return tanks.get(TANK_TYPE.ACIDIFIER).get(0).getTtaValue() / getAverageTta();
    }

}
