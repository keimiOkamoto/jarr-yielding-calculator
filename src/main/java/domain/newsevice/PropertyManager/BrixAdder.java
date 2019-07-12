package domain.newsevice.PropertyManager;

import domain.Tank;

public class BrixAdder implements KombuchaPropertyAdder {

    @Override
    public Tank add(double kgs, Tank blend) {
        double newBrix = ((kgs * 100) / (blend.getVolume() * 2)) + blend.getBrixValue();
        blend.setBrixValue(newBrix);
        return blend;
    }
}
