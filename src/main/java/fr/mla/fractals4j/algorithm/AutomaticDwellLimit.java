package fr.mla.fractals4j.algorithm;

import fr.mla.fractals4j.FileUtil;

/**
 * Created by mathieu on 01/12/14.
 */



public class AutomaticDwellLimit {


/*
    Histogram Method

    After a zoom, a set of points is selected (perhaps at random) and each is
    iterated up to a histogram dwell limit: a special dwell limit used just for
    making a histogram. (If this is a zoom from a previous view, the histogram
    dwell limit might be a constant times the dwell limit in that previous view).
    Once the dwells of these points are determined, a histogram is generated
    (frequency versus dwell value). An optimal dwell limit is selected in such a
    way that most of the points in the histogram fall either below this optimum
    value, or did not escape at all. In addition, the histogram is typically used
    to automatically generate a color map.
*/

    private static final int histogramDwellLimit = 5000;

    // this rate is an expression for :
    // ... in such a way that most of the points in the histogram fall either...
    private static final double threshold = 0.999;


    public static int getAutomaticDwellLimit(double x0, double x1, double y0, double y1, int width, int height) {

        int[] dwellFrequency = new int[histogramDwellLimit];

        int sampleSize = 0;

        for (int j = 0; j < height; j += 2) {
            for (int i = 0; i < width; i += 2) {

                double xc = ((width - i) * x0 + i * x1) / width;
                double yc = ((height - j) * y1 + j * y0) / height;

                int n = Orbit.process(xc, yc, histogramDwellLimit);

                dwellFrequency[n - 1]++;
                sampleSize++;
            }

        }

        FileUtil.saveIntArrayToFile(dwellFrequency);

        // ... or did not escape at all...
        int keepInSample = dwellFrequency[histogramDwellLimit - 1];
        System.out.println("Did not escape at all : " + keepInSample);
        // ... fall either below this optimum value...
        for (int d = 0; d < histogramDwellLimit - 1; d++) {
            keepInSample += dwellFrequency[d];
            if (keepInSample >= sampleSize * threshold) {
                return d;
            }
        }







    return 0;

    }




}
