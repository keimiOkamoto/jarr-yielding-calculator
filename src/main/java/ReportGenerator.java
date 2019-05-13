import domain.Report;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ReportGenerator implements JarrCalculatorService {


    private Report report;
    private BlendGenerator blendGenerator;

    public ReportGenerator(Report report, BlendGenerator blendGenerator) {
        this.report = new Report();
        this.blendGenerator = blendGenerator;
    }


    public Report generateReport() {
        return report;
    }

    private double convertToTwoDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    private double convertToThreeDecimalPlaces(double number) {
        return new BigDecimal(number).setScale(3, RoundingMode.HALF_UP).doubleValue();
    }
}
