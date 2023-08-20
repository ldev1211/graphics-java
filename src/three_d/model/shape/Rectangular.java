package three_d.model.shape;

import three_d.model.Coordinate;
import three_d.model.Coordinate3D;

import javax.swing.*;
import java.awt.*;

public class Rectangular {
    private JFrame frame;
    private Coordinate3D origin;
    private int length;
    private int width;
    private int height;

    Coordinate3D coor_E;
    Coordinate3D coor_F;
    Coordinate3D coor_G;
    Coordinate3D coor_H;
    Coordinate3D coor_A;
    Coordinate3D coor_B;
    Coordinate3D coor_C;
    Coordinate3D coor_D;

    public Rectangular(JFrame frame, Coordinate3D origin, int length, int width, int height) {
        this.frame = frame;
        this.origin = origin;
        this.length = length;
        this.width = width;
        this.height = height;

        initComponent();
    }

//    public void cvttoString() {
//        // print out the coordinate of the rectangular;
//        System.out.println("E: " + coor_E.toString());
//        System.out.println("F: " + coor_F.toString());
//        System.out.println("G: " + coor_G.toString());
//        System.out.println("H: " + coor_H.toString());
//        System.out.println("A: " + coor_A.toString());
//        System.out.println("B: " + coor_B.toString());
//        System.out.println("C: " + coor_C.toString());
//        System.out.println("D: " + coor_D.toString());
//
//    }

    public String toString() {
        return "A: " + coor_A.toString() + "\n" +
                "B: " + coor_B.toString() + "\n" +
                "C: " + coor_C.toString() + "\n" +
                "D: " + coor_D.toString() + "\n" +
                "E: " + coor_E.toString() + "\n" +
                "F: " + coor_F.toString() + "\n" +
                "G: " + coor_G.toString() + "\n" +
                "H: " + coor_H.toString() + "\n";
    }

    public void drawShape() {
        Graphics g = this.frame.getGraphics();

        // convert to 2D
        Coordinate coorE_2D = coor_E.convertTo2D();
        Coordinate coorF_2D = coor_F.convertTo2D();
        Coordinate coorG_2D = coor_G.convertTo2D();
        Coordinate coorH_2D = coor_H.convertTo2D();
        Coordinate coorA_2D = coor_A.convertTo2D();
        Coordinate coorB_2D = coor_B.convertTo2D();
        Coordinate coorC_2D = coor_C.convertTo2D();
        Coordinate coorD_2D = coor_D.convertTo2D();

        // draw edge
        new Line(this.frame, coorE_2D, coorF_2D).drawNetDut(); // net dut
        new Line(this.frame, coorF_2D, coorG_2D).drawNetDut(); // net dut
        new Line(this.frame, coorG_2D, coorH_2D).drawShape();
        new Line(this.frame, coorH_2D, coorE_2D).drawShape();
        new Line(this.frame, coorE_2D, coorA_2D).drawShape();
        new Line(this.frame, coorF_2D, coorB_2D).drawNetDut(); // net dut
        new Line(this.frame, coorG_2D, coorC_2D).drawShape();
        new Line(this.frame, coorH_2D, coorD_2D).drawShape();
        new Line(this.frame, coorA_2D, coorB_2D).drawShape();
        new Line(this.frame, coorB_2D, coorC_2D).drawShape();
        new Line(this.frame, coorC_2D, coorD_2D).drawShape();
        new Line(this.frame, coorD_2D, coorA_2D).drawShape();


        // get machine vertex coordinate
        Coordinate vertexA = coorA_2D.userToMachineCoor();
        Coordinate vertexB = coorB_2D.userToMachineCoor();
        Coordinate vertexC = coorC_2D.userToMachineCoor();
        Coordinate vertexD = coorD_2D.userToMachineCoor();
        Coordinate vertexE = coorE_2D.userToMachineCoor();
        Coordinate vertexF = coorF_2D.userToMachineCoor();
        Coordinate vertexG = coorG_2D.userToMachineCoor();
        Coordinate vertexH = coorH_2D.userToMachineCoor();
        
        // put vertex coordinate name
        g.setColor(Color.RED);
        g.drawString("A", vertexA.getX()-10, vertexA.getY()+10);
        g.drawString("B", vertexB.getX()+10, vertexB.getY()-10);
        g.drawString("C", vertexC.getX()+10, vertexC.getY()-10);
        g.drawString("D", vertexD.getX()+10, vertexD.getY()+10);
        g.drawString("E", vertexE.getX()-10, vertexE.getY()+10);
        g.drawString("F", vertexF.getX()+10, vertexF.getY()-10);
        g.drawString("G", vertexG.getX()+10, vertexG.getY()+20);
        g.drawString("H", vertexH.getX()+10, vertexH.getY()+10);
        
    }


    void initComponent() {
        coor_E = new Coordinate3D(origin.getX(), origin.getY(), origin.getZ() + width);
        coor_F = new Coordinate3D(origin.getX(), origin.getY(), origin.getZ());
        coor_G = new Coordinate3D(origin.getX() + length, origin.getY(), origin.getZ());
        coor_H = new Coordinate3D(origin.getX() + length, origin.getY(), origin.getZ() + width);
        coor_A = new Coordinate3D(origin.getX(), origin.getY() + height, origin.getZ() + width);
        coor_B = new Coordinate3D(origin.getX(), origin.getY() + height, origin.getZ());
        coor_C = new Coordinate3D(origin.getX() + length, origin.getY() + height, origin.getZ());
        coor_D = new Coordinate3D(origin.getX() + length, origin.getY() + height, origin.getZ() + width);
    }


}
