package model.shape;

import model.Coordinate;
import model.PixelPoint;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Set;

public class Circle extends Shape{
    private Coordinate central;
    private int radius;

    public Circle(Graphics graphics, Coordinate central, int radius) {
        super.g = graphics;
        this.central = central;
        this.radius = radius;
    }

    public void drawShape(){
        if(coordinateHashMap.size()==0){
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
        } else {
            Set<Map.Entry<String,PixelPoint>> entries = coordinateHashMap.entrySet();
            for (Map.Entry<String,PixelPoint> entry : entries){
                super.putPixel(entry.getValue());
            }
        }
    }

    public void fillColor(Color color){
        g.setColor(color);
        g.fillOval((central.getX()-radius)*5,(central.getY()-radius)*5,(radius*2)*5,radius*2*5);
    }

    public Coordinate getCentral() {
        return central;
    }

    public void setCentral(Coordinate central) {
        this.central = central;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    private void drawPoints(int x, int y) {
        PixelPoint pixelPoint1 = new PixelPoint(new Coordinate(central.getX() + x,central.getY() + y),Color.WHITE);
        super.putPixel(pixelPoint1);
        super.coordinateHashMap.put(pixelPoint1.getCoordinate().toString(),pixelPoint1);
        PixelPoint pixelPoint2 = new PixelPoint(new Coordinate(central.getX() - x,central.getY() + y),Color.WHITE);
        super.putPixel(pixelPoint2);
        super.coordinateHashMap.put(pixelPoint2.getCoordinate().toString(),pixelPoint2);
        PixelPoint pixelPoint3 = new PixelPoint(new Coordinate(central.getX() + x, central.getY() - y),Color.WHITE);
        super.putPixel( pixelPoint3);
        super.coordinateHashMap.put(pixelPoint3.getCoordinate().toString(),pixelPoint3);
        PixelPoint pixelPoint4 = new PixelPoint(new Coordinate(central.getX() - x, central.getY() - y),Color.WHITE);
        super.putPixel( pixelPoint4);
        super.coordinateHashMap.put(pixelPoint4.getCoordinate().toString(),pixelPoint4);
        PixelPoint pixelPoint5 = new PixelPoint(new Coordinate(central.getX() + y, central.getY() + x),Color.WHITE);
        super.putPixel( pixelPoint5);
        super.coordinateHashMap.put(pixelPoint5.getCoordinate().toString(),pixelPoint5);
        PixelPoint pixelPoint6 = new PixelPoint(new Coordinate(central.getX() - y, central.getY() + x),Color.WHITE);
        super.putPixel( pixelPoint6);
        super.coordinateHashMap.put(pixelPoint6.getCoordinate().toString(),pixelPoint6);
        PixelPoint pixelPoint7 = new PixelPoint(new Coordinate(central.getX() + y, central.getY() - x),Color.WHITE);
        super.putPixel( pixelPoint7);
        super.coordinateHashMap.put(pixelPoint7.getCoordinate().toString(),pixelPoint7);
        PixelPoint pixelPoint8 = new PixelPoint(new Coordinate(central.getX() + -y,central.getY() + -x),Color.WHITE);
        super.putPixel( pixelPoint8);
        super.coordinateHashMap.put(pixelPoint8.getCoordinate().toString(),pixelPoint8);
        fillColor(Color.WHITE);
    }
}
