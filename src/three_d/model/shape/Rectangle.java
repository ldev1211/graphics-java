package three_d.model.shape;

import three_d.model.Coordinate;
import three_d.model.PixelPoint;

import javax.swing.*;
import java.awt.*;

public class Rectangle extends Shape{
    private JFrame frame;
    private Coordinate vertex;
    private int width;
    private int height;

    public Rectangle(JFrame frame,Coordinate vertex, int width, int height) {
        this.frame = frame;
        this.vertex = vertex;
        this.width = width;
        this.height = height;
    }

    public void drawShape(){
        Coordinate c1 = new Coordinate(vertex.getX() + width,vertex.getY());
        Coordinate c2 = new Coordinate(vertex.getX()+width,vertex.getY()+height);
        Coordinate c3 = new Coordinate(vertex.getX(),vertex.getY()+height);
        drawLine(vertex,c1);
        drawLine(c1,c2);
        drawLine(c2,c3);
        drawLine(c3,vertex);
    }

    private void drawLine(Coordinate pointStart,Coordinate pointEnd){
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
//            PixelPoint pixelPoint = new PixelPoint(coordinate, Color.BLACK);
            PixelPoint pixelPoint = new PixelPoint(coordinate.userToMachineCoor(), Color.BLACK);
            super.coordinateHashMap.put(coordinate.toString(),pixelPoint);
            super.putPixel(frame,pixelPoint);

        }
    }
}
