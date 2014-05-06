package DeJongFormulas;

import java.util.Random;

/**
 * Created by Benoit on 18/04/2014.
 */
public class Algorithm {
    /*GA parameters*/
    private static double uniformRate = 0.5;
    private static double mutationRate = 0.05;
    private static int tournamentSize = 5;
    private static boolean maximization = true;
    /*
    TODO : Standard optimization method
    private static double approximation = 0;
    */
    protected static double idealSolution;

    public static void setSolution(double newSolution) {
        idealSolution = newSolution;
    }

    public static double getIdealSolution() {
        return idealSolution;
    }

    /*
    TODO : Standard optimization method
    public static double getApproximation() {
        return approximation;
    }

    public static void setApproximation(double approximation) {
        Algorithm.approximation = approximation;
    }

    public static boolean isClosest(){
        if(getIdealSolution()<getApproximation()){
            return true;
        }
        else{
            return false;
        }
    }
    */

    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size());

        int i = 0;
        while (i != pop.size()) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            mutate(newIndiv);
            newPopulation.saveIndividual(i, newIndiv);
            i++;
        }

        return newPopulation;
    }

    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        for (int i = 0; i < indiv1.getChromSize(); i++) {
            if (Math.random() <= uniformRate) {
                newSol.setValues(indiv1.getValues(i), i);
            } else {
                newSol.setValues(indiv2.getValues(i), i);
            }
        }
        return newSol;
    }

    private static void mutate(Individual indiv) {
        for (int i = 0; i < indiv.getChromSize(); i++) {
            if (Math.random() <= mutationRate) {
                Random randomno = new Random();
                indiv.setValues(indiv.getValues(i) * (1 + randomno.nextGaussian()), i);
            }
        }
    }

    private static Individual tournamentSelection(Population pop) {
        Population tournament = new Population(tournamentSize);
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
    }

    public static boolean isMaximization() {
        return maximization;
    }

    public static void setMaximization(boolean maximization) {
        Algorithm.maximization = maximization;
    }

    public double getMaxFitness(Population myPop) {
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
