package fr.mla.fractals4j.algorithm;

/**
 * Created by mathieu on 01/12/14.
 */
public class Orbit {

    private static double escapeRadiusSq = 4;

    private static final double ln2 = Math.log(2.0);
    private static final double log2log2escapeRadius = log2(log2(escapeRadiusSq));


    public static int dwell(double xc, double yc, int dwellLimit) {

        int n = 0;
        double x = 0;
        double y = 0;

        double modCsq = 0;

        while ((n < dwellLimit) && (modCsq < escapeRadiusSq)) {
            double tmpX = x * x - y * y + xc;
            double tmpY = 2 * x * y + yc;
            x = tmpX;
            y = tmpY;
            modCsq = x * x + y * y;
            n++;
        }

        return n;
    }



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
        double x = 0;
        double y = 0;

        double modCsq = 0;

        while ((n < dwellLimit) && (modCsq < escapeRadiusSq)) {
            double tmpX = x * x - y * y + xc;
            double tmpY = 2 * x * y + yc;
            x = tmpX;
            y = tmpY;
            modCsq = x * x + y * y;
            n++;
        }

        return n + log2(log2(Math.sqrt(modCsq))) - log2log2escapeRadius;
    }


    private static double log2(double x) {

        return Math.log(x) / ln2;
    }
}
