package fr.mla.fractals4j.algorithm;

/**
 * Created by mathieu on 01/12/14.
 */
public class Orbit {

    public static int process(double xc, double yc, int dwellLimit) {

        int n = 0;
        double x = 0;
        double y = 0;

        double modCsq = 0;

        while ((n < dwellLimit) && (modCsq < 4)) {
            double tmpX = x * x - y * y + xc;
            double tmpY = 2 * x * y + yc;
            x = tmpX;
            y = tmpY;
            modCsq = x * x + y * y;
            n++;
        }

        return n;
    }


}
