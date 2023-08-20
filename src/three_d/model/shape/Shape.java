package three_d.model.shape;


import three_d.config.ConstantSymmetry;
import three_d.config.Constants;
import three_d.model.Coordinate;
import three_d.model.PixelPoint;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Shape {
    public Map<String, PixelPoint> coordinateHashMap = new HashMap<>();
    public void putPixel(JFrame frame, PixelPoint pixelPoint){
        Graphics g = frame.getGraphics();
        g.setColor(pixelPoint.getColor());
//        g.drawLine(
//                pixelPoint.getCoordinate().getX(),
//                pixelPoint.getCoordinate().getY(),
//                pixelPoint.getCoordinate().getX(),
//                pixelPoint.getCoordinate().getY()
//        );
        for (int i = 0; i < Constants.USER_PIXEL; i++) {
            for (int j = 0; j < Constants.USER_PIXEL; j++)
                g.fillRect(pixelPoint.getCoordinate().getX() + i, pixelPoint.getCoordinate().getY() - j, 1, 1);
        }
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
