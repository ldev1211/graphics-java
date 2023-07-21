package model.shape;

import config.MatrixCalculate;
import model.Coordinate;
import model.PixelPoint;
import model.transform.Transform;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Line extends Shape {
    Coordinate pointStart;
    Coordinate pointEnd;

    public Coordinate getPointStart() {
        return pointStart;
    }

    public void setPointStart(Coordinate pointStart) {
        this.pointStart = pointStart;
    }

    public Coordinate getPointEnd() {
        return pointEnd;
    }

    public void setPointEnd(Coordinate pointEnd) {
        this.pointEnd = pointEnd;
    }

    @Override
    public void setTransform(List<Transform> transforms) {
        super.setTransform(transforms);
        double[][] resStart = MatrixCalculate.mulMatrix1x3(new double[][]{new double[]{pointStart.getX(),pointStart.getY(),1}},tmp);
        double[][] resEnd = MatrixCalculate.mulMatrix1x3(new double[][]{new double[]{pointEnd.getX(),pointEnd.getY(),1}},tmp);
        setPointStart(new Coordinate((int) resStart[0][0], (int) resStart[0][1]));
        setPointEnd(new Coordinate((int) resEnd[0][0], (int) resEnd[0][1]));
        tmp = new double[][]{{1,0,0},{0,1,0},{0,0,1}};
    }

    public void drawShape(){
        if(coordinateHashMap.size()==0){
            int xStart = pointStart.getX();
            int yStart = pointStart.getY();
            int xEnd = pointEnd.getX();
            int yEnd = pointEnd.getY();
            int dx = Math.abs(xEnd - xStart);
            int dy = Math.abs(yEnd - yStart);
            int sx = xStart < xEnd ? 1 : -1;
            int sy = yStart < yEnd ? 1 : -1;
            int err = dx - dy;

            while (xStart != xEnd || yStart != yEnd) {
                int err2 = 2 * err;
                if (err2 > -dy) {
                    err -= dy;
                    xStart += sx;
                }
                if (err2 < dx) {
                    err += dx;
                    yStart += sy;
                }
                Coordinate coordinate = new Coordinate(xStart,yStart);
                PixelPoint pixelPoint = new PixelPoint(coordinate, Color.WHITE);
                super.coordinateHashMap.put(coordinate.toString(),pixelPoint);
                super.putPixel(pixelPoint);
            }
        } else {
            Set<Map.Entry<String,PixelPoint>> entries = coordinateHashMap.entrySet();
            for (Map.Entry<String,PixelPoint> entry : entries){
                super.putPixel(entry.getValue());
            }
        }
    }

    public Line(Graphics graphics, Coordinate pointStart, Coordinate pointEnd) {
        super.g = graphics;
        this.pointStart = pointStart;
        this.pointEnd = pointEnd;
    }
}
