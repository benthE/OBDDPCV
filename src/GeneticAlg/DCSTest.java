package GeneticAlg;

import Data.ObservationReal;
import Data.RealLabelledData;
import Data.RealLabelledDataFactory;
import Formulas.DCS;
import Formulas.FitnessCalc1;
import Projection.LinProj;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Benoit on 18/04/2014.
 */
public class DCSTest {

    public static String filename;

    public static void main(String[] args) {
        try {
            FitnessCalc1 FitnessFunc;
            String testfile = "C:\\Users\\Vincent\\Documents\\GitHub\\Exercises\\testFiles\\A";

            /*Initialisation*/
            FitnessFunc = new DCS();
            Algorithm.setMaximization(false);
            Algorithm.setSolution(0);
            Individual.setChromSize(26);
            Population myPop = new Population(164);
            int generationCounter = 0;
            DateFormat dateFormat = new SimpleDateFormat("_dd_MM_hh_mm");
            Date date = new Date();
            filename = "DCS_Results"+dateFormat.format(date)+".txt";
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            PrintStream out = new PrintStream(new FileOutputStream(filename));

            /*Fetching values into an individual*/
            RealLabelledData testingData = RealLabelledDataFactory.dataFromTextFile(testfile, -1, null);
            RealLabelledData testingDataLabels[] = RealLabelledDataFactory.partitionLabels(testingData);
            ObservationReal[] individuals = new ObservationReal[164];
            for(int i=0; i<individuals.length; i++){
                individuals[i] = testingData.get(i);
            }

            /*Putting my patient values into my original model ones*/
            for(int j=0; j<individuals.length; j++) {
                for (int i = 0; i < individuals[j].getAttributeSize(); i++) {
                    myPop.getIndividual(1).setValues(individuals[i].getAttribute(i), i);
                    myPop.getIndividual(2).setValues(individuals[i].getAttribute(i), i);
                }
            }

            /*Beginning the Genetic Algorithm*/
            System.out.println("Generation Number, Best Fitness, Average Fitness, Deviance");
            out.println("#DCS calculation");
            out.println("#Chromosome size : 26");
            out.println("#Population size : 164");
            out.println("#Crossover rate : " + Algorithm.getUniformRate() + " , Mutation rate : " + Algorithm.getMutationRate() + " , Tournament size : " + Algorithm.getTournamentSize());
            out.println("#Generation Number, Best Fitness, Average Fitness, Fitness Deviance");

            while (generationCounter != 10) {
                /*Projecting for 2 individuals*/
                LinProj P1 = new LinProj(26);
                LinProj P2 = new LinProj(26);
                for(int j=0; j<individuals.length;j++) {
                    for (int i = 0; i < individuals[i].getAttributeSize(); i++) {
                        P1.w[i] = myPop.getIndividual(1).getValues(i);
                        P2.w[i] = myPop.getIndividual(2).getValues(i);
                    }
                }
                P1.project(individuals[1].getAttributeValues());
                P2.project(individuals[2].getAttributeValues());

                /*Partitioning*/
                RealLabelledDataFactory.twoPartition(testingData, 2, null);

                /*Evaluating*/
                DCS.calculate(individuals[1].getAttributeValues(), testingData.getLabelList(), 2);
                DCS.calculate(individuals[2].getAttributeValues(), testingData.getLabelList(), 2);
                System.out.println("Genome values number = " + individuals[1].getAttributeValues().length);
                System.out.println("Label 1 size = " + testingData.getLabelList().length);

                generationCounter++;
                System.out.println(generationCounter + " , " + myPop.getFittest(FitnessFunc).getFitness(FitnessFunc) + " , " + myPop.getAvgFitness(FitnessFunc) + " , " + myPop.getDeviance(FitnessFunc));
                myPop = Algorithm.evolvePopulation(myPop, FitnessFunc);
                out.println(generationCounter + " , " + myPop.getFittest(FitnessFunc).getFitness(FitnessFunc) + " , " + myPop.getAvgFitness(FitnessFunc) + " , " + myPop.getDeviance(FitnessFunc));
            }

            System.out.println("Solution found !");
            System.out.println("Generation number : " + generationCounter);
            System.out.println(myPop.getFittest(FitnessFunc));

            out.close();
            writer.close();

            /*CSV Generation into a file*/
            CSVGen.CSVGenerator.main(null);
            File file = new File(filename);

            /*Simple text file deletion*/
            if(file.delete()){
                System.out.println(file.getName() + " is deleted!");
            }else{
                System.out.println("Delete operation is failed.");
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
