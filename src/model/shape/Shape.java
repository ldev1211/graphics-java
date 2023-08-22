package model.shape;

import config.MatrixCalculate;
import form.MainForm;
import model.Coordinate;
import model.PixelPoint;
import model.transform.Symmetry;
import model.transform.Transform;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static config.WindowManager.userCoordinate;

public class Shape {
    public Graphics g;
    public Map<String, PixelPoint> coordinateHashMap = new HashMap<>();
    public void putPixel(PixelPoint pixelPoint){
        g.setColor(pixelPoint.getColor());
        g.drawLine(
                pixelPoint.getCoordinate().getX()*userCoordinate,
                pixelPoint.getCoordinate().getY()*userCoordinate,
                pixelPoint.getCoordinate().getX()*userCoordinate,
                pixelPoint.getCoordinate().getY()*userCoordinate
        );
    }
    public double[][] tmp = new double[][]{{1,0,0},{0,1,0},{0,0,1}};
    public void setTransform(List<Transform> transforms){
        for (Transform transform : transforms) {
            tmp = MatrixCalculate.mulMatrix3x3(tmp, transform.transformMatrix);
        }
        Map<String, PixelPoint> mapTmp = new HashMap<>(coordinateHashMap);
        Set<Map.Entry<String, PixelPoint>> entries = mapTmp.entrySet();
        coordinateHashMap.clear();
        for(Map.Entry<String,PixelPoint> keyValue: entries){
            Coordinate coordinate = keyValue.getValue().getCoordinate();
            double[][] res = MatrixCalculate.mulMatrix1x3(new double[][]{new double[]{coordinate.getX(),coordinate.getY(),1}},tmp);
            Coordinate newCoordinate = new Coordinate((int) Math.round(res[0][0]), (int) Math.round(res[0][1]));
            PixelPoint newPixelPoint = new PixelPoint(newCoordinate,keyValue.getValue().getColor());
            coordinateHashMap.put(newCoordinate.toString(),newPixelPoint);
        }
    }
}
