package model.transform;

public class Scale extends Transform{
    int sx;
    int sy;

    public Scale(int sx, int sy) {
        this.sx = sx;
        this.sy = sy;
        super.transformMatrix = new double[][]{
                new double[] {sx,0,0},
                new double[] {0,sy,0},
                new double[] {0,0,1}
        };
    }
}
