package fr.mla.fractals4j;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


// could extend JPanel either. See what's best
public class Fractals4jComponent extends JComponent implements MouseListener, MouseMotionListener {

	private static final long serialVersionUID = 1L;

	private static final int width = 800;
	private static final int height = 600;

	private FractalView fractalView;

	private RegionSelectionComponent regionSelectionComponent;

	public Fractals4jComponent() {
		addMouseListener(this);
		addMouseMotionListener(this);
		setLayout(null);
		setBorder(BorderFactory.createLineBorder(Color.black));

		this.computeAndShowFractal();
	}

	public Dimension getPreferredSize() {
		return new Dimension(width, height);
	}

	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.drawImage(fractalView.getImage(), null, 0, 0);

	}


	private void startSelectingRegion(int locationX, int locationY) {
		this.regionSelectionComponent = new RegionSelectionComponent(locationX, locationY, ((double) width) / height);

		this.add(this.regionSelectionComponent);
//		this.validate();
	}

	private void continueSelectingRegion(int locationX, int locationY) {
		if (this.regionSelectionComponent == null) {
			return;
		}

		this.regionSelectionComponent.refreshBounds(locationX, locationY);

	}

	private void finishSelectingRegion() {

		double x0 =
				this.fractalView.getX0()
						+ (this.fractalView.getX1() - this.fractalView.getX0())
						* this.regionSelectionComponent.getNorthWestX()
						/ width;

		double x1 =
				this.fractalView.getX0()
						+ (this.fractalView.getX1() - this.fractalView.getX0())
						* (this.regionSelectionComponent.getNorthWestX() + this.regionSelectionComponent.getRegionW())
						/ width;

		double y0 =
				this.fractalView.getY1()
						- (this.fractalView.getY1() - this.fractalView.getY0())
						* (this.regionSelectionComponent.getNorthWestY() + this.regionSelectionComponent.getRegionH())
						/ height;

		double y1 =
				this.fractalView.getY1()
						- (this.fractalView.getY1() - this.fractalView.getY0())
						* this.regionSelectionComponent.getNorthWestY()
						/ height;


		this.remove(this.regionSelectionComponent);
		this.repaint(
				this.regionSelectionComponent.getNorthWestX(),
				this.regionSelectionComponent.getNorthWestY(),
				this.regionSelectionComponent.getRegionW(),
				this.regionSelectionComponent.getRegionH());
		if (this.regionSelectionComponent.isSelectionDragged()) {
			computeAndShowFractal(x0, x1, y0, y1);
		}

		this.regionSelectionComponent = null;

	}


	private void computeAndShowFractal() {
		this.computeAndShowFractal(FractalView.DEFAULT_X0, FractalView.DEFAULT_X1, FractalView.DEFAULT_Y0, FractalView.DEFAULT_Y1);
	}


	private void computeAndShowFractal(double x0, double x1, double y0, double y1) {

		SwingUtilities.invokeLater(() -> {
			fractalView = new FractalView(x0, x1, y0, y1, width, height, 200);
			fractalView.doSomething();
			repaint();
		});
	}


	// MouseListener implementation
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			this.computeAndShowFractal();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			startSelectingRegion(e.getX(), e.getY());
		}
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			finishSelectingRegion();
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	// MouseMotionListener implementation
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			continueSelectingRegion(e.getX(), e.getY());
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
	}


}
