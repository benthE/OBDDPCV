package DeJongFormulas;

import simpleGA.*;

/**
 * Created by Benoit on 18/04/2014.
 *
 */
public class Individual extends simpleGA.Individual {
    private float value;
    private float maxVal, minVal;
    private float fitness;

    //create a random guy
    public Individual() {
        value = randRange(minVal,maxVal);
    }

    private float randRange(float min, float max) {
        return min + (float)Math.random() * (max - min);
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getMaxVal() {
        return maxVal;
    }

    public void setMaxVal(float maxVal) {
        this.maxVal = maxVal;
    }

    public float getMinVal() {
        return minVal;
    }

    public void setMinVal(float minVal) {
        this.minVal = minVal;
    }

    public float getFitness() {
        if (fitness == 0.0) {
            fitness = simpleGA.FitnessCalc.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        return "Individual{" +
                "value=" + value +
                '}';
    }
}
