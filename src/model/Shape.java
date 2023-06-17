package model;

import config.ConstantSymmetry;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Shape {
    public Map<String, PixelPoint> coordinateHashMap = new HashMap<>();
    public void putPixel(JFrame frame,PixelPoint pixelPoint){
        Graphics g = frame.getGraphics();
        g.setColor(pixelPoint.getColor());
        g.drawLine(
                pixelPoint.getCoordinate().getX(),
                pixelPoint.getCoordinate().getY(),
                pixelPoint.getCoordinate().getX(),
                pixelPoint.getCoordinate().getY()
        );
    }
    public void getSymmetry(int TYPE){
        switch (TYPE) {
            case ConstantSymmetry.O:
                break;
            case ConstantSymmetry.OX:
                break;
            case ConstantSymmetry.OY:
                break;
        }
    }
    public void getSymmetry(Coordinate coordinate){

    }
}
