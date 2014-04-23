package DeJongFormulas;

import java.util.Scanner;

/**
 * Created by Benoit on 18/04/2014.
 */
public class GenAlgo{

    public static void main(String[] args){

        //Set a ideal solution
        FitnessCalc.setSolution(500);

        //Generate G0 (First population)
        Population myPop = new Population(5);

        //Evolve our population 'til we get to our optimum
        int generationCounter = 1;
        while(myPop.getFittest().getFitness() < FitnessCalc.idealSolution){
            generationCounter++;
            System.out.println("Generation: "+generationCounter+" Fittest : "+ myPop.getFittest().getFitness());
            myPop = Algorithm.evolvePopulation(myPop);
        }
        System.out.println("Solution found !");
        System.out.println("Generation number : " + generationCounter);
        System.out.println(myPop.getFittest());
        System.out.println("Best Fitness : " + myPop.getFittest().getFitness());
    }
}
