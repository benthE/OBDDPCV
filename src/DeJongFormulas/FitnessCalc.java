package DeJongFormulas;

/**
 * Created by Benoit on 18/04/2014.
 */
public class FitnessCalc {
    private static float idealSolution;

    public static void setSolution(float newSolution){
        idealSolution = newSolution;
    }

    public static double getFitness(Individual indivToEval){
        indivToEval.fitness = 0;
        //H(x)

        return indivToEval.fitness;
    }

    public static double getMaxFitness(){
        int bestFitness = 0;
        //Comparison

        return bestFitness;
    }
}
