package fr.mla.fractals4j;

import javax.swing.*;
import java.io.File;

public class Fractals4jFrame extends JFrame {

	private static final long serialVersionUID = 1L;


	public Fractals4jFrame(int width, int height) {
		super("Fractals4j");
		Fractals4jComponent drawing = new Fractals4jComponent(width, height);
		getContentPane().add(new JScrollPane(drawing));
	}

	public static void main(String[] args) {

		if (args.length < 3) {
			usage();
		}

		int widthParam = 0;
		int heightParam = 0;
		String workingDirectory = null;

		try {
			widthParam = Integer.parseInt(args[0]);
			heightParam = Integer.parseInt(args[1]);
			workingDirectory = args[2];
		} catch (NumberFormatException e) {
			usage();
		}

		File wd = new File(workingDirectory);
		if (!wd.exists() || !wd.isDirectory()) {
			usage();
		}

		AppConst.workingDirectory = workingDirectory;

		final int width = widthParam;
		final int height = heightParam;

		SwingUtilities.invokeLater(() -> createAndShowGUI(width, height));

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
