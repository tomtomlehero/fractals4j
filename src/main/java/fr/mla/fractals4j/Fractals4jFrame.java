package fr.mla.fractals4j;

import javax.swing.*;
import java.io.File;

/*
	Algorithms used in this soft are from Robert P. Munafo's
	Mu-Ency - The Encyclopedia of the Mandelbrot Set
	http://mrob.com/pub/muency.html
*/

public class Fractals4jFrame extends JFrame {

	private static final long serialVersionUID = 1L;


	public Fractals4jFrame(int width, int height) {
		super("Fractals4j");
		Fractals4jComponent drawing = new Fractals4jComponent(width, height);
		getContentPane().add(new JScrollPane(drawing));
	}

	public static void main(String[] args) {

		if (args.length < 7) {
			usage();
		}

		double defaultX0 = 0.0;
		double defaultX1 = 0.0;
		double defaultY0 = 0.0;
		double defaultY1 = 0.0;
		int width = 0;
		int height = 0;
		String workingDirectory = null;

		try {
			defaultX0 = Double.parseDouble(args[0]);
			defaultX1 = Double.parseDouble(args[1]);
			defaultY0 = Double.parseDouble(args[2]);
			defaultY1 = Double.parseDouble(args[3]);
			width = Integer.parseInt(args[4]);
			height = Integer.parseInt(args[5]);
			workingDirectory = args[6];
		} catch (NumberFormatException e) {
			usage();
		}

		File wd = new File(workingDirectory);
		if (!wd.exists() || !wd.isDirectory()) {
			usage();
		}

		AppConst.DEFAULT_X0 = defaultX0;
		AppConst.DEFAULT_X1 = defaultX1;
		AppConst.DEFAULT_Y0 = defaultY0;
		AppConst.DEFAULT_Y1 = defaultY1;

		AppConst.workingDirectory = workingDirectory;

		final int finalWidth = width;
		final int finalHeight = height;

		SwingUtilities.invokeLater(() -> createAndShowGUI(finalWidth, finalHeight));

	}

	private static void createAndShowGUI(int width, int height) {
		JFrame fractals4jFrame = new Fractals4jFrame(width, height);
		fractals4jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fractals4jFrame.pack();
		fractals4jFrame.setVisible(true);
	}


	private static void usage() {
		System.out.println("Usage : WIDTH HEIGHT WORKING_DIRECTORY");
		System.out.println("WORKING_DIRECTORY must be a valid directory");
		System.exit(1);
	}


}
