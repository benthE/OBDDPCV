package DeJongFormulas;

import java.util.Random;

/**
 * Created by Benoit on 18/04/2014.
 *
 */
public class Individual extends simpleGA.Individual {
    private double values[];
    private double maxVal, minVal;
    protected double fitness;

    //create a random guy
    public Individual() {
        int n = 4;
        values = new double[n];
        setMaxVal(5.12);
        setMinVal(-5.12);
        for(int i=0; i<=(n-1); i++) {
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

    @Override
    public String toString() {
        String res = "Value : \n";
        for(int i=0; i< values.length; i++){
            res += values[i];
            res += '\n';
        }
        return res;
    }
}
