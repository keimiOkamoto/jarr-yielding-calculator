package domain;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public enum TankContainer {
    INSTANCE;

    private Map<TANK_TYPE, List<Tank>> tanks = Collections.EMPTY_MAP;

    public void setTanks(Map<TANK_TYPE, List<Tank>> tanks) {
        this.tanks = tanks;
    }

    public List<Tank> getTanks(TANK_TYPE tank_type) {
        return tanks.get(tank_type);
    }
}
