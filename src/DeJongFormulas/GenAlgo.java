package DeJongFormulas;

/**
 * Created by Benoit on 18/04/2014.
 */
public class GenAlgo {

    public static void main(String[] args){

        //Set a ideal solution
        FitnessCalc.setSolution(0);

        //Generate G0 (First population)
        Population myPop = new Population(20);

        //Evolve our population 'til we get to our optimum
        int generationCounter = 0;
        while(myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()){
            generationCounter++;
            System.out.println("Generation: "+generationCounter+" Fittest : "+ myPop.getFittest().getFitness());
            myPop = Algorithm.evolvePopulation(myPop);
        }
        System.out.println("Solution found !");
        System.out.println("Generation : " + generationCounter);
        System.out.println(myPop.getFittest());
    }
}
