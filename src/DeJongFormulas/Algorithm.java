package DeJongFormulas;

/**
 * Created by Benoit on 18/04/2014.
 */
public class Algorithm {
    /*GA parameters*/
    private static double uniformRate = 0.5;
    private static double mutationRate = 0.05;
    private static int tournamentSize = 5;

    public static Population evolvePopulation(Population pop){
        Population newPopulation = new Population(pop.size());

        int i = 0;
        while(i!=pop.size()){
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
            i++;
        }

        while(i!=newPopulation.size()){
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    private static Individual crossover(Individual indiv1, Individual indiv2){
        Individual newSol = new Individual();
        for(int i=0; i<indiv1.size(); i++){
            if(Math.random()<=uniformRate){
                newSol.setValue(indiv1.getValue());
            }
            else{
                newSol.setValue(indiv2.getValue());
            }
        }
        return newSol;
    }

    private static void mutate(Individual indiv){
        for(int i=0; i<indiv.size(); i++){
            if(Math.random()<=mutationRate){
                int operationType = (int)Math.random();
                switch(operationType){
                    default :
                        indiv.setValue((float) (indiv.getValue()+Math.random()));
                        break;
                    case 1 :
                        indiv.setValue((float) (indiv.getValue()-Math.random()));
                        break;
                    case 2 :
                        indiv.setValue((float) (indiv.getValue()*Math.random()));
                        break;
                    case 3 :
                        indiv.setValue((float) (indiv.getValue()/Math.random()));
                        break;
                }
            }
        }
    }

    private static Individual tournamentSelection(Population pop){
        Population tournament = new Population(tournamentSize);
        for(int i=0; i<tournamentSize; i++){
            int randomId = (int) (Math.random()*pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}
