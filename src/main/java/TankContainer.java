import domain.Tank;

import java.util.*;

public enum TankContainer {
    INSTANCE;

    private Map tanks = Collections.EMPTY_MAP;

    public void setTanks(Map<TANK_TYPE, List<Tank>> tanks) {
        this.tanks = tanks;
    }

    public Map getTanks() {
        return tanks;
    }
}
