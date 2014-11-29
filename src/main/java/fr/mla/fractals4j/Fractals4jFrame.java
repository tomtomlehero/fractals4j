package fr.mla.fractals4j;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class Fractals4jFrame extends JFrame {

	private static final long serialVersionUID = 1L;


	public Fractals4jFrame() {
		

		Fractals4jComponent drawing = new Fractals4jComponent();
		getContentPane().add(new JScrollPane(drawing));
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		
	}

	public static void main(String[] args) {
		new Fractals4jFrame();
	}





}
