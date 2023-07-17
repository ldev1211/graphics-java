package model.shape;

import config.Constants;
import model.Coordinate;
import model.PixelPoint;

import javax.swing.*;
import java.awt.*;

public class Circle extends Shape{
    private JFrame frame;
    private Coordinate central;
    private int radius;

    public Circle(JFrame frame, Coordinate central, int radius) {
        this.frame = frame;
        this.central = central;
        this.radius = radius;
    }

    public void drawShape(){
        int x = 0;
        int y = radius;
        int p = 1 - radius;

        while (x <= y) {
            drawPoints(x, y);
            x++;
            if (p < 0) {
                p += 2 * x + 1;
            } else {
                y--;
                p += 2 * (x - y) + 1;
            }
        }
    }
    private void drawPoints(int x, int y) {
        PixelPoint pixelPoint1 = new PixelPoint(new Coordinate(central.getX() + x,central.getY() + y).cvtUserCoorToMachineCoor(Constants.ORIGIN_X, Constants.ORIGIN_Y),Color.BLACK);
        super.putPixel(frame, pixelPoint1);
        super.coordinateHashMap.put(pixelPoint1.getCoordinate().toString(),pixelPoint1);
        PixelPoint pixelPoint2 = new PixelPoint(new Coordinate(central.getX() - x,central.getY() + y).cvtUserCoorToMachineCoor(Constants.ORIGIN_X, Constants.ORIGIN_Y),Color.BLACK);
        super.putPixel(frame, pixelPoint2);
        super.coordinateHashMap.put(pixelPoint2.getCoordinate().toString(),pixelPoint2);
        PixelPoint pixelPoint3 = new PixelPoint(new Coordinate(central.getX() + x, central.getY() - y).cvtUserCoorToMachineCoor(Constants.ORIGIN_X, Constants.ORIGIN_Y),Color.BLACK);
        super.putPixel(frame, pixelPoint3);
        super.coordinateHashMap.put(pixelPoint3.getCoordinate().toString(),pixelPoint3);
        PixelPoint pixelPoint4 = new PixelPoint(new Coordinate(central.getX() - x, central.getY() - y).cvtUserCoorToMachineCoor(Constants.ORIGIN_X, Constants.ORIGIN_Y),Color.BLACK);
        super.putPixel(frame, pixelPoint4);
        super.coordinateHashMap.put(pixelPoint4.getCoordinate().toString(),pixelPoint4);
        PixelPoint pixelPoint5 = new PixelPoint(new Coordinate(central.getX() + y, central.getY() + x).cvtUserCoorToMachineCoor(Constants.ORIGIN_X, Constants.ORIGIN_Y),Color.BLACK);
        super.putPixel(frame, pixelPoint5);
        super.coordinateHashMap.put(pixelPoint5.getCoordinate().toString(),pixelPoint5);
        PixelPoint pixelPoint6 = new PixelPoint(new Coordinate(central.getX() - y, central.getY() + x).cvtUserCoorToMachineCoor(Constants.ORIGIN_X, Constants.ORIGIN_Y),Color.BLACK);
        super.putPixel(frame, pixelPoint6);
        super.coordinateHashMap.put(pixelPoint6.getCoordinate().toString(),pixelPoint6);
        PixelPoint pixelPoint7 = new PixelPoint(new Coordinate(central.getX() + y, central.getY() - x).cvtUserCoorToMachineCoor(Constants.ORIGIN_X, Constants.ORIGIN_Y),Color.BLACK);
        super.putPixel(frame, pixelPoint7);
        super.coordinateHashMap.put(pixelPoint7.getCoordinate().toString(),pixelPoint7);
        PixelPoint pixelPoint8 = new PixelPoint(new Coordinate(central.getX() + -y,central.getY() + -x).cvtUserCoorToMachineCoor(Constants.ORIGIN_X, Constants.ORIGIN_Y),Color.BLACK);
        super.putPixel(frame, pixelPoint8);
        super.coordinateHashMap.put(pixelPoint8.getCoordinate().toString(),pixelPoint8);
    }
}
