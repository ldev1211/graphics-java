import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainForm extends JFrame{
    Point lastPoint;
    Coordinate pS;
    Coordinate pE;

    public MainForm(){
        setSize(WindowManager.getScreenWidth(), WindowManager.getScreenHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        pS = new Coordinate(0,0);
        pE = new Coordinate(0,0);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MainForm.this.getGraphics().setColor(Color.GREEN);
                MainForm.this.getGraphics().drawLine(100,200,100,400);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                pS.setX(e.getX());
                pS.setY(e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pE.setX(e.getX());
                pE.setY(e.getY());
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
                if(WindowManager.stackShape.size()!=0){
                    Line prevLine = (Line) WindowManager.stackShape.peek();
                    Set<Map.Entry<Coordinate,Coordinate>> entries = prevLine.coordinateHashMap.entrySet();
                    for(Map.Entry<Coordinate,Coordinate> keyValue: entries){
                        prevLine.putPixel(MainForm.this,new PixelPoint(keyValue.getValue(), Color.WHITE));
                    }
                }
                pE.setX(e.getX());
                pE.setY(e.getY());
                Line line = new Line(MainForm.this,pS,pE);
                line.drawShape();
                WindowManager.stackShape.push(line);
                System.out.println(WindowManager.stackShape.size());
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
