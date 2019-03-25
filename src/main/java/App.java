import domain.KombuchaTank;
import domain.Report;
import domain.Tank;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
//        Tank primaryTank1 = new KombuchaTank(800, 4.9, 0.148);
//        Tank primaryTank2 = new KombuchaTank(800, 4.8, 0.160);
//
//        List<Tank> tanks = new ArrayList<>();
//        tanks.add(primaryTank1);
//        tanks.add(primaryTank2);
//
//        JarrCalculatorService jarrCalculatorService = new Calculator(tanks);
//        Report report = jarrCalculatorService.calculate();
//
//        System.out.println("Water needed per tank: " + Math.round((report.getWaterAdjustment()) * 10)/ 10.0 + "litres of water needed");
//        System.out.println("Water needed per tank: " + Math.round((report.getBrixAdjustment()) * 10)/ 10.0 + "kg sugar needed");
//


        Tank primaryTank1 = new KombuchaTank(800, 5.1, 0.124);
        Tank primaryTank2 = new KombuchaTank(800, 5.1, 0.108);
        Tank primaryTank3 = new KombuchaTank(800, 5.3, 0.104);

        List<Tank> tanks = new ArrayList<>();
        tanks.add(primaryTank1);
        tanks.add(primaryTank2);
        tanks.add(primaryTank3);

        JarrCalculatorService jarrCalculatorService = new Calculator(tanks);
        Report report = jarrCalculatorService.calculate();

        System.out.println("Water needed per tank: " + Math.round((report.getWaterAdjustment()) * 10)/ 10.0 + "litres of water needed");
        System.out.println("Water needed per tank: " + Math.round((report.getBrixAdjustment()) * 10)/ 10.0 + "kg sugar needed");
    }
}
