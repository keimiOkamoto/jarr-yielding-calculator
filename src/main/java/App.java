import domain.KombuchaTank;
import domain.Tank;

import java.util.Arrays;

public class App {
    public static void main(String[] args) {
        Tank tank1 = new KombuchaTank(800, 5.2, 0.126);
        Tank tank2 = new KombuchaTank(800, 5.1, 0.134);
        Tank acidifierTank = new KombuchaTank(800, 2.2, 0.852);

        BrixAndAcidCalculator tta = new BrixAndAcidCalculator(new TTAAnalyser(Arrays.asList(tank1, tank2), acidifierTank));
        double ttaValue = tta.calculate();

        BrixAndAcidCalculator brix = new BrixAndAcidCalculator(new BrixAnalyser(ttaValue, Arrays.asList(tank1, tank2), acidifierTank));
        double brixValue = brix.calculate();

        System.out.println("TTA value: " + ttaValue + " Brix value: " + brixValue);
    }
}
