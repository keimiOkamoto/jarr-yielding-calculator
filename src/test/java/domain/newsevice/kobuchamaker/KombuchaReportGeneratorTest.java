package domain.newsevice.kobuchamaker;

import domain.KombuchaTank;
import domain.Tank;
import domain.newsevice.PropertyManager.BrixAdder;
import domain.newsevice.propertycalculators.BrixCalculator;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;

public class KombuchaReportGeneratorTest {

    private KombuchaReportGenerator kombuchaReportGenerator;

    private List<Tank> tanks;


    @Test
    public void shouldWorkOutHowMuchBrixIsNeededToReachTargetBrix() {
        List<Tank> tanks = Arrays.asList(new KombuchaTank(800, 4.9, 0.188), new KombuchaTank(800, 4.8, 0.160));

        KombuchaPropertyValueManager waterPropertyValueManager = new KombuchaPropertyValueManager(new BrixCalculator(), new BrixAdder());

        kombuchaReportGenerator = new KombuchaReportGenerator(tanks, waterPropertyValueManager);
        kombuchaReportGenerator.generateReport();
    }

}