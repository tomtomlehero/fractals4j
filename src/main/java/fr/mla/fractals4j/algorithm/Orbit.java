package fr.mla.fractals4j.algorithm;

/**
 * Created by mathieu on 01/12/14.
 */
public class Orbit {

private static double escapeSquareRadius = 4;


    public static int dwell(double xc, double yc, int dwellLimit) {

        int n = 0;
        double[] z = new double[] {0.0, 0.0};
        double squareRadius = 0;

        while ((n < dwellLimit) && (squareRadius < escapeSquareRadius)) {
            iterate(z, xc, yc);

            squareRadius = z[0] * z[0] + z[1] * z[1];
            n++;
        }

        return n;
    }




//    public static int dwellWithOrbitDetection(double xc, double yc, int dwellLimit) {
//
//        return 0;
//    }


/*

    A method of calculating dwell that gives a continuous value, i.e. a value that varies smoothly
    rather than jumping from one integer to another. This allows for more variety in coloring algorithms.

    The simplest method is to choose a large escape radius, then perform the ordinary iteration algorithm.
    Let Zn be the value of the iterate that exceeds the escape radius. The normal dwell value would be n
    (as shown in the pseudo-code in the escape-iterations article). The "continuous dwell" value is given by

    D = n + log2(log2(|Zn|)) - log2(log2(EscapeRadius))

*/

    public static double continuousDwell(double xc, double yc, int dwellLimit) {

        int n = 0;
        double[] z = new double[] {0.0, 0.0};
        double squareRadius = 0;

        while ((n < dwellLimit) && (squareRadius < escapeSquareRadius)) {
            iterate(z, xc, yc);
            squareRadius = z[0] * z[0] + z[1] * z[1];
            n++;
        }

        if (n == dwellLimit) {
            return (double) n;
        } else {
            return n + log2(log2(Math.sqrt(squareRadius))) - log2log2EscapeRadius;
        }
    }






    private static double[] tmpZ = new double[] {0.0, 0.0};

    private static void iterate(double[] z, double xc, double yc) {
        tmpZ[0] = z[0] * z[0] - z[1] * z[1] + xc;
        tmpZ[1] = 2 * z[0] * z[1] + yc;
        z[0] = tmpZ[0];
        z[1] = tmpZ[1];
    }


    private static final double ln2 = Math.log(2.0);
    private static final double log2log2EscapeRadius = log2(log2(Math.sqrt(escapeSquareRadius)));


    private static double log2(double x) {

        return Math.log(x) / ln2;
    }
}
