package model.object;

import config.MatrixCalculate;
import form.MainForm;
import model.Coordinate;
import model.PixelPoint;
import model.shape.*;
import model.shape.Rectangle;
import model.shape.Shape;
import model.transform.Symmetry;
import model.transform.Transform;

import java.awt.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Plane extends Shape {
    private Coordinate central;
    private int width;
    private int height;
    private List<Shape> shapes;

    public Coordinate getCentral() {
        return central;
    }

    public void setCentral(Coordinate central) {
        this.central = central;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Plane(Graphics graphics, Coordinate central, int width, int height, List<Shape> shapes) {
        super.g = graphics;
        this.central = central;
        this.width = width;
        this.height = height;
        this.shapes = shapes;
    }

    @Override
    public void setTransform(List<Transform> transforms) {
        super.setTransform(transforms);
        if(MainForm.isSymmetric){
            Symmetry symmetry = new Symmetry(Symmetry.OX);
            tmp = MatrixCalculate.mulMatrix3x3(tmp, symmetry.transformMatrix);
        }
        double[][] res = MatrixCalculate.mulMatrix1x3(new double[][]{new double[]{central.getX(),central.getY(),1}},tmp);
        setCentral(new Coordinate((int) Math.round(res[0][0]), (int) Math.round(res[0][1])));
        super.tmp = new double[][]{{1,0,0},{0,1,0},{0,0,1}};
    }

    public void transformEffectFire(int cutWidth){
        Line l1 = (Line) shapes.get(0);
        Line l2 = (Line) shapes.get(1);
        Line l3 = (Line) shapes.get(2);
        Set<Map.Entry<String, PixelPoint>> entries = l1.coordinateHashMap.entrySet();
        for (Map.Entry<String,PixelPoint> entry : entries){
            super.coordinateHashMap.remove(entry.getKey());
        }
        entries = l2.coordinateHashMap.entrySet();
        for (Map.Entry<String,PixelPoint> entry : entries){
            super.coordinateHashMap.remove(entry.getKey());
        }
        entries = l3.coordinateHashMap.entrySet();
        for (Map.Entry<String,PixelPoint> entry : entries){
            super.coordinateHashMap.remove(entry.getKey());
        }

        ((Line) shapes.get(0)).setPointStart(new Coordinate(l1.getPointStart().getX()+cutWidth,l1.getPointStart().getY()));
        ((Line) shapes.get(1)).setPointStart(new Coordinate(l2.getPointStart().getX()+cutWidth,l2.getPointStart().getY()));
        ((Line) shapes.get(2)).setPointStart(new Coordinate(l3.getPointStart().getX()+cutWidth,l3.getPointStart().getY()));

        shapes.get(0).coordinateHashMap.clear();
        shapes.get(1).coordinateHashMap.clear();
        shapes.get(2).coordinateHashMap.clear();

        ((Line) shapes.get(0)).drawShape();
        ((Line) shapes.get(1)).drawShape();
        ((Line) shapes.get(2)).drawShape();

        coordinateHashMap.putAll(shapes.get(0).coordinateHashMap);
        coordinateHashMap.putAll(shapes.get(1).coordinateHashMap);
        coordinateHashMap.putAll(shapes.get(2).coordinateHashMap);
    }

    public void drawShape(){
        if (coordinateHashMap.size()==0){
            for (int i=0;i<shapes.size();++i){
                Shape shape = shapes.get(i);
                if (shape instanceof Line) {
                    ((Line) shape).drawShape();
                } else if (shape instanceof Rectangle) {
                    ((Rectangle) shape).drawShape();
                } else if (shape instanceof Circle) {
                    ((Circle) shape).drawShape();
                } else if (shape instanceof Elisp) {
                    ((Elisp) shape).drawShape();
                }
                coordinateHashMap.putAll(shape.coordinateHashMap);
            }
        } else {
            Set<Map.Entry<String, PixelPoint>> entries = coordinateHashMap.entrySet();
            for (Map.Entry<String,PixelPoint> entry : entries){
                super.putPixel(entry.getValue());
            }
        }
    }
}
