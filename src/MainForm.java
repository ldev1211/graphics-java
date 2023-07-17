import config.WindowManager;
import model.*;
import model.shape.Circle;
import model.shape.Elisp;
import model.shape.Rectangle;
import model.shape.Shape;

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
    Shape prevShape;
    boolean isDragging = false;

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.WHITE);
        int screenWidth = WindowManager.getScreenWidth();
        int screenHeight = WindowManager.getScreenHeight();
        g.drawLine(
                screenWidth/2,
                0,
                screenWidth/2,
                screenHeight
        );
        g.drawLine(
                0,
                screenHeight/2,
                screenWidth,
                screenHeight/2
        );
        int dotWidth = 2;
        for (int i=0;i<=screenWidth;i+=5){
            g.drawLine(
                    i,
                    screenHeight/2 - dotWidth,
                    i,
                    screenHeight/2 + dotWidth
            );
            g.drawLine(
                    screenWidth/2 - dotWidth,
                    i,
                    screenWidth/2 + dotWidth,
                    i
            );
        }
    }

    public MainForm(){
        setSize(WindowManager.getScreenWidth(), WindowManager.getScreenHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
//        pS = new Coordinate(0,0);
//        pE = new Coordinate(0,0);
//        addMouseListener(new MouseListener() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//            }
//
//            @Override
//            public void mousePressed(MouseEvent e) {
//                pS.setX(e.getX());
//                pS.setY(e.getY());
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                isDragging = false;
//                if(prevShape==null) return;
//                pE.setX(e.getX());
//                pE.setY(e.getY());
//                WindowManager.saveShape(prevShape);
//                Coordinate coordinate = new Coordinate(1,1);
//                WindowManager.mapPixel.put(coordinate.toString(),new Stack<>());
//            }
//
//            @Override
//            public void mouseEntered(MouseEvent e) {
//
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//
//            }
//        });
//        addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                super.mouseDragged(e);
//                if(prevShape == null) prevShape = new Elisp(MainForm.this,pS,(e.getX()-pS.getX()),(e.getY()-pS.getY()));
//                if(isDragging){
//                    Set<Map.Entry<String, PixelPoint>> entries = prevShape.coordinateHashMap.entrySet();
//                    for(Map.Entry<String,PixelPoint> keyValue: entries){
//                        Stack<PixelPoint> pixelPointStack = WindowManager.mapPixel.get(keyValue.getKey());
//                        if(pixelPointStack==null){
//                            prevShape.putPixel(MainForm.this,
//                                    new PixelPoint(
//                                            keyValue.getValue().getCoordinate(),
//                                            Color.WHITE
//                                    )
//                            );
//                        }
//                    }
//                }
//                isDragging = true;
//                Circle shape = new Circle(MainForm.this,pS,(int) Math.sqrt(Math.pow(pS.getX()-e.getX(),2)+Math.pow(pS.getY()-e.getY(),2)));
//                shape.drawShape();
//                prevShape = shape;
//            }
//
//            @Override
//            public void mouseMoved(MouseEvent e) {
//                super.mouseMoved(e);
////                System.out.println("Moved: X: "+e.getX()+". Y: "+e.getY());
////                Point currentPoint = e.getPoint();
////                if (lastPoint != null) {
////                    // Nội suy giữa lastPoint và currentPoint
////                    int deltaX = currentPoint.x - lastPoint.x;
////                    int deltaY = currentPoint.y - lastPoint.y;
////                    int steps = Math.max(Math.abs(deltaX), Math.abs(deltaY));
////
////                    for (int i = 0; i < steps; i++) {
////                        int x = lastPoint.x + (deltaX * i) / steps;
////                        int y = lastPoint.y + (deltaY * i) / steps;
////                        MainForm.this.getGraphics().drawLine(x,y,x,y);
////                    }
////                }
////                MainForm.this.getGraphics().drawLine(currentPoint.x,currentPoint.y,currentPoint.x,currentPoint.y);
////                lastPoint = currentPoint;
//            }
//        });

        setVisible(true);
    }
}
