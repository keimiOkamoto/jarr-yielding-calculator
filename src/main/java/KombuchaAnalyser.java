public interface KombuchaAnalyser {
    double getTTaValue();

    double getBrixValue();

    boolean isTooAcidic();

    boolean tooMuchBrix();

    double getTotalVolume();

    double getDifferenceBetweenTtaValueAndTargetValue();

    double getDifferenceFromBrixValueToTargetValue();

    double getDifferenceFromBrixTargetToValue();

    int getTankCount();

    void setTtaValue(double value);

    void setBrixValue(double value);

}
