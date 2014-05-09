package DeJongFormulas;

/**
 * Created by Benoit on 18/04/2014.
 */
public class Population {

    private Individual[] individuals;

    public Population(int popSize) {
        individuals = new Individual[popSize];
        for (int i = 0; i < size(); i++) {
            Individual newIndividual = new Individual();
            saveIndividual(i, newIndividual);
        }
    }

    public Individual getIndividual(int index) {
        return individuals[index];
    }

    public Individual getFittest(FitnessCalc1 F) {
        Individual fittest = individuals[0];
        for (int i = 0; i < size(); i++) {
            if(Algorithm.isMaximization()) {
                if (fittest.getFitness(F) <= getIndividual(i).getFitness(F))
                    fittest = getIndividual(i);
            }
            else {
                if (fittest.getFitness(F) >= getIndividual(i).getFitness(F))
                    fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    public double getAvgFitness(FitnessCalc1 F) {
        int i = 1;
        double totalFitness = 0;
        while (i != size()) {
            totalFitness += getIndividual(i).getFitness(F);
            i++;
        }
        return (totalFitness / i);
    }

    public double getDeviance(FitnessCalc1 F) {
        double mean = getAvgFitness(F);
        double res1 = 0;
        for (int i = 0; i < size(); i++) {
            res1 += ((getIndividual(i).getFitness(F) - mean) * (getIndividual(i).getFitness(F) - mean));
        }
        return (Math.sqrt(res1));
    }

    public int size() {
        return individuals.length;
    }

    public void saveIndividual(int index, Individual indivToSave) {
        individuals[index] = indivToSave;
    }

    @Override
    public String toString() {
        String res = "Population values : \n";
        for (int i = 0; i < size(); i++) {
            res += "Individual number [" + i + "]\n";
            res += individuals[i];
        }
        return res;
    }
}
