package DeJongFormulas;

import java.util.Random;

/**
 * Created by Benoit on 18/04/2014.
 */
public class Individual {
    protected double fitness;
    protected static int chromSize = 4;
    private double values[];
    private double maxVal, minVal;

    //create a random guy
    public Individual() {
        values = new double[chromSize];
        setMaxVal(5.12);
        setMinVal(-5.12);
        for (int i = 0; i < chromSize; i++) {
            Random r = new Random();
            values[i] = minVal + (maxVal - minVal) * r.nextDouble();
        }
    }

    public double getValues(int index) {
        return values[index];
    }

    public void setValues(double value, int index) {
        this.values[index] = value;
    }

    public double getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(double maxVal) {
        this.maxVal = maxVal;
    }

    public double getMinVal() {
        return minVal;
    }

    public void setMinVal(double minVal) {
        this.minVal = minVal;
    }

    public double getFitness() {
        fitness = 0;
        for (int i = 0; i < chromSize; i++) {
            fitness += FitnessCalc.sum(chromSize, this);
//            System.out.println("Loop number : " + i + " for utility function, individual number : " + i + ",  result : " + fitness);
        }
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public int getChromSize() {
        return chromSize;
    }

    public static void setChromSize(int n) {
        Individual.chromSize = n;
    }

    @Override
    public String toString() {
        String res = "Value : \n";
        for (int i = 0; i < values.length; i++) {
            res += values[i];
            res += '\n';
        }
        return res;
    }
}
