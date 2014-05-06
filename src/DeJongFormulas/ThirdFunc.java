package DeJongFormulas;

/**
 * Created by Vincent on 06/05/2014.
 */
public class ThirdFunc implements FitnessCalc1 {

    @Override
    public double utilityFunction(Individual X) {
        double res = 0;
        double tmp = 0;
        for (int i = 1; i<X.getChromSize(); i++){
            if(X.getValues(i)<0){
                tmp = X.getValues(i)*(-1);
                res += tmp;
            }
            else{
                res += X.getValues(i);
            }
        }
        return res;
    }

    @Override
    public double function(double x, Individual X) {
        return 0;
    }
}
