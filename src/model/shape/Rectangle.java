package model.shape;

import config.MatrixCalculate;
import model.Coordinate;
import model.PixelPoint;
import model.transform.Transform;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Rectangle extends Shape{
    private Coordinate vertex;
    private int width;
    private int height;

    public Rectangle(Graphics graphics,Coordinate vertex, int width, int height) {
        super.g = graphics;
        this.vertex = vertex;
        this.width = width;
        this.height = height;
    }

    public void drawShape(){
        if (coordinateHashMap.size()==0){
            Coordinate c1 = new Coordinate(vertex.getX() + width,vertex.getY());
            Coordinate c2 = new Coordinate(vertex.getX()+width,vertex.getY()+height);
            Coordinate c3 = new Coordinate(vertex.getX(),vertex.getY()+height);
            drawLine(vertex,c1);
            drawLine(c1,c2);
            drawLine(c2,c3);
            drawLine(c3,vertex);
        } else {
            Set<Map.Entry<String,PixelPoint>> entries = coordinateHashMap.entrySet();
            for (Map.Entry<String,PixelPoint> entry : entries){
                putPixel(entry.getValue());
            }
        }
        fillColor(Color.RED);
    }

    public Coordinate getVertex() {
        return vertex;
    }

    public void setVertex(Coordinate vertex) {
        this.vertex = vertex;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    private void drawLine(Coordinate pointStart, Coordinate pointEnd){
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
    }

    public void fillColor(Color color){
        g.setColor(color);
        g.fillRect(vertex.getX()*5,vertex.getY()*5,width*5,height*5);
    }

    @Override
    public void setTransform(List<Transform> transforms) {
        super.setTransform(transforms);
        double[][] res = MatrixCalculate.mulMatrix1x3(new double[][]{new double[]{vertex.getX(),vertex.getY(),1}},tmp);
        setVertex(new Coordinate((int)Math.round(res[0][0]),(int) Math.round(res[0][1])));
        tmp = new double[][]{{1,0,0},{0,1,0},{0,0,1}};
    }
}
