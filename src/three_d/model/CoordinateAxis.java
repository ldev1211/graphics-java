package three_d.model;

import three_d.config.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class CoordinateAxis {
    private JFrame frame;
    private Coordinate coorOx; // toa do mui ten
    private Coordinate coorOy; // toa do mui ten
    private Coordinate coorOz; // toa do mui ten

    public CoordinateAxis(JFrame frame) {
        this.frame = frame;
        initComponent();
    }

    public void putDash(Graphics g, Coordinate pixelPoint, int radius) {
        g.fillOval(pixelPoint.getX() - radius / 2, pixelPoint.getY() - radius / 2, radius, radius);
    }

    public void drawShape() {
        Graphics g = this.frame.getGraphics();
        g.setColor(Color.BLACK);

        Graphics2D g2 = (Graphics2D) g;
        Line2D oy = new Line2D.Float(Constants.ORIGIN_X, Constants.ORIGIN_Y, coorOy.getX(), coorOy.getY());
        Line2D ox = new Line2D.Float(Constants.ORIGIN_X, Constants.ORIGIN_Y, coorOx.getX(), coorOx.getY());
        Line2D oz = new Line2D.Float(Constants.ORIGIN_X, Constants.ORIGIN_Y, coorOz.getX(), coorOz.getY());
        g2.draw(ox);
        g2.draw(oy);
        g2.draw(oz);

        g.drawString("Ox", coorOx.getX() - 20, coorOx.getY() + 20);
        g.drawString("Oy", coorOy.getX() - 30, coorOy.getY() + 10);
        g.drawString("Oz", coorOz.getX() - 10, coorOz.getY() + 20);


        for (int y = Constants.ORIGIN_Y; y >= coorOy.getY(); y -= Constants.USER_PIXEL) {
            putDash(g, new Coordinate(Constants.ORIGIN_X, y), Constants.DASH_RADIUS);
        }

        for (int x = Constants.ORIGIN_X; x <= coorOx.getX(); x += Constants.USER_PIXEL) {
            putDash(g, new Coordinate(x, Constants.ORIGIN_Y), Constants.DASH_RADIUS);
        }

        // (Oz, Ox) = 45 degree
         // x-=3 => don vi pixel tren Oz ~ tren Ox, Oy
        for (int x = Constants.ORIGIN_X; x >= coorOz.getX(); x -= 3) {
            putDash(g, new Coordinate(x, Constants.ORIGIN_Y + (Constants.ORIGIN_X - x)), Constants.DASH_RADIUS);
//            System.out.println(new Coordinate(x, Constants.ORIGIN_Y + (Constants.ORIGIN_X - x)).toString());
        }
    }


    void initComponent() {
        int y_OY = 100; // toa do tia OY
        int x_OX = Constants.RESOLUTION_WIDTH - 400; // toa do tia ox
        int x_Oz = Constants.ORIGIN_X - 250;
        int y_Oz = Constants.ORIGIN_Y + 250;

        coorOx = new Coordinate(x_OX, Constants.ORIGIN_Y);
        coorOy = new Coordinate(Constants.ORIGIN_X, y_OY);
        coorOz = new Coordinate(x_Oz, y_Oz);
    }


}
