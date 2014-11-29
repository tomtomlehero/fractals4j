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


    public RegionSelectionComponent(int originX, int originY) {
        this.originX = originX;
        this.originY = originY;
        this.currentX = originX;
        this.currentY = originY;

    }

    public void refreshBounds(int locationX, int locationY) {
        this.currentX = locationX;
        this.currentY = locationY;

        this.setBounds(
                this.getNorthEastX(),
                this.getNorthEastY(),
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

    public int getNorthEastX() {
        return (originX <= currentX ? originX : currentX);
    }

    public int getNorthEastY() {
        return (originY <= currentY ? originY : currentY);
    }

}
