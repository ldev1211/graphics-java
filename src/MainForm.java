import config.WindowManager;
import model.*;
import model.object.Meteorite;
import model.object.Plane;
import model.shape.*;
import model.shape.Rectangle;
import model.shape.Shape;
import model.transform.Transform;
import model.transform.Translate;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class MainForm extends JFrame{

    static List<Meteorite> meteorites;

    @Override
    public void paint(Graphics g) {
        super.paintComponents(g);
        g.setColor(Color.WHITE);
    }
    public MainForm(){
        setSize(WindowManager.getScreenWidth(), WindowManager.getScreenHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }
            Graphics graphicsRoot;
            @Override
            public void mouseReleased(MouseEvent e) {
                graphicsRoot = MainForm.this.getGraphics();
                new BackgroundThemeThread(graphicsRoot).start();
                new GenerateMeteoriteThread().start();
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        setVisible(true);
    }
    static List<Rectangle> bullets;
    static Image buffer; // Bộ nhớ đệm (buffer) để vẽ
    static Graphics bufferGraphics; // Graphics của bộ nhớ đệm
    static int screenWidth = WindowManager.getScreenWidth();
    static int screenHeight = WindowManager.getScreenHeight();
    static Plane plane;
    class BackgroundThemeThread extends Thread implements KeyListener{
        Graphics g;
        List<Transform> transforms;
        List<Line> lines;

        public BackgroundThemeThread(Graphics g) {
            this.g = g;
            buffer = createImage(getWidth(),getHeight());
            bufferGraphics = buffer.getGraphics();
            transforms = new ArrayList<>();
            meteorites = new ArrayList<>();
            lines = new ArrayList<>();
            transforms.add(new Translate(-30,0));
            addKeyListener(this);
            bullets = new ArrayList<>();
            transformsBullet = new ArrayList<>();
            transformsBullet.add(new Translate(10,0));
            transformsPlane = new ArrayList<>();
            transformsPlane.add(new Translate(0,0));
        }
        @Override
        public void run() {
            drawPlane();
            new BulletThread().start();
            while (true){
                drawCoordinateSystem();
                generateNewLineStar();
                transformLineStar();
                transformPlane();
                transformMeteorite();
                transformBullet();
                g.drawImage(buffer, 0, 0, null);
            }
        }
        List<Transform> transformsBullet;

        void transformBullet() {
            for (int i=0;i<bullets.size();++i){
                if(bullets.get(i)==null) continue;
                if(bullets.get(i).getVertex().getX() >= screenWidth){
                    bullets.remove(i);
                    continue;
                }
                boolean isTouch = false;
                for(int j=0;j<meteorites.size();++j){
                    Rectangle rectangle =bullets.get(i);
                    Circle circle =meteorites.get(j).getParent();
                    if(
                            rectangle.getVertex().getX()>= circle.getCentral().getX()-circle.getRadius() &&
                                    rectangle.getVertex().getY() >= circle.getCentral().getY() - circle.getRadius() &&
                                    rectangle.getVertex().getY() <= circle.getCentral().getY() + circle.getRadius()
                    ){
                        meteorites.remove(j);
                        bullets.remove(i);
                        isTouch = true;
                        break;
                    }
                }
                if(isTouch) continue;
                bullets.get(i).setTransform(transformsBullet);
                bullets.get(i).drawShape();
            }
        }
        private void transformLineStar() {
            for (int i=0;i<lines.size();++i){
                if (lines.get(i)==null) continue;
                if(lines.get(i).getPointEnd().getX() <= 0){
                    lines.remove(i);
                    continue;
                }
                lines.get(i).setTransform(transforms);
                lines.get(i).drawShape();
            }
        }

        private int getRandom(int min,int max){
            return (int)((Math.random()) * ((max - min) + 1)) + min;
        }

        private void generateNewLineStar() {
            int delayTimeRand = getRandom(500,1000);//1000 -500
            Timer timer = new Timer();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    // Thực hiện công việc trong đây
                    int yRand = getRandom(2,screenHeight);
                    int widthRand = getRandom(20,200);// 200 - 20
                    Line newLine = new Line(bufferGraphics,new Coordinate(screenWidth,yRand),new Coordinate(screenWidth+widthRand,yRand));
                    lines.add(newLine);
                }
            };
            timer.schedule(task, delayTimeRand);
        }
        private void drawCoordinateSystem() {
            bufferGraphics.setColor(Color.BLACK); // Đặt màu nền đen
            bufferGraphics.fillRect(0, 0, getWidth(), getHeight()); // Xoá toàn bộ nội dung bộ nhớ đệm
            bufferGraphics.setColor(Color.WHITE);
            bufferGraphics.drawLine(
                    screenWidth/2,
                    0,
                    screenWidth/2,
                    screenHeight
            );
            bufferGraphics.drawLine(
                    0,
                    screenHeight/2,
                    screenWidth,
                    screenHeight/2
            );
            int dotWidth = 2;
            for (int k=0;k<=screenWidth;k+=5){
                bufferGraphics.drawLine(
                        k,
                        screenHeight/2 - dotWidth,
                        k,
                        screenHeight/2 + dotWidth
                );
                bufferGraphics.drawLine(
                        screenWidth/2 - dotWidth,
                        k,
                        screenWidth/2 + dotWidth,
                        k
                );
            }
        }
        int RIGHT = 39;
        int LEFT = 37;
        int UP = 38;
        int DOWN = 40;
        int stepTranslate = 20;
        int cutWidth=0;
        boolean increase = false;
        private void transformPlane() {
            if(increase){
                if (cutWidth<5) cutWidth++;
                else {
                    cutWidth--;
                    increase = false;
                }
            } else {
                if (cutWidth>-5) cutWidth--;
                else {
                    increase = true;
                    cutWidth++;
                }
            }
            plane.transformEffectFire(cutWidth);
            plane.setTransform(transformsPlane);
            plane.drawShape();
            transformsPlane.set(0,new Translate(0,0));
        }
        private void drawPlane(){
            List<Shape> shapes = new ArrayList<>();
            int widthFire = 10;
            int planeWidth = 100;
            int planeHeight = 80;
            int spaceBetweenFire = 10;
            Coordinate centralPlane = new Coordinate(100,200);
            shapes.add(new Line(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-planeWidth/2,
                                    centralPlane.getY()-spaceBetweenFire
                            ),
                            new Coordinate(
                                    centralPlane.getX()-planeWidth/2+widthFire,
                                    centralPlane.getY()-spaceBetweenFire

                            )
                    )
            );
            shapes.add(new Line(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-planeWidth/2,
                                    centralPlane.getY()
                            ),
                            new Coordinate(
                                    centralPlane.getX()-planeWidth/2+widthFire,
                                    centralPlane.getY()

                            )
                    )
            );
            shapes.add(new Line(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-planeWidth/2,
                                    centralPlane.getY()+spaceBetweenFire
                            ),
                            new Coordinate(
                                    centralPlane.getX()-planeWidth/2+widthFire,
                                    centralPlane.getY()+spaceBetweenFire

                            )
                    )
            );
            int major = widthFire/2;
            int minor = spaceBetweenFire+3;
            shapes.add(
                    new Elisp(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-(planeWidth/2)+widthFire+major,
                                    centralPlane.getY()
                            ),
                            major,
                            minor
                    )
            );
            int widthPlaneBody = 40;
            shapes.add(
                    new Rectangle(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-(planeWidth/2)+widthFire+major*2,
                                    centralPlane.getY()-minor
                            ),
                            widthPlaneBody,
                            minor*2
                    )
            );
            shapes.add(
                    new Elisp(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()+10+major,
                                    centralPlane.getY()
                            ),
                            major,
                            minor
                    )
            );
            shapes.add(
                    new Line(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()+10+major*2,
                                    centralPlane.getY()-minor
                            ),
                            new Coordinate(
                                    centralPlane.getX()+10+major*2,
                                    centralPlane.getY()+minor
                            )
                    )
            );
            shapes.add(
                    new Line(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()+10+major*2,
                                    centralPlane.getY()-minor
                            ),
                            new Coordinate(
                                    centralPlane.getX()+10+major*2+30,
                                    centralPlane.getY()
                            )
                    )
            );
            shapes.add(
                    new Line(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()+10+major*2,
                                    centralPlane.getY()+minor
                            ),
                            new Coordinate(
                                    centralPlane.getX()+10+major*2+30,
                                    centralPlane.getY()
                            )
                    )
            );


            shapes.add(
                    new Line(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()+5,
                                    centralPlane.getY()-minor
                            ),
                            new Coordinate(
                                    centralPlane.getX()-10,
                                    centralPlane.getY()-planeHeight/2
                            )
                    )
            );
            shapes.add(
                    new Line(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-10,
                                    centralPlane.getY()-planeHeight/2
                            ),
                            new Coordinate(
                                    centralPlane.getX()-30,
                                    centralPlane.getY()-planeHeight/2
                            )
                    )
            );
            shapes.add(
                    new Line(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-30,
                                    centralPlane.getY()-planeHeight/2
                            ),
                            new Coordinate(
                                    centralPlane.getX()-30,
                                    centralPlane.getY()-minor
                            )
                    )
            );
            shapes.add(
                    new Line(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-30,
                                    centralPlane.getY()-planeHeight/2
                            ),
                            new Coordinate(
                                    centralPlane.getX()-30,
                                    centralPlane.getY()+planeHeight/2
                            )
                    )
            );
            shapes.add(
                    new Line(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-30,
                                    centralPlane.getY()+planeHeight/2
                            ),
                            new Coordinate(
                                    centralPlane.getX()-10,
                                    centralPlane.getY()+planeHeight/2
                            )
                    )
            );
            shapes.add(
                    new Line(
                            bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-10,
                                    centralPlane.getY()+planeHeight/2
                            ),
                            new Coordinate(
                                    centralPlane.getX()+5,
                                    centralPlane.getY()+15
                            )
                    )
            );
            plane = new Plane(bufferGraphics,centralPlane,planeWidth,planeHeight,shapes);
            plane.drawShape();
        }
        void transformMeteorite(){
            for (int i=0;i<meteorites.size();++i){
                Meteorite meteorite = meteorites.get(i);
                if(meteorite == null) continue;
                if(meteorite.getParent().getCentral().getX()<=0){
                    meteorites.remove(i);
                    continue;
                }
                meteorite.setTransform();
                meteorite.drawShape();
            }
        }
        @Override
        public void keyTyped(KeyEvent e) {

        }

        List<Transform> transformsPlane;
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode==UP){
                transformsPlane.set(0,new Translate(0,-stepTranslate));
            } else if (keyCode == DOWN) {
                transformsPlane.set(0,new Translate(0,stepTranslate));
            } else if (keyCode == LEFT) {
                transformsPlane.set(0,new Translate(-stepTranslate,0));
            } else if (keyCode == RIGHT) {
                transformsPlane.set(0,new Translate(stepTranslate,0));
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

    class GenerateMeteoriteThread extends Thread{

        public GenerateMeteoriteThread(){
        }

        @Override
        public void run() {
            while (true){
                int radius = getRandom(50,100);
                Coordinate central = new Coordinate(screenWidth+radius, getRandom(2,screenHeight));
                drawMeteorite(central,radius);
                int sleepTimeRand = getRandom(1000,3000);
                try {
                    sleep(sleepTimeRand);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        Circle circleParent;
        void drawMeteorite(Coordinate central,int radius){
            Meteorite meteorite;
            List<Circle> children = new ArrayList<>();
            circleParent = new Circle(
                    bufferGraphics,
                    central,
                    radius
            );
            children.add(
                    new Circle(
                            bufferGraphics,
                            new Coordinate(circleParent.getCentral().getX(),circleParent.getCentral().getY()-radius/2),
                            radius/2
                    )
            );
            meteorite = new Meteorite(bufferGraphics,circleParent,children);
            meteorite.drawShape();
            meteorites.add(meteorite);
        }
        private int getRandom(int min,int max){
            return (int)((Math.random()) * ((max - min) + 1)) + min;
        }
    }

    class BulletThread extends Thread {
        @Override
        public void run() {
            int delay = 200;
            while (true){
                Rectangle rectangle = new Rectangle(
                        bufferGraphics,
                        new Coordinate(
                                plane.getCentral().getX()+ plane.getWidth()/2, plane.getCentral().getY()
                        ),
                        50,
                        20
                );
                bullets.add(rectangle);
                try {
                    sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
