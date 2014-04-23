package DeJongFormulas;

/**
 * Created by Benoit on 18/04/2014.
 *
 */
public class Population {

    private static Individual[] individuals;

    public Population(int popSize) {
        individuals = new Individual[popSize];
        for (int i=0; i<size(); i++){
            Individual newIndividual = new Individual();
            //TODO : Utility function
            //newIndividual.setFitness(newIndividual.getFitness());
            saveIndividual(i, newIndividual);
        }
    }

    public static Individual getIndividual(int index){
        return individuals[index];
    }

    public Individual getFittest(){
        Individual fittest = individuals[0];
        for(int i=0; i<size(); i++){
            if(fittest.getFitness() <= getIndividual(i).getFitness())
                fittest=getIndividual(i);
        }
        return fittest;
    }

    public int size(){
        return individuals.length;
    }

    public void saveIndividual(int index, Individual indivToSave){
        individuals[index] = indivToSave;
    }

    @Override
    public String toString(){
        String res = "Population values : \n";
        for(int i=0; i<size(); i++){
            res += "Individual number ["+i+"]\n";
            res += individuals[i];
        }
        return res;
    }
}
