package DeJongFormulas;

import java.io.*;
import java.util.Scanner;

/**
 * Created by Benoit on 18/04/2014.
 */
public class GenAlgo {

    public static void main(String[] args) {
        try {
            int popSize, nbValues, optiChoice;
            double sol;

            Scanner s = new Scanner(System.in);

            System.out.println("What is your optimization problem ? 1 : Maximization / 2 : Minimization ");
            optiChoice = s.nextInt();
            if(optiChoice == 1){
                Algorithm.setMaximization(true);
            }
            else{
                Algorithm.setMaximization(false);
            }

            //Set a ideal solution
            System.out.println("Enter your ideal solution : ");
            sol = s.nextDouble();
            FitnessCalc.setSolution(sol);

            //Generate G0 (First population)
            System.out.println("Enter your population number of values : ");
            nbValues = s.nextInt();
            Individual.setN(nbValues);

            System.out.println("Enter your population size : ");
            popSize = s.nextInt();
            Population myPop = new Population(popSize);

            //Evolve our population 'til we get to our optimum
            int generationCounter = 0;
            PrintWriter writer = new PrintWriter("results.txt", "UTF-8");
            PrintStream out = new PrintStream(new FileOutputStream("results.txt"));
            System.out.println("Generation Number, Best Fitness, Average Fitness, Deviance");
            out.println("Generation Number, Best Fitness, Average Fitness, Fitness Deviance");
            if(Algorithm.isMaximization()) {
                while (myPop.getFittest().getFitness() < FitnessCalc.idealSolution) {
                    generationCounter++;
                    System.out.println(generationCounter + " , " + myPop.getFittest().getFitness() + " , " + myPop.getAvgFitness() + " , " + myPop.getDeviance());
                    myPop = Algorithm.evolvePopulation(myPop);
                    out.println(generationCounter + " , " + myPop.getFittest().getFitness() + " , " + myPop.getAvgFitness() + " , " + myPop.getDeviance());
                }
            }
            else{
                while (myPop.getFittest().getFitness() > FitnessCalc.idealSolution) {
                    generationCounter++;
                    System.out.println(generationCounter + " , " + myPop.getFittest().getFitness() + " , " + myPop.getAvgFitness() + " , " + myPop.getDeviance());
                    myPop = Algorithm.evolvePopulation(myPop);
                    out.println(generationCounter + " , " + myPop.getFittest().getFitness() + " , " + myPop.getAvgFitness() + " , " + myPop.getDeviance());
                }
            }
            System.out.println("Solution found !");
            System.out.println("Generation number : " + generationCounter);
            System.out.println(myPop.getFittest());
            System.out.println("Best Fitness : " + myPop.getFittest().getFitness());
            out.close();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
