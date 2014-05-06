package DeJongFormulas;

/**
 * Created by Vincent on 06/05/2014.
 */
public class FourthFunc implements FitnessCalc1 {
    @Override
    public double utilityFunction(Individual X) {
        double res =0;
        for(int i=1; i<X.getChromSize();i++){
            res += i*Math.pow(X.getValues(i),4);
        }
        return res;
    }

    @Override
    public double function(double x, Individual X) {
        return 0;
    }
}
