package fr.mla.fractals4j;

import java.awt.Dimension;

import javax.swing.*;

public class Fractals4jFrame extends JFrame {

	private static final long serialVersionUID = 1L;


	public Fractals4jFrame() {
		super("Fractals4j");
		Fractals4jComponent drawing = new Fractals4jComponent();
		getContentPane().add(new JScrollPane(drawing));
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(() -> createAndShowGUI());
	}

	private static void createAndShowGUI() {
		JFrame fractals4jFrame = new Fractals4jFrame();
		fractals4jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fractals4jFrame.pack();
		fractals4jFrame.setVisible(true);
	}


}
