import domain.KombuchaTank;
import domain.Tank;

import java.util.List;
import java.util.stream.Collectors;

public class BlendAnalyser implements KombuchaAnalyser {

    private static final double TARGET_TTA = 0.140;
    private static final double TARGET_BRIX = 5.00;
    private List<Tank> baseTanks;
    private Tank blend;


    public BlendAnalyser(List<Tank> baseTanks) {
        this.baseTanks = baseTanks;
        this.blend = new KombuchaTank(0.0, 0.0, 0.0);
        setBlend(this.blend);
    }

    private void setBlend(Tank brew) {
        brew.setTtaValue(baseTanks.stream().collect(Collectors.averagingDouble(Tank::getTtaValue)));
        brew.setBrixValue(baseTanks.stream().collect(Collectors.averagingDouble(Tank::getBrixValue)));
    }

    @Override
    public boolean isTooAcidic() {
        return blend.getTtaValue() > TARGET_TTA;
    }

    @Override
    public boolean tooMuchBrix() {
        return blend.getBrixValue() > TARGET_BRIX;
    }

    @Override
    public double getTotalVolume() {
        return baseTanks.stream().mapToDouble(Tank::getVolume).sum();
    }

    @Override
    public double getDifferenceBetweenTtaValueAndTargetValue() {
        return blend.getTtaValue() - TARGET_TTA;
    }

    @Override
    public double getDifferenceFromBrixValueToTargetValue() {
        return blend.getBrixValue() - TARGET_BRIX;
    }

    @Override
    public double getDifferenceFromBrixTargetToValue() {
        return TARGET_BRIX - blend.getBrixValue();
    }

    @Override
    public int getTankCount() {
        return baseTanks.size();
    }

    @Override
    public double getTTaValue() {
        return blend.getTtaValue();
    }

    @Override
    public double getBrixValue() {
        return blend.getBrixValue();
    }

    @Override
    public void setTtaValue(double value) {
        blend.setTtaValue(value);
    }

    @Override
    public void setBrixValue(double value) {
        blend.setBrixValue(value);
    }
}
