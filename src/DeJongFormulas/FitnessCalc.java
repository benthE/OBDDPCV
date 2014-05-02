package DeJongFormulas;

/**
 * Created by Benoit on 18/04/2014.
 */
public class FitnessCalc {
    protected static double idealSolution;

    public static void setSolution(double newSolution) {
        idealSolution = newSolution;
    }

    public static double getIdealSolution() {
        return idealSolution;
    }

    public static double sum(int n, Individual X) {
        double res = 0;
        double function = function(X.getValues(0));
        for (int i = 1; i < n; i++) {
            res += function;
//            System.out.println("Loop number : " + i + " for function 2, function = " + function + " result : " + res);
            function = function(X.getValues(i));
        }
        return res;
    }

    public static double function(double x) {
        double res = 0;
        for (int i = 1; i <= 3; i++) {
            res += x * x;
//            System.out.println("Loop number : " + i + " for function 1, x = " + x + " result : " + res);
        }
        return res;
    }

    public static double getMaxFitness(Population myPop) {
        int i = 0;
        double maxFitness = myPop.getIndividual(i).getFitness();
        //Comparison
        for (i = 1; i < myPop.size(); i++) {
            if(Algorithm.isMaximization()) {
                if (myPop.getIndividual(i).getFitness() > myPop.getIndividual(i - 1).getFitness()) {
                    maxFitness = myPop.getIndividual(i).getFitness();
                }
            }
            else{
                if (myPop.getIndividual(i).getFitness() < myPop.getIndividual(i - 1).getFitness()) {
                    maxFitness = myPop.getIndividual(i).getFitness();
                }
            }
        }
        return maxFitness;
    }
}
