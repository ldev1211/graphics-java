package model.shape;

import model.Coordinate;
import model.PixelPoint;

import javax.swing.*;
import java.awt.*;

public class Elisp extends Shape{
    private Coordinate central;
    private int majorAxis;
    private int minorAxis;

    public Elisp(Graphics graphics, Coordinate central, int majorAxis, int minorAxis) {
        super.g = graphics;
        this.central = central;
        this.majorAxis = majorAxis;
        this.minorAxis = minorAxis;
    }

    public void drawShape() {
        int a = majorAxis;
        int b = minorAxis;

        int x = 0;
        int y = b;

        double d1 = (b * b) - (a * a * b) + (0.25 * a * a);
        int dx = 2 * b * b * x;
        int dy = 2 * a * a * y;

        while (dx < dy) {
            drawPoints(x, y);
            x++;

            if (d1 < 0) {
                dx = dx + (2 * b * b);
                d1 = d1 + dx + (b * b);
            } else {
                y--;
                dx = dx + (2 * b * b);
                dy = dy - (2 * a * a);
                d1 = d1 + dx - dy + (b * b);
            }
        }

        double d2 = ((b * b) * ((x + 0.5) * (x + 0.5))) +
                ((a * a) * ((y - 1) * (y - 1))) -
                (a * a * b * b);

        while (y >= 0) {
            drawPoints(x, y);
            y--;

            if (d2 > 0) {
                dy = dy - (2 * a * a);
                d2 = d2 + (a * a) - dy;
            } else {
                x++;
                dx = dx + (2 * b * b);
                dy = dy - (2 * a * a);
                d2 = d2 + dx - dy + (a * a);
            }
        }
    }

    private void drawPoints(int x, int y) {
        PixelPoint p1 = new PixelPoint(new Coordinate(central.getX()+x, central.getY()+y), Color.WHITE);
        super.putPixel(p1);
        super.coordinateHashMap.put(p1.getCoordinate().toString(),p1);
        PixelPoint p2 = new PixelPoint(new Coordinate(central.getX()-x, central.getY()+y),Color.WHITE);
        super.putPixel(p2);
        super.coordinateHashMap.put(p2.getCoordinate().toString(),p2);
        PixelPoint p3 = new PixelPoint(new Coordinate(central.getX()+x, central.getY()-y),Color.WHITE);
        super.putPixel(p3);
        super.coordinateHashMap.put(p3.getCoordinate().toString(),p3);
        PixelPoint p4 = new PixelPoint(new Coordinate(central.getX()-x, central.getY()-y),Color.WHITE);
        super.putPixel(p4);
        super.coordinateHashMap.put(p4.getCoordinate().toString(),p4);
    }
}
