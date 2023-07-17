package model;

import java.awt.Color;
import java.awt.geom.Point2D;

public class PixelPoint extends Point2D implements java.io.Serializable{
    private Coordinate coordinate;
    private Color color;

    public PixelPoint(Coordinate coordinate, Color color) {
        this.coordinate = coordinate;
        this.color = color;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public double getX() {
        return coordinate.getX();
    }

    @Override
    public double getY() {
        return coordinate.getY();
    }

    @Override
    public void setLocation(double x, double y) {

    }
}
