package form;

import config.WindowManager;
import model.*;
import model.object.Meteorite;
import model.object.Plane;
import model.shape.*;
import model.shape.Rectangle;
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

    static JList<String> planeJList;
    static JList<String> meteoriteJList;
    static JList<String> bulletJList;
    static DefaultListModel<String> planeDataList;
    static DefaultListModel<String> bulletDataList;
    static DefaultListModel<String> meteoriteDataList;
    public MainForm(){
        setSize(WindowManager.getScreenWidth(), WindowManager.getScreenHeight());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());
        planeDataList = new DefaultListModel<>();
        bulletDataList = new DefaultListModel<>();
        meteoriteDataList = new DefaultListModel<>();

        planeJList = new JList<>(planeDataList);
        bulletJList = new JList<>(bulletDataList);
        meteoriteJList = new JList<>(meteoriteDataList);

        JPanel mainPanel = new JPanel(new GridLayout(3, 1));

        // Tạo panel cho JList 1
        JPanel panel1 = createListPanel("Toạ độ máy bay", 0.2,planeJList);
        mainPanel.add(panel1);

        // Tạo panel cho JList 2
        JPanel panel2 = createListPanel("Toạ độ đạn", 0.4,bulletJList);
        mainPanel.add(panel2);

        // Tạo panel cho JList 3
        JPanel panel3 = createListPanel("Toạ độ đĩa bay", 0.4,meteoriteJList);
        mainPanel.add(panel3);

        add(mainPanel, BorderLayout.LINE_START);
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

    private JPanel createListPanel(String title, double heightRatio,JList<String> jListParam) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel(title);
        panel.add(titleLabel, BorderLayout.NORTH);
        jListParam.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(jListParam);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panel.add(scrollPane, BorderLayout.CENTER);

        int screenHeight = MainForm.screenHeight;
        int panelHeight = (int) (screenHeight * heightRatio);
        panel.setPreferredSize(new Dimension(150, panelHeight));
        return panel;
    }
    static List<Rectangle> bullets;
    static Image buffer;
    static Graphics bufferGraphics;
    static int screenWidth = WindowManager.getScreenWidth();
    static int screenHeight = WindowManager.getScreenHeight();
    static Plane plane;

    class CustomListCellRenderer extends JLabel implements ListCellRenderer<String> {
        public CustomListCellRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            setText(value);

            // Tùy chỉnh giao diện dựa trên trạng thái isSelected
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }
    static class GenerateMeteoriteThread extends Thread{

        public GenerateMeteoriteThread(){
        }

        @Override
        public void run() {
            while (true){
                int radius = getRandom(5,30);
                Coordinate central = new Coordinate((screenWidth/5+radius), getRandom(0,(screenHeight-100)/5));
                drawMeteorite(central,radius);
                int sleepTimeRand = getRandom(500,1500);
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
            meteoriteDataList.addElement("Đĩa bay "+(meteoriteDataList.size()+1)+" ("+central.getX()+","+central.getY()+")");
        }
        private int getRandom(int min,int max){
            return (int)((Math.random()) * ((max - min) + 1)) + min;
        }
    }

    static class BulletThread extends Thread {
        @Override
        public void run() {
            int delay = 200;
            while (true){
                Rectangle rectangle = new Rectangle(
                        bufferGraphics,
                        new Coordinate(
                                plane.getCentral().getX()+ plane.getWidth()/2, plane.getCentral().getY()-5
                        ),
                        15,
                        10
                );
                bullets.add(rectangle);
                bulletDataList.addElement("Đạn "+(bulletDataList.size()+1)+" ("+rectangle.getVertex().getX()+","+rectangle.getVertex().getY()+")");
                try {
                    sleep(delay);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    class BackgroundThemeThread extends Thread implements KeyListener {
        Graphics g;
        java.util.List<Transform> transforms;
        java.util.List<Line> lines;

        public BackgroundThemeThread(Graphics g) {
            addKeyListener(this);
            this.g = g;
            MainForm.buffer = createImage(getWidth(),getHeight());
            MainForm.bufferGraphics = MainForm.buffer.getGraphics();
            transforms = new ArrayList<>();
            MainForm.meteorites = new ArrayList<>();
            lines = new ArrayList<>();
            transforms.add(new Translate(-20,0));
            MainForm.bullets = new ArrayList<>();
            transformsBullet = new ArrayList<>();
            transformsBullet.add(new Translate(5,0));
            transformsPlane = new ArrayList<>();
            transformsPlane.add(new Translate(0,0));
        }
        @Override
        public void run() {
            drawPlane();
            new GenerateMeteoriteThread().start();
            new BulletThread().start();
            while (true){
                drawCoordinateSystem();
                generateNewLineStar();
                transformLineStar();
                transformPlane();
                transformMeteorite();
                transformBullet();
                g.drawImage(MainForm.buffer, 150, 0, null);
            }
        }
        List<Transform> transformsBullet;
        void transformBullet() {
            if(bulletDataList.isEmpty()) return;
            for (int i = 0; i< MainForm.bullets.size(); ++i){
                if(MainForm.bullets.get(i)==null) continue;
                if(MainForm.bullets.get(i).getVertex().getX()*5 >= MainForm.screenWidth){
                    MainForm.bullets.remove(i);
                    bulletDataList.remove(i);
                    continue;
                }
                Rectangle rectangle = MainForm.bullets.get(i);
                boolean isTouch = false;
                for(int j = 0; j< MainForm.meteorites.size(); ++j){
                    Circle circle = MainForm.meteorites.get(j).getParent();
                    if(
                            (rectangle.getVertex().getX()+rectangle.getWidth())*5>= (circle.getCentral().getX()-circle.getRadius())*5 &&
                                    rectangle.getVertex().getY()*5 >= circle.getCentral().getY()*5 - circle.getRadius()*5 &&
                                    rectangle.getVertex().getY()*5 <= circle.getCentral().getY()*5 + circle.getRadius()*5
                    ){
                        MainForm.meteorites.remove(j);
                        MainForm.bullets.remove(i);
                        meteoriteDataList.remove(j);
                        bulletDataList.remove(i);
                        isTouch = true;
                        break;
                    }
                }
                if(isTouch) continue;
                MainForm.bullets.get(i).setTransform(transformsBullet);
                MainForm.bullets.get(i).drawShape();
                rectangle = bullets.get(i);
                bulletDataList.setElementAt("Đạn "+(i+1)+" ("+rectangle.getVertex().getX()+","+rectangle.getVertex().getY()+")",i);
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
                    int yRand = getRandom(2, MainForm.screenHeight);
                    int widthRand = getRandom(20,200);// 200 - 20
                    Line newLine = new Line(MainForm.bufferGraphics,new Coordinate(MainForm.screenWidth,yRand),new Coordinate(MainForm.screenWidth+widthRand,yRand));
                    lines.add(newLine);
                }
            };
            timer.schedule(task, delayTimeRand);
        }
        private void drawCoordinateSystem() {
            MainForm.bufferGraphics.setColor(Color.BLACK); // Đặt màu nền đen
            MainForm.bufferGraphics.fillRect(0, 0, getWidth(), getHeight()); // Xoá toàn bộ nội dung bộ nhớ đệm
            MainForm.bufferGraphics.setColor(Color.WHITE);
            MainForm.bufferGraphics.drawLine(
                    MainForm.screenWidth/2,
                    0,
                    MainForm.screenWidth/2,
                    MainForm.screenHeight
            );
            MainForm.bufferGraphics.drawLine(
                    0,
                    MainForm.screenHeight/2,
                    MainForm.screenWidth,
                    MainForm.screenHeight/2
            );
            int dotWidth = 2;
            for (int k = 0; k<= MainForm.screenWidth; k+=5){
                MainForm.bufferGraphics.drawLine(
                        k,
                        MainForm.screenHeight/2 - dotWidth,
                        k,
                        MainForm.screenHeight/2 + dotWidth
                );
                MainForm.bufferGraphics.drawLine(
                        MainForm.screenWidth/2 - dotWidth,
                        k,
                        MainForm.screenWidth/2 + dotWidth,
                        k
                );
            }
        }
        int RIGHT = 39;
        int LEFT = 37;
        int UP = 38;
        int DOWN = 40;
        int stepTranslate = 5;
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
            MainForm.plane.setTransform(transformsPlane);
            MainForm.plane.drawShape();
            planeDataList.setElementAt("Máy bay ("+plane.getCentral().getX()+","+plane.getCentral().getY()+")",0);
            transformsPlane.set(0,new Translate(0,0));
        }
        private void drawPlane(){
            java.util.List<model.shape.Shape> shapes = new ArrayList<>();
            int widthFire = 8;
            int planeWidth = 70;
            int planeHeight = 40;
            int spaceBetweenFire = 5;
            Coordinate centralPlane = new Coordinate(40,50);
            shapes.add(
                    new Line(
                            MainForm.bufferGraphics,
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
                            MainForm.bufferGraphics,
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
                            MainForm.bufferGraphics,
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
                            MainForm.bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-(planeWidth/2)+widthFire+major,
                                    centralPlane.getY()
                            ),
                            major,
                            minor
                    )
            );
            int widthPlaneBody = 25;
            shapes.add(
                    new Rectangle(
                            MainForm.bufferGraphics,
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
                            MainForm.bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()+6+major,
                                    centralPlane.getY()
                            ),
                            major,
                            minor
                    )
            );
            shapes.add(
                    new Line(
                            MainForm.bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()+6+major*2,
                                    centralPlane.getY()-minor
                            ),
                            new Coordinate(
                                    centralPlane.getX()+6+major*2,
                                    centralPlane.getY()+minor
                            )
                    )
            );
            shapes.add(
                    new Line(
                            MainForm.bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()+3+major*2,
                                    centralPlane.getY()-minor
                            ),
                            new Coordinate(
                                    centralPlane.getX()+3+major*2+30,
                                    centralPlane.getY()
                            )
                    )
            );
            shapes.add(
                    new Line(
                            MainForm.bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()+3+major*2,
                                    centralPlane.getY()+minor
                            ),
                            new Coordinate(
                                    centralPlane.getX()+3+major*2+30,
                                    centralPlane.getY()
                            )
                    )
            );


            shapes.add(
                    new Line(
                            MainForm.bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()+5,
                                    centralPlane.getY()-minor
                            ),
                            new Coordinate(
                                    centralPlane.getX()-3,
                                    centralPlane.getY()-planeHeight/2
                            )
                    )
            );
            shapes.add(
                    new Line(
                            MainForm.bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-3,
                                    centralPlane.getY()-planeHeight/2
                            ),
                            new Coordinate(
                                    centralPlane.getX()-19,
                                    centralPlane.getY()-planeHeight/2
                            )
                    )
            );
            shapes.add(
                    new Line(
                            MainForm.bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-19,
                                    centralPlane.getY()-planeHeight/2
                            ),
                            new Coordinate(
                                    centralPlane.getX()-19,
                                    centralPlane.getY()-minor
                            )
                    )
            );
            shapes.add(
                    new Line(
                            MainForm.bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-19,
                                    centralPlane.getY()-planeHeight/2
                            ),
                            new Coordinate(
                                    centralPlane.getX()-19,
                                    centralPlane.getY()+planeHeight/2
                            )
                    )
            );
            shapes.add(
                    new Line(
                            MainForm.bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-19,
                                    centralPlane.getY()+planeHeight/2
                            ),
                            new Coordinate(
                                    centralPlane.getX()-3,
                                    centralPlane.getY()+planeHeight/2
                            )
                    )
            );
            shapes.add(
                    new Line(
                            MainForm.bufferGraphics,
                            new Coordinate(
                                    centralPlane.getX()-3,
                                    centralPlane.getY()+planeHeight/2
                            ),
                            new Coordinate(
                                    centralPlane.getX()+5,
                                    centralPlane.getY()+9
                            )
                    )
            );
            MainForm.plane = new Plane(MainForm.bufferGraphics,centralPlane,planeWidth,planeHeight,shapes);
            MainForm.plane.drawShape();
            planeDataList.addElement("Máy bay ("+plane.getCentral().getX()+","+plane.getCentral().getY()+")");
        }
        void transformMeteorite(){
            if(meteoriteDataList.isEmpty()) return;
            for (int i = 0; i< MainForm.meteorites.size(); ++i){
                Meteorite meteorite = MainForm.meteorites.get(i);
                if(meteorite == null) continue;
                if(meteorite.getParent().getCentral().getX()+meteorite.getParent().getRadius()<=0){
                    MainForm.meteorites.remove(i);
                    meteoriteDataList.remove(i);
                    continue;
                }
                meteorite.setTransform();
                meteorite.drawShape();
                meteoriteDataList.setElementAt(String.format("Đĩa bay %d (%d,%d)", i + 1, meteorite.getParent().getCentral().getX(), meteorite.getParent().getCentral().getY()),i);
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
}
