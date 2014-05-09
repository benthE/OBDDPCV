package DeJongFormulas;

import java.io.*;
import java.util.Scanner;


/**
 * Created by Benoit on 18/04/2014.
 */
public class GenAlgo {

    public static void main(String[] args) {
        /*TODO : REFACTOOOOOOR*/
        try {
            int popSize, nbValues, optiChoice, funcChoice;
            double sol, approx;
            FitnessCalc1 FitnessFunc;

            /*Beginning parameters input*/

            Scanner s = new Scanner(System.in);

            System.out.println("What is your optimization problem ? 1 : Maximization / 2 : Minimization");
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
            Algorithm.setSolution(sol);

            //Generate G0 (First population)
            System.out.println("Enter your chromosome size : ");
            nbValues = s.nextInt();
            Individual.setChromSize(nbValues);

            System.out.println("Enter your population size : ");
            popSize = s.nextInt();
            Population myPop = new Population(popSize);

            /*
            TODO : Standard optimization method
            System.out.println("Enter your approximation : ");
            approx = s.nextDouble();
            Algorithm.setApproximation(approx);
            */

            /*Ending parameters input*/

            //Function choice (switch case)
            System.out.println("Which De Jong's Function would you like to optimize ? (default = First Function)");
            funcChoice = s.nextInt();
            switch (funcChoice){
                default :
                    FitnessFunc = new FirstFunc();
                    break;
                case 2 :
                    FitnessFunc = new SecondFunc();
                    break;
                case 3 :
                    FitnessFunc = new ThirdFunc();
                    break;
                case 4 :
                    FitnessFunc = new FourthFunc();
                    break;
                case 5 :
                    FitnessFunc = new FifthFunc();
                    break;
            }

            //Evolve our population 'til we get to our optimum
            int generationCounter = 0;
            PrintWriter writer = new PrintWriter("results.txt", "UTF-8");
            PrintStream out = new PrintStream(new FileOutputStream("results.txt"));
            System.out.println("Generation Number, Best Fitness, Average Fitness, Deviance");
            out.println("Generation Number, Best Fitness, Average Fitness, Fitness Deviance");
            if(Algorithm.isMaximization()) {
                while (myPop.getFittest(FitnessFunc).getFitness(FitnessFunc) < Algorithm.getIdealSolution()) {
                    generationCounter++;
                    System.out.println(generationCounter + " , " + myPop.getFittest(FitnessFunc).getFitness(FitnessFunc) + " , " + myPop.getAvgFitness(FitnessFunc) + " , " + myPop.getDeviance(FitnessFunc));
                    myPop = Algorithm.evolvePopulation(myPop, FitnessFunc);
                    out.println(generationCounter + " , " + myPop.getFittest(FitnessFunc).getFitness(FitnessFunc) + " , " + myPop.getAvgFitness(FitnessFunc) + " , " + myPop.getDeviance(FitnessFunc));
                }
            }
            else{
                while (myPop.getFittest(FitnessFunc).getFitness(FitnessFunc) > Algorithm.getIdealSolution()) {
                    generationCounter++;
                    System.out.println(generationCounter + " , " + myPop.getFittest(FitnessFunc).getFitness(FitnessFunc) + " , " + myPop.getAvgFitness(FitnessFunc) + " , " + myPop.getDeviance(FitnessFunc));
                    myPop = Algorithm.evolvePopulation(myPop, FitnessFunc);
                    out.println(generationCounter + " , " + myPop.getFittest(FitnessFunc).getFitness(FitnessFunc) + " , " + myPop.getAvgFitness(FitnessFunc) + " , " + myPop.getDeviance(FitnessFunc));
                }
            }
            /*
            TODO : Implement standard optimization method (report to Algorithm)
            while(!Algorithm.isClosest()){

            }*/
            System.out.println("Solution found !");
            System.out.println("Generation number : " + generationCounter);
            System.out.println(myPop.getFittest(FitnessFunc));
            System.out.println("Best Fitness : " + myPop.getFittest(FitnessFunc).getFitness(FitnessFunc));
            out.close();
            writer.close();
            CSVGen.CSVGenerator.main(null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
