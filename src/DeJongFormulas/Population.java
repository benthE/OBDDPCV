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

    public Individual getFittest() {
        Individual fittest = individuals[0];
        for (int i = 0; i < size(); i++) {
            if(Algorithm.isMaximization()) {
                if (fittest.getFitness() <= getIndividual(i).getFitness())
                    fittest = getIndividual(i);
            }
            else {
                if (fittest.getFitness() >= getIndividual(i).getFitness())
                    fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    public double getAvgFitness() {
        int i = 1;
        double totalFitness = 0;
        while (i != size()) {
            totalFitness += getIndividual(i).getFitness();
            i++;
        }
        return (totalFitness / i);
    }

    public double getDeviance() {
        double mean = getAvgFitness();
        double res1 = 0;
        for (int i = 0; i < size(); i++) {
            res1 += ((getIndividual(i).getFitness() - mean) * (getIndividual(i).getFitness() - mean));
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
