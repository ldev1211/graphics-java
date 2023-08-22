package model.object;

import com.sun.tools.javac.Main;
import config.MatrixCalculate;
import form.MainForm;
import model.Coordinate;
import model.PixelPoint;
import model.shape.*;
import model.shape.Rectangle;
import model.shape.Shape;
import model.transform.*;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Meteorite extends Shape {
    Circle parent;
    List<Circle> children;
    boolean isBroke = false;

    public boolean isBroke() {
        return isBroke;
    }

    public void setBroke(boolean broke) {
        isBroke = broke;
    }

    public Circle getParent() {
        return parent;
    }

    public void setParent(Circle parent) {
        this.parent = parent;
    }

    public List<Circle> getChildren() {
        return children;
    }

    public void setChildren(List<Circle> children) {
        this.children = children;
    }

    public Meteorite(Graphics g, Circle parent, List<Circle> children) {
        this.children = children;
        this.parent = parent;
        super.g = g;
    }

    public void transformEffectRemove(){
        
    }

    public void setTransform() {
        Translate reverseToO = new Translate(-parent.getCentral().getX(),-parent.getCentral().getY());
        Turn turn = new Turn(parent.getCentral(),5);
        Translate reverseToOld = new Translate(parent.getCentral().getX(),parent.getCentral().getY());
        Translate translate = new Translate(-3,0);
        float percent = 0.8f;
        double[][] transformMatrixChildren = MatrixCalculate.mulMatrix3x3(tmp,reverseToO.transformMatrix);
        transformMatrixChildren = MatrixCalculate.mulMatrix3x3(transformMatrixChildren, turn.transformMatrix);
        transformMatrixChildren = MatrixCalculate.mulMatrix3x3(transformMatrixChildren, reverseToOld.transformMatrix);
        transformMatrixChildren = MatrixCalculate.mulMatrix3x3(transformMatrixChildren, translate.transformMatrix);
        if(MainForm.isSymmetric){
            Symmetry symmetry = new Symmetry(Symmetry.OX);
            transformMatrixChildren = MatrixCalculate.mulMatrix3x3(transformMatrixChildren, symmetry.transformMatrix);
        }
        if(isBroke) {
            parent.setRadius(Math.round(parent.getRadius()*percent));
        }
        double[][] resParent = MatrixCalculate.mulMatrix1x3(new double[][]{new double[]{parent.getCentral().getX(),parent.getCentral().getY(),1}},transformMatrixChildren);
        parent.setCentral(new Coordinate((int)Math.round(resParent[0][0]),(int) Math.round(resParent[0][1])));
        parent.coordinateHashMap.clear();
        int len = children.size();
        for(int i=0;i<len;++i){
            double[][] res = MatrixCalculate.mulMatrix1x3(new double[][]{new double[]{children.get(i).getCentral().getX(),children.get(i).getCentral().getY(),1}},transformMatrixChildren);
            Coordinate newCoordinate = new Coordinate((int) Math.round(res[0][0]),(int) Math.round(res[0][1]));
            children.get(i).setCentral(newCoordinate);
            if(isBroke) children.get(i).setRadius(Math.round(children.get(i).getRadius()*percent));
            children.get(i).coordinateHashMap.clear();
        }
    }

    public void drawShape(){
        parent.drawShape();
        for (int i=0;i<children.size();++i){
            Circle circle = children.get(i);
            circle.drawShape();
            circle.fillColor(Color.BLACK);
            coordinateHashMap.putAll(circle.coordinateHashMap);
        }
    }
}
