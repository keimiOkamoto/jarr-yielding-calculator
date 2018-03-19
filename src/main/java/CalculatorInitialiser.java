public class CalculatorInitialiser {

    private final double brixBaseValue;
    private final double ttaBaseValue;

    private CalculatorInitialiser(double brixBaseValue, double ttaBaseValue) throws AssertionError {
        this.brixBaseValue = brixBaseValue;
        this.ttaBaseValue = ttaBaseValue;
    }

    public static CalculatorInitialiser getInstance(double brixBaseValue, double ttaBaseValue) {
        return new CalculatorInitialiser(brixBaseValue, ttaBaseValue);
    }

    public double getBrixBaseValue() {
        return brixBaseValue;
    }

    public double getTtaBaseValue() {
        return ttaBaseValue;
    }
}
