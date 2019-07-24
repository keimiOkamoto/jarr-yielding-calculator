package domain.newsevice.domain;

public enum TargetPropertyValue {
    TTA_TARGET(0.14),
    BRIX_TARGET(5.0);

    public double get() {
        return propertyValue;
    }

    private double propertyValue;

    TargetPropertyValue (double propertyValue) {
        this.propertyValue = propertyValue;
    }
}
