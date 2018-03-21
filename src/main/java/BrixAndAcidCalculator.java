public class BrixAndAcidCalculator {

    private TankAnalyser tankAnalyser;

    public BrixAndAcidCalculator(TankAnalyser tankAnalyser){
        this.tankAnalyser = tankAnalyser;
    }

    public double calculate(){
        return tankAnalyser.calculate();
    }

}
