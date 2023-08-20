package three_d.model.shape;

import three_d.model.Coordinate;
import three_d.model.Coordinate3D;
import javax.swing.*;
import java.awt.*;

public class Cylinder extends Shape{
    private JFrame frame;
    private Coordinate3D center; // tam day hinh tru
    private int radius;
    private int height;


    public void drawShape() {
        Coordinate center2D = center.convertTo2D();
        Coordinate coorA = new Coordinate(center2D.getX() - this.radius, center2D.getY() + this.height);
        Coordinate coorB = new Coordinate(center2D.getX() + this.radius, center2D.getY() + this.height);
        Coordinate coorC = new Coordinate(center2D.getX() + this.radius, center2D.getY());
        Coordinate coorD = new Coordinate(center2D.getX() - this.radius, center2D.getY());

        int a = radius;
        int b = (int) (a / 2 * (Math.sqrt(2) / 2));

        new Line(this.frame, coorA, coorD).drawShape();
        new Line(this.frame, coorB, coorC).drawShape();
        new Elisp(this.frame, center2D, a, b).drawNetDut();
        new Elisp(this.frame, new Coordinate(center2D.getX(), coorA.getY()), a, b).drawShape();

    }

    public String toString() {
        return "Tam duoi: " + this.center.toString() + "\n" +
                "Tam tren: " + new Coordinate3D(this.center.getX(), this.center.getY(), this.center.getZ()+this.height).toString() + "\n" +
                "Ban kinh day: " + this.radius + "\n" +
                "Chieu cao: " + this.height + "\n";
    }

    public Cylinder(JFrame frame, Coordinate3D center, int radius, int height) {
        this.frame = frame;
        this.center = center;
        this.radius = radius;
        this.height = height;
    }
}
