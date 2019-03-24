import domain.KombuchaTank;
import domain.Report;
import domain.Tank;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Tank primaryTank1 = new KombuchaTank(800, 4.9, 0.188);
        Tank primaryTank2 = new KombuchaTank(800, 4.8, 0.160);

        List<Tank> tanks = new ArrayList<>();
        tanks.add(primaryTank1);
        tanks.add(primaryTank2);

        JarrCalculatorService jarrCalculatorService = new Calculator(tanks);
        Report report = jarrCalculatorService.calculate();

        System.out.println("Water needed per tank: " + report.getWaterAdjustment());
        System.out.println("Water needed per tank: " + report.getBrixAdjustment());

    }
}
