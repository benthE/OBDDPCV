package DeJongFormulas;

/**
 * Created by Benoit on 02/05/2014.
 */
public class FirstFunc implements FitnessCalc1{

    @Override
    public double utilityFunction(Individual X){
        double res = 0;
        double function = function(X.getValues(0), null);
        for (int i = 1; i < X.getChromSize(); i++) {
            res += function;
            function = function(X.getValues(i), null);
        }
        return res;
    }

    @Override
    public double function(double x, Individual X){
        double res = 0;
        for (int i = 1; i <= 3; i++) {
            res += x * x;
        }
        return res;
    }
}
