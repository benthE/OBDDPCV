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
    public static String filename2;

    public static void main(String[] args) {
        try {
            FitnessCalc1 FitnessFunc;
            String testfile = "C:\\Users\\Vincent\\Documents\\GitHub\\Exercises\\testFiles\\A";

            /*Initialisation*/
            FitnessFunc = new DCS();
            Algorithm.setMaximization(true);
            Algorithm.setSolution(0);
            Individual.setChromSize(26);
            int generationCounter = 0;
            DateFormat dateFormat = new SimpleDateFormat("_dd_MM_hh_mm");
            Date date = new Date();
            filename = "DCS_Results"+dateFormat.format(date)+".txt";
            filename2 = "resultToPrint"+dateFormat.format(date)+".txt";
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            PrintWriter writer2 = new PrintWriter(filename2, "UTF-8");
            PrintStream out = new PrintStream(new FileOutputStream(filename));
            PrintStream out2 = new PrintStream(new FileOutputStream(filename2));

            /*Fetching values into an individual*/
            RealLabelledData testingData = RealLabelledDataFactory.dataFromTextFile(testfile, -1, null);
            int[] indexes = {4,5};
            RealLabelledData testingDataLabels = RealLabelledDataFactory.selectLabels(testingData, indexes);
            ObservationReal[] patients = testingDataLabels.getArrayList().toArray(new ObservationReal[0]);

            /*Putting my patient values into my original model ones*/
            Population myPop = new Population(patients.length);
            for(int j=0; j<patients.length; j++) {
                for (int i = 0; i < patients[j].getAttributeSize(); i++) {
                    myPop.getIndividual(j).setValues(patients[j].getAttribute(i), i);
                }
            }

            /*Beginning the Genetic Algorithm*/
            System.out.println("Generation Number, Best Fitness, Average Fitness, Deviance");
            out.println("#DCS calculation");
            out.println("#Chromosome size : " + myPop.getIndividual(0).getChromSize());
            out.println("#Population size : " + patients.length);
            out.println("#Crossover rate : " + Algorithm.getUniformRate() + " , Mutation rate : " + Algorithm.getMutationRate() + " , Tournament size : " + Algorithm.getTournamentSize());
            out.println("Generation.Number, Best.Fitness, Average.Fitness, Fitness.Deviance");
            out2.println("Projection, Label");

            while (generationCounter != 100) {
                /*Projecting*/
                LinProj P1 = new LinProj(26);
                for (int i = 0; i < myPop.getIndividual(1).getChromSize(); i++) {
                    P1.w[i] = myPop.getIndividual(1).getValues(i);
                }

                int[] labeldata = new int[patients.length];
                double[] projdata = new double[patients.length];
                for(int i = 0; i<patients.length; i++){
                    projdata[i] = P1.project(patients[i].getAttributeValues());
                    labeldata[i] = patients[i].getLabel();
                    out2.println(projdata[i] +" "+ labeldata[i]);
                }

                /*Evaluating and Evolving*/
                generationCounter++;
                System.out.println(generationCounter + " , " + myPop.getFittest(FitnessFunc, projdata, labeldata).getFitness(FitnessFunc, projdata, labeldata) + " , " + myPop.getAvgFitness(FitnessFunc, projdata, labeldata) + " , " + myPop.getDeviance(FitnessFunc, projdata, labeldata));
                myPop = Algorithm.evolvePopulation(myPop, FitnessFunc, projdata, labeldata);
                out.println(generationCounter + " " + myPop.getFittest(FitnessFunc, projdata, labeldata).getFitness(FitnessFunc, projdata, labeldata) + " " + myPop.getAvgFitness(FitnessFunc, projdata, labeldata) + " " + myPop.getDeviance(FitnessFunc, projdata, labeldata));
            }

            System.out.println("Finished in " + generationCounter + " generations !");

            out.close();
            out2.close();
            writer.close();
            writer2.close();

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
