package fr.mla.fractals4j;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

public class Fractals4jComponent extends JComponent implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	private static final int width = 800;
	private static final int height = 600;

	private FractalView fractalView;

	public Fractals4jComponent() {
		setPreferredSize(new Dimension(width, height));
		addMouseListener(this);
		addMouseMotionListener(this);

		fractalView = new FractalView(-2.0, 1.0, -1.0, 1.0, width, height, 250, "C:\\Users\\MHDB4820\\Desktop\\fractals4jWorkshop");
		fractalView.doSomething();

	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(fractalView.getImage(), null, 0, 0);

	}

	
	
	
	// MouseListener implementation
	
	@Override
	public void mouseClicked(MouseEvent e) {
		displayMouseEventInfo(e, "mouseClicked");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		displayMouseEventInfo(e, "mousePressed");

		if (e.getButton() == MouseEvent.BUTTON1) {
			startDetectingLongMousePress();
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		displayMouseEventInfo(e, "mouseReleased");

		if (e.getButton() == MouseEvent.BUTTON1) {
			stopDetectingLongMousePress();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		displayMouseEventInfo(e, "mouseEntered");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		displayMouseEventInfo(e, "mouseExited");
	}

	// MouseMotionListener implementation
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
		displayMouseEventInfo(e, "mouseDragged");
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		displayMouseEventInfo(e, "mouseMoved");
	}

	private static void displayMouseEventInfo(MouseEvent e, String label) {
//		System.out.println("### " + label + " #############");
//		System.out.println(" Button = " + e.getButton());
//		System.out.println(" ClickCount = " + e.getClickCount());
//		System.out.println(" Modifiers = " + e.getModifiers());
//		System.out.println(" ModifiersEx = " + e.getModifiersEx());
//		System.out.println(" X = " + e.getX());
//		System.out.println(" Y = " + e.getY());
//		System.out.println(" XOnScreen = " + e.getXOnScreen());
//		System.out.println(" YOnScreen = " + e.getYOnScreen());
//		System.out.println(" LocationOnScreen.x = " + e.getLocationOnScreen().x);
//		System.out.println(" LocationOnScreen.y = " + e.getLocationOnScreen().y);
//		System.out.println(" Point.x = " + e.getPoint().x);
//		System.out.println(" Point.y = " + e.getPoint().y);
//		System.out.println(" Component Class = " + e.getComponent().getClass().getName());
//		System.out.println(" Source = " + e.getSource());
	}


/* ---------------------------------------------------------
 	Long Mouse Press Detection
----------------------------------------------------------*/
	private Thread longMousePressDetectionThread;

	private void startDetectingLongMousePress() {

		this.longMousePressDetectionThread = new Thread(
				() -> {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						return;
					}

					onLongMousePressDetected();
				});

		this.longMousePressDetectionThread.start();

	}

	private void stopDetectingLongMousePress() {
		if (this.longMousePressDetectionThread != null) {
			this.longMousePressDetectionThread.interrupt();
			this.longMousePressDetectionThread = null;
		}

	}

	private void onLongMousePressDetected() {
		System.out.println("GO !!!!!!!!!!!!!!!!!!!!");
	}

}
