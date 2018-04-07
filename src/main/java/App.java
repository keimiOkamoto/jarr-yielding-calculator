//import domain.*;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class App {
//    public static void main(String[] args) {
//        Tank primaryTank1 = new KombuchaTank(800, 5.2, 0.126);
//        Tank primaryTank2 = new KombuchaTank(800, 5.1, 0.134);
//        Tank acidifierTank = new KombuchaTank(800, 2.2, 0.852);
//
//        List<Tank> primaryTanks = new ArrayList<>();
//        primaryTanks.add(primaryTank1);
//        primaryTanks.add(primaryTank2);
//
//        List<Tank> acidifierTanks = new ArrayList<>();
//        acidifierTanks.add(acidifierTank);
//
//        Map<TANK_TYPE, List<Tank>> tanks = new HashMap<>();
//        tanks.put(TANK_TYPE.PRIMARY, primaryTanks);
//        tanks.put(TANK_TYPE.ACIDIFIER, acidifierTanks);
//        TankContainer.INSTANCE.setTanks(tanks);
//
//        TankAnalyser tankAnalyser = new TankAnalyserImpl(TankContainer.INSTANCE);
//        JarrCalculatorService brixAndAcidCalculatorService = new BrixAndAcidCalculatorService(tankAnalyser);
//
//        double tta = brixAndAcidCalculatorService.calculate(PROPERTY_TYPE.TTA);
//        double brix = brixAndAcidCalculatorService.calculate(PROPERTY_TYPE.BRIX);
//
//        System.out.println(tta + " of TTA needed to match target");
//        System.out.println(brix + " of Brix needed to match target");
//    }
//}
