package model.transform;

import model.Coordinate;

import java.util.Arrays;

public class Turn extends Transform{
    Coordinate coordinate;
    double alpha;

    public Turn(Coordinate coordinate, double alpha) {
        this.coordinate = coordinate;
        this.alpha = alpha;
        double phi = (alpha/180)*Math.PI;
        super.transformMatrix = new double[][]{
                new double[] {Math.cos(phi),-Math.sin(phi),0},
                new double[] {Math.sin(phi),Math.cos(phi),0},
                new double[] {0,0,1}
        };
    }
}
