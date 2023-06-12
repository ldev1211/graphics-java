import javax.swing.*;
import java.awt.Color;
import java.util.HashMap;

public class Shape {
    public HashMap<Coordinate,Coordinate> coordinateHashMap = new HashMap<>();
    public void putPixel(JFrame frame,PixelPoint pixelPoint){
        frame.getGraphics().setColor(pixelPoint.getColor());
        frame.getGraphics().drawLine(
                pixelPoint.getCoordinate().getX(),
                pixelPoint.getCoordinate().getY(),
                pixelPoint.getCoordinate().getX(),
                pixelPoint.getCoordinate().getY());
    }
}
