package Formulas;

import GeneticAlg.Individual;

/**
 * Created by Benoit on 06/05/2014.
 */
public class FifthFunc implements FitnessCalc1 {
    private int[][] a;

    @Override
    public double utilityFunction(Individual X) {
        double res = 0.002;
        double x1 = X.getValues(0);
        double x2 = X.getValues(1);
        for(int i=-2; i<=2;i++)
            for(int j=-2; j<=2; j++)
                res+=Math.pow(((5*(i+2))+j+3+Math.pow((x1-16*j),6)+Math.pow((x2-16*i),6)),-1);
        Math.pow(res, -1);
        return res;
    }

    @Override
    public double function(double x, Individual X) {
        return 0;
    }
}
