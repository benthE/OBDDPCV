package Formulas;

import GeneticAlg.Individual;

/**
 * Created by Benoit on 02/05/2014.
 */
public interface FitnessCalc1 {
    public double utilityFunction(double[] v, int[] l,Individual X);
    public double function(double x, Individual X);
}
