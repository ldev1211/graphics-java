import config.ConstantSymmetry;
import config.WindowManager;
import model.Coordinate;
import model.Line;
import model.PixelPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class MainForm extends JFrame{
    Point lastPoint;
    Coordinate pS;
    Coordinate pE;
    Line prevLine;
    boolean isDragging = false;
    public MainForm(){
        setSize(WindowManager.getScreenWidth(), WindowManager.getScreenHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        int oy = ConstantSymmetry.OY;
        pS = new Coordinate(0,0);
        pE = new Coordinate(0,0);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                WindowManager.mapPixel.put(new Coordinate(1,1).toString(),new Stack<>());
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pS.setX(e.getX());
                pS.setY(e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                isDragging = false;
                if(prevLine==null) return;
                pE.setX(e.getX());
                pE.setY(e.getY());
                WindowManager.saveShape(prevLine);
                Coordinate coordinate = new Coordinate(1,1);
                WindowManager.mapPixel.put(coordinate.toString(),new Stack<>());
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if(prevLine == null) prevLine = new Line(MainForm.this,pS,pE);
                if(isDragging){
                    Set<Map.Entry<String, PixelPoint>> entries = prevLine.coordinateHashMap.entrySet();
                    for(Map.Entry<String,PixelPoint> keyValue: entries){
                        Stack<PixelPoint> pixelPointStack = WindowManager.mapPixel.get(keyValue.getKey());
                        if(pixelPointStack==null){
                            prevLine.putPixel(MainForm.this,
                                    new PixelPoint(
                                            keyValue.getValue().getCoordinate(),
                                            Color.WHITE
                                    )
                            );
                        }
                    }
                }
                isDragging = true;
                pE.setX(e.getX());
                pE.setY(e.getY());
                Line line = new Line(MainForm.this,pS,pE);
                line.drawShape();
                prevLine = line;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
//                System.out.println("Moved: X: "+e.getX()+". Y: "+e.getY());
//                Point currentPoint = e.getPoint();
//                if (lastPoint != null) {
//                    // Nội suy giữa lastPoint và currentPoint
//                    int deltaX = currentPoint.x - lastPoint.x;
//                    int deltaY = currentPoint.y - lastPoint.y;
//                    int steps = Math.max(Math.abs(deltaX), Math.abs(deltaY));
//
//                    for (int i = 0; i < steps; i++) {
//                        int x = lastPoint.x + (deltaX * i) / steps;
//                        int y = lastPoint.y + (deltaY * i) / steps;
//                        MainForm.this.getGraphics().drawLine(x,y,x,y);
//                    }
//                }
//                MainForm.this.getGraphics().drawLine(currentPoint.x,currentPoint.y,currentPoint.x,currentPoint.y);
//                lastPoint = currentPoint;
            }
        });
        setVisible(true);
    }
}
