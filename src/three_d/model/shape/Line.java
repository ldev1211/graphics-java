package three_d.model.shape;


import three_d.model.Coordinate;
import three_d.model.PixelPoint;

import javax.swing.*;
import java.awt.*;

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

    public void drawShape() {
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
            Coordinate coordinate = new Coordinate(xStart, yStart);
            Coordinate machineCoor = coordinate.userToMachineCoor();
            putPixel(frame, new PixelPoint(machineCoor, Color.BLACK));
        }
    }

    public void drawNetDut() {
        int xStart = pointStart.getX();
        int yStart = pointStart.getY();
        int xEnd = pointEnd.getX();
        int yEnd = pointEnd.getY();
        int dx = Math.abs(xEnd - xStart);
        int dy = Math.abs(yEnd - yStart);
        int sx = xStart < xEnd ? 1 : -1;
        int sy = yStart < yEnd ? 1 : -1;
        int err = dx - dy;

        int count = 0;

        while (xStart != xEnd || yStart != yEnd) {
            count++;

            int err2 = 2 * err;
            if (err2 > -dy) {
                err -= dy;
                xStart += sx;
            }
            if (err2 < dx) {
                err += dx;
                yStart += sy;
            }

            if (count % 5 == 0)
                continue;

            Coordinate coordinate = new Coordinate(xStart, yStart);
            Coordinate machineCoor = coordinate.userToMachineCoor();
            putPixel(frame, new PixelPoint(machineCoor, Color.BLACK));
        }
    }

    public void drawChamGach() {
        int xStart = pointStart.getX();
        int yStart = pointStart.getY();
        int xEnd = pointEnd.getX();
        int yEnd = pointEnd.getY();
        int dx = Math.abs(xEnd - xStart);
        int dy = Math.abs(yEnd - yStart);
        int sx = xStart < xEnd ? 1 : -1;
        int sy = yStart < yEnd ? 1 : -1;
        int err = dx - dy;

        int count = 0;

        while (xStart != xEnd || yStart != yEnd) {
            count++;

            int err2 = 2 * err;
            if (err2 > -dy) {
                err -= dy;
                xStart += sx;
            }
            if (err2 < dx) {
                err += dx;
                yStart += sy;
            }

            if (count <= 5 || count == 8) {
                Coordinate coordinate = new Coordinate(xStart, yStart);
                Coordinate machineCoor = coordinate.userToMachineCoor();
                putPixel(frame, new PixelPoint(machineCoor, Color.BLACK));
            }

            if (count == 10)
                count = 0;
        }
    }

    public void drawHaiChamGach() {
        int xStart = pointStart.getX();
        int yStart = pointStart.getY();
        int xEnd = pointEnd.getX();
        int yEnd = pointEnd.getY();
        int dx = Math.abs(xEnd - xStart);
        int dy = Math.abs(yEnd - yStart);
        int sx = xStart < xEnd ? 1 : -1;
        int sy = yStart < yEnd ? 1 : -1;
        int err = dx - dy;

        int count = 0;

        while (xStart != xEnd || yStart != yEnd) {
            count++;

            int err2 = 2 * err;
            if (err2 > -dy) {
                err -= dy;
                xStart += sx;
            }
            if (err2 < dx) {
                err += dx;
                yStart += sy;
            }

            if (count <= 12 || count == 15 || count == 18) {
                Coordinate coordinate = new Coordinate(xStart, yStart);
                Coordinate machineCoor = coordinate.userToMachineCoor();
                putPixel(frame, new PixelPoint(machineCoor, Color.BLACK));
            }

            if (count == 20)
                count = 0;
        }
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
