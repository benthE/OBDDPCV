package DeJongFormulas;

import java.io.*;

/**
 * Created by Benoit on 18/04/2014.
 */
public class GenAlgo{

    public static void main(String[] args){
        try{
        //Set a ideal solution
        FitnessCalc.setSolution(500);

        //Generate G0 (First population)
        Population myPop = new Population(5);

        //Evolve our population 'til we get to our optimum
        int generationCounter = 0;
        PrintWriter writer = new PrintWriter("results.txt", "UTF-8");
        PrintStream out = new PrintStream(new FileOutputStream("results.txt"));
        System.out.println("Generation Number, Best Fitness, Average Fitness, Deviance");
        while(myPop.getFittest().getFitness() < FitnessCalc.idealSolution){
            generationCounter++;
            System.out.println(generationCounter +" , "+ myPop.getFittest().getFitness()+" , "+myPop.getAvgFitness()+" , "+myPop.getDeviance());
            myPop = Algorithm.evolvePopulation(myPop);
            out.println(generationCounter +" , "+ myPop.getFittest().getFitness()+" , "+myPop.getAvgFitness()+" , "+myPop.getDeviance());
        }
        System.out.println("Solution found !");
        System.out.println("Generation number : " + generationCounter);
        System.out.println(myPop.getFittest());
        System.out.println("Best Fitness : " + myPop.getFittest().getFitness());
        out.close();
        writer.close();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
