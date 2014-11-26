package fr.mla.fractals4j;

public class Fractals4jApp {

    public static void main(String[] args) {

    	FractalView fractalView = parseArgs(args);
    	if (fractalView == null) {
    		return;
    	}

    	fractalView.doSomething();

    }



	private static FractalView parseArgs(String[] args) {

		FractalView fractalView = null;

    	if (args.length < 8) {
    		usage();
    		return null;
    	}

    	try {
    		double x0 = Double.parseDouble(args[0]);
    		double x1 = Double.parseDouble(args[1]);
    		double y0 = Double.parseDouble(args[2]);
    		double y1 = Double.parseDouble(args[3]);
    		int width = Integer.parseInt(args[4]);
    		int height = Integer.parseInt(args[5]);
    		int maxIterations = Integer.parseInt(args[6]);
    		String workingDirectory = args[7];

    		fractalView = new FractalView(x0, x1, y0, y1, width, height, maxIterations, workingDirectory);

    	} catch (NumberFormatException e) {
    		usage();
    		return null;
    	}

    	return fractalView;

	}



	private static void usage() {
    	System.out.println("Usage : X0 X1 Y0 Y1 WIDTH HEIGHT MAX_ITERATION");
    }

}
