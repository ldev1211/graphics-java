package model;

import model.PixelPoint;
import model.Shape;

import javax.swing.*;
import java.awt.Color;

public class Line extends Shape {
    JFrame frame;
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

    public void drawShape(){
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
            PixelPoint pixelPoint = new PixelPoint(coordinate, Color.BLACK);
            super.coordinateHashMap.put(coordinate.toString(),pixelPoint);
            super.putPixel(frame,pixelPoint);
        }
    }

    public void drawLineWithMsThao(){
//        int xStart = pointStart.getX();
//        int yStart = pointStart.getY();
//        int xEnd = pointEnd.getX();
//        int yEnd = pointEnd.getY();
//        int dX = (xEnd - xStart);
//        int dY = (yEnd - yStart);
//        int p = 2*dY - dX;
//        int yCurr = yStart;
//        super.putPixel(frame,pointStart);
//        super.putPixel(frame,pointEnd);
//        for (int i=xStart+1;i<xEnd;++i){
//            if(p>=0) {
//                p += p + 2*dY - 2*dX;
//                super.putPixel(frame,new Coordinate(i,++yCurr));
//            }
//            else {
//                p+= p + 2*dY;
//                super.putPixel(frame,new Coordinate(i,yCurr));
//            }
//        }
    }

    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    public Line(JFrame frame, Coordinate pointStart, Coordinate pointEnd) {
        this.frame = frame;
        this.pointStart = pointStart;
        this.pointEnd = pointEnd;
    }
}
