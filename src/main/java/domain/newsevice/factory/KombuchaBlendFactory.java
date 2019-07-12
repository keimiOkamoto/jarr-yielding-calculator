package domain.newsevice.factory;

import domain.KombuchaTank;
import domain.Tank;

import java.util.List;
import java.util.stream.Collectors;

public enum KombuchaBlendFactory {
    INSTANCE;

    public Tank getInitialTank(List<Tank> tanks) {
        Double tta = tanks.stream().collect(Collectors.averagingDouble(Tank::getTtaValue));
        Double brix = tanks.stream().collect(Collectors.averagingDouble(Tank::getBrixValue));
        return new KombuchaTank(800, brix, tta);
    }
}
