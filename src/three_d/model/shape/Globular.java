package three_d.model.shape;

import three_d.model.Coordinate;
import three_d.model.Coordinate3D;

import javax.swing.*;

public class Globular {
    private Coordinate3D center;
    private int radius;
    private JFrame frame;

    public void drawShape() {
        int a = radius;
        int b = (int) (a / 2 * (Math.sqrt(2) / 2));

        Coordinate center_2D = center.convertTo2D();

        Circle vienngoai = new Circle(this.frame, center_2D, this.radius);
        Elisp vienphu = new Elisp(this.frame, center_2D, a,b);

        vienngoai.drawShape();
        vienphu.drawNetDut();


    }

    public String toString() {
        return "Tam: " + this.center.toString() + "\n" +
                "Ban kinh: " + this.radius + "\n";
    }


    public Globular(JFrame frame, Coordinate3D center, int radius) {
        this.frame = frame;
        this.center = center;
        this.radius = radius;
    }
}
