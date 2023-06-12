import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class Shape {
    public HashMap<Coordinate,Coordinate> coordinateHashMap = new HashMap<>();
    public void putPixel(JFrame frame,PixelPoint pixelPoint){
        Graphics g = frame.getGraphics();
        g.setColor(pixelPoint.getColor());
        g.drawLine(
                pixelPoint.getCoordinate().getX(),
                pixelPoint.getCoordinate().getY(),
                pixelPoint.getCoordinate().getX(),
                pixelPoint.getCoordinate().getY());
    }
}
