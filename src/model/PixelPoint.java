package model;

import java.awt.Color;

public class PixelPoint {
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
}
