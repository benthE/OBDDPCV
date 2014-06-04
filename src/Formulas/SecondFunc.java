package Formulas;


import GeneticAlg.Individual;

/**
 * Created by Benoit on 06/05/2014.
 */
public class SecondFunc implements FitnessCalc1 {
    @Override
    public double utilityFunction(double[] v, int[] l,Individual X){
        double res = 0;
        for (int i=1; i<X.getChromSize()-1; i++){
            res += (100*Math.pow((X.getValues(i+1))-Math.pow(X.getValues(i),2),2)+Math.pow(1-X.getValues(i),2));
        }
        return res;
    }

    @Override
    public  double function(double x, Individual X){return 0;};
}
