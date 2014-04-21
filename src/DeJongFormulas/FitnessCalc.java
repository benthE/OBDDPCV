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
        for(int i=0; i < indivToEval.size(); i++){
            indivToEval.fitness += sum(indivToEval.size(), indivToEval.getValues(i));
        }
        return indivToEval.fitness;
    }

    public static double sum(int n, double X) {
        double res = 0;
        double function = function(X);
        for (int i = 0; i < n; i++) {
            res += function;
        }
        return res;
    }

    public static double function(double x){
        double res = 0;
        for(int i=1; i<=3; i++){
            res += x*x;
        }
        return res;
    }

    public static double getMaxFitness(Population myPop){
        int i = 0;
        double maxFitness = myPop.getIndividual(i).getFitness();
        //Comparison
        for(i = 1; i < myPop.size(); i++){
            if (myPop.getIndividual(i).getFitness() > myPop.getIndividual(i-1).getFitness()){
                maxFitness = myPop.getIndividual(i).getFitness();
            }
        }
        return maxFitness;
    }
}
