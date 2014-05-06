package DeJongFormulas;

/**
 * Created by Benoit on 18/04/2014.
 */
public class FitnessCalc {
    public static double utilityFunction(int n, Individual X) {
        double res = 0;
        double function = function(X.getValues(0));
        for (int i = 1; i < n; i++) {
            res += function;
            function = function(X.getValues(i));
        }
        return res;
    }

    public static double function(double x) {
        double res = 0;
        for (int i = 1; i <= 3; i++) {
            res += x * x;
        }
        return res;
    }
}
