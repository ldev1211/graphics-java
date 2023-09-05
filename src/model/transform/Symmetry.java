package model.transform;

import form.MainForm;
import model.Coordinate;

public class Symmetry extends Transform{
    public static final int OX = 1;
    public static final int OY = 2;
    public static final int O = 3;
    Coordinate coordinate;
    int type;

    public Symmetry(Coordinate coordinate, int type) {
        this.coordinate = coordinate;
        this.type = type;
    }

    public Symmetry(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public Symmetry(int type) {
        this.type = type;
        switch (type){
            case OX:
                super.transformMatrix = new double[][]{
                        new double[] {1,0,0},
                        new double[] {0,-1,0},
                        new double[] {0,0,1}
                };
                break;
            case OY:
                super.transformMatrix = new double[][]{
                        new double[] {-1,0,0},
                        new double[] {0,1,0},
                        new double[] {0,0,1}
                };
                break;
            case O:
            default:
                super.transformMatrix = new double[][]{
                        new double[] {-1,0,0},
                        new double[] {0,-1,0},
                        new double[] {0,0,1}
                };
        }
    }
}
