import java.util.ArrayList;
import java.util.List;

public enum TankContainer {
    INSTANCE;

    private List<Tank> tanks = new ArrayList<>();

    public void setTanks(List<Tank> tanks) {
        this.tanks = tanks;
    }

    public List<Tank> getTanks() {
        return tanks;
    }

}
