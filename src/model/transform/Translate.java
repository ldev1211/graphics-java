package model.transform;

public class Translate extends Transform{
    int dx;
    int dy;
    public Translate(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
        super.transformMatrix = new double[][]{
                new double[] {1,0,0},
                new double[] {0,1,0},
                new double[] {dx,dy,1}
        };
    }
}
