package fr.mla.fractals4j;

import javax.swing.*;
import java.awt.*;

/**
 * Created by mathieu on 29/11/14.
 */
public class RegionSelectionComponent extends JComponent{

    private int originX;
    private int originY;
    private int currentX;
    private int currentY;

    private double aspectRatio;

    public RegionSelectionComponent(int originX, int originY, double aspectRatio) {
        this.originX = originX;
        this.originY = originY;
        this.currentX = originX;
        this.currentY = originY;

        this.aspectRatio = aspectRatio;

    }

    public void refreshBounds(int locationX, int locationY) {
        this.currentX = locationX;
        this.currentY = this.originY
                + (int) (Math.signum(locationY - this.originY) * Math.signum(locationX - this.originX))
                * new Double((locationX - this.originX) / this.aspectRatio).intValue();

        this.setBounds(
                this.getNorthWestX(),
                this.getNorthWestY(),
                this.getRegionW(),
                this.getRegionH());

        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.drawRect(0, 0, this.getRegionW(), this.getRegionH());
    }




    public int getRegionW() {
        return (originX <= currentX ? currentX - originX : originX - currentX);
    }

    public int getRegionH() {
        return (originY <= currentY ? currentY - originY : originY - currentY);
    }

    public int getNorthWestX() {
        return (originX <= currentX ? originX : currentX);
    }

    public int getNorthWestY() {
        return (originY <= currentY ? originY : currentY);
    }

}
