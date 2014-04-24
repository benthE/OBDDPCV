package TestingStuff;

/**
 * Created by Benoit on 18/04/2014.
 */
public class UtilityFunction {
    private static int elements[][];
    private static int N;
    private static int PopulationSize;

    public UtilityFunction() {
        N = 4;
        PopulationSize = 5;
    }

    public double firstDeJongFunction() {
        double res = 0;
        for (int i = 1; i <= 3; i++) {
            //    res += (/*xi*/ * /*xi*/);
        }
        return res;
    }

    public double coreFuntion(double resDJF) {
        double res = 0;
        for (int i = 1; i <= N; i++) {
            res += firstDeJongFunction();
        }
        return res;
    }
}
