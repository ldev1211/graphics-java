package three_d;

import three_d.config.Constants;
import three_d.model.Coordinate3D;
import three_d.model.CoordinateAxis;
import three_d.model.shape.Cylinder;
import three_d.model.shape.Globular;
import three_d.model.shape.Rectangular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Form3D extends JFrame {
    JButton btnDrawRectangular;
    JButton btnDrawHinhTru;
    JButton btnDrawHinhCau;
    JButton btnConfirm;
    CoordinateAxis coorAxis;

    JTextField tfX;
    JTextField tfY;
    JTextField tfZ;
    JTextField tfLength;
    JTextField tfWidth;
    JTextField tfHeight;
    JTextField tfRadius;

    JTextArea coordinateInfoLabel;


    Rectangular rectangular;
    Globular globular;
    Cylinder cylinder;

    String TAG = "NONE";

    public Form3D() {
        initComponent();

        //set initial state for TAG and button
        TAG = "RECTANGULAR";
        tfRadius.setEnabled(false);
        tfRadius.setBackground(Color.GRAY);
        btnDrawRectangular.setBackground(Color.GREEN);

        setSize(Constants.RESOLUTION_WIDTH, Constants.RESOLUTION_HEIGHT);
        setTitle("3D Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                tfX.requestFocus();
            }
        });

        btnDrawRectangular.addActionListener(e -> {
//            resetAll();

            tfLength.setEnabled(true);
            tfWidth.setEnabled(true);
            tfHeight.setEnabled(true);
            tfLength.setBackground(Color.WHITE);
            tfWidth.setBackground(Color.WHITE);
            tfHeight.setBackground(Color.WHITE);
            tfX.requestFocus();

            tfRadius.setEnabled(false);
            tfRadius.setBackground(Color.GRAY);

            TAG = "RECTANGULAR";
            btnDrawRectangular.setBackground(Color.GREEN);
            btnDrawHinhCau.setBackground(Color.WHITE);
            btnDrawHinhTru.setBackground(Color.WHITE);
        });

        btnDrawHinhCau.addActionListener(e -> {
//            resetAll();

            tfRadius.setEnabled(true);
            tfRadius.setBackground(Color.WHITE);
            tfX.requestFocus();

            tfLength.setEnabled(false);
            tfWidth.setEnabled(false);
            tfHeight.setEnabled(false);
            tfLength.setBackground(Color.GRAY);
            tfWidth.setBackground(Color.GRAY);
            tfHeight.setBackground(Color.GRAY);

            TAG = "GLOBULAR";
            btnDrawHinhCau.setBackground(Color.GREEN);
            btnDrawRectangular.setBackground(Color.WHITE);
            btnDrawHinhTru.setBackground(Color.WHITE);

        });

        btnDrawHinhTru.addActionListener(e -> {
//            resetAll();

            tfRadius.setEnabled(true);
            tfHeight.setEnabled(true);
            tfRadius.setBackground(Color.WHITE);
            tfHeight.setBackground(Color.WHITE);
            tfX.requestFocus();

            tfLength.setEnabled(false);
            tfWidth.setEnabled(false);
            tfLength.setBackground(Color.GRAY);
            tfWidth.setBackground(Color.GRAY);

            TAG = "CYLINDER";
            btnDrawHinhTru.setBackground(Color.GREEN);
            btnDrawRectangular.setBackground(Color.WHITE);
            btnDrawHinhCau.setBackground(Color.WHITE);
        });

        btnConfirm.addActionListener(e -> {
            if (TAG.equals("RECTANGULAR")) {
                if (tfX.getText().equals("") || tfY.getText().equals("") || tfZ.getText().equals("") ||
                        tfLength.getText().equals("") || tfWidth.getText().equals("") || tfHeight.getText().equals("")) {
                    JOptionPane.showMessageDialog(Form3D.this, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }
            } else if (TAG.equals("GLOBULAR")) {
                if (tfX.getText().equals("") || tfY.getText().equals("") || tfZ.getText().equals("") ||
                        tfRadius.getText().equals("")) {
                    JOptionPane.showMessageDialog(Form3D.this, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }
            } else if (TAG.equals("CYLINDER")) {
                if (tfX.getText().equals("") || tfY.getText().equals("") || tfZ.getText().equals("") ||
                        tfRadius.getText().equals("") || tfHeight.getText().equals("")) {
                    JOptionPane.showMessageDialog(Form3D.this, "Vui lòng nhập đầy đủ thông tin");
                    return;
                }
            }

            try {
                if (TAG.equals("RECTANGULAR")) {
                    rectangular = new Rectangular(Form3D.this,
                            new Coordinate3D(Integer.parseInt(tfX.getText()),
                                    Integer.parseInt(tfY.getText()),
                                    Integer.parseInt(tfZ.getText())),
                            Integer.parseInt(tfLength.getText()),
                            Integer.parseInt(tfWidth.getText()),
                            Integer.parseInt(tfHeight.getText()));
                    coordinateInfoLabel.setText(rectangular.toString());
                    repaint();
                } else if (TAG.equals("GLOBULAR")) {
                    globular = new Globular(Form3D.this,
                            new Coordinate3D(Integer.parseInt(tfX.getText()),
                                    Integer.parseInt(tfY.getText()),
                                    Integer.parseInt(tfZ.getText())),
                            Integer.parseInt(tfRadius.getText()));

                    coordinateInfoLabel.setText(globular.toString());
                    repaint();
                } else if (TAG.equals("CYLINDER")) {
                    cylinder = new Cylinder(Form3D.this,
                            new Coordinate3D(Integer.parseInt(tfX.getText()),
                                    Integer.parseInt(tfY.getText()),
                                    Integer.parseInt(tfZ.getText())),
                            Integer.parseInt(tfRadius.getText()),
                            Integer.parseInt(tfHeight.getText()));

                    coordinateInfoLabel.setText(cylinder.toString());
                    repaint();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(Form3D.this, "Vui lòng chỉ nhập số nguyên");
                return;
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.RED);
        coorAxis.drawShape();

        if (rectangular != null) {

            rectangular.drawShape();
            System.out.println(rectangular.toString());
        }

        if (globular != null) {

            globular.drawShape();
            System.out.println(globular.toString());
        }

        if (cylinder != null) {

            cylinder.drawShape();
            System.out.println(cylinder.toString());
        }

        setNullAll();
    }


    void initComponent() {
//        rect = new Rectangular(Form3D.this, new Coordinate3D(0, 0, 0), 80, 50, 80);
        coorAxis = new CoordinateAxis(Form3D.this);

        tfX = new JTextField("10");
        tfY = new JTextField("0");
        tfZ = new JTextField("10");
        tfLength = new JTextField("80");
        tfWidth = new JTextField("50");
        tfHeight = new JTextField("60");
        tfRadius = new JTextField("30");

        btnDrawHinhCau = new JButton("Vẽ hình cầu");
        btnDrawHinhTru = new JButton("Vẽ hình trụ");
        btnDrawRectangular = new JButton("Vẽ hình hộp chữ nhật");
        btnConfirm = new JButton("Xác nhận vẽ");
        btnConfirm.setBackground(Color.WHITE);
        btnDrawHinhCau.setBackground(Color.WHITE);
        btnDrawHinhTru.setBackground(Color.WHITE);
        btnDrawRectangular.setBackground(Color.WHITE);

        JLabel lbX = new JLabel("X");
        JLabel lbY = new JLabel("Y");
        JLabel lbZ = new JLabel("Z");
        JLabel lbLength = new JLabel("DÀI");
        JLabel lbWidth = new JLabel("RỘNG");
        JLabel lbHeight = new JLabel("CAO");
        JLabel lbRadius = new JLabel("BÁN KÍNH");

        coordinateInfoLabel = new JTextArea("");
        coordinateInfoLabel.setLineWrap(true);
        coordinateInfoLabel.setWrapStyleWord(true);
        coordinateInfoLabel.setOpaque(false);
        coordinateInfoLabel.setBackground(new Color(0, 0, 0, 100));
        coordinateInfoLabel.setBounds(50, 50, 300, 300);
        coordinateInfoLabel.setForeground(Color.RED);

        tfX.setBounds(Constants.RESOLUTION_WIDTH - 260, 100, 50, 30);
        tfY.setBounds(Constants.RESOLUTION_WIDTH - 200, 100, 50, 30);
        tfZ.setBounds(Constants.RESOLUTION_WIDTH - 140, 100, 50, 30);
        tfLength.setBounds(Constants.RESOLUTION_WIDTH - 260, 180, 50, 30);
        tfWidth.setBounds(Constants.RESOLUTION_WIDTH - 200, 180, 50, 30);
        tfHeight.setBounds(Constants.RESOLUTION_WIDTH - 140, 180, 50, 30);
        tfRadius.setBounds(Constants.RESOLUTION_WIDTH - 200, 260, 50, 30);

        lbX.setBounds(Constants.RESOLUTION_WIDTH - 260, 70, 50, 30);
        lbY.setBounds(Constants.RESOLUTION_WIDTH - 200, 70, 50, 30);
        lbZ.setBounds(Constants.RESOLUTION_WIDTH - 140, 70, 50, 30);
        lbLength.setBounds(Constants.RESOLUTION_WIDTH - 260, 150, 50, 30);
        lbWidth.setBounds(Constants.RESOLUTION_WIDTH - 200, 150, 50, 30);
        lbHeight.setBounds(Constants.RESOLUTION_WIDTH - 140, 150, 50, 30);
        lbRadius.setBounds(Constants.RESOLUTION_WIDTH - 200, 230, 80, 30);


        btnDrawRectangular.setBounds(Constants.RESOLUTION_WIDTH - 260, 340, 200, 30);
        btnDrawHinhTru.setBounds(Constants.RESOLUTION_WIDTH - 260, 380, 200, 30);
        btnDrawHinhCau.setBounds(Constants.RESOLUTION_WIDTH - 260, 420, 200, 30);
        btnConfirm.setBounds(Constants.RESOLUTION_WIDTH - 260, 460, 200, 30);


        add(tfX);
        add(tfY);
        add(tfZ);
        add(tfLength);
        add(tfWidth);
        add(tfHeight);
        add(tfRadius);
        add(btnDrawRectangular);
        add(btnDrawHinhTru);
        add(btnDrawHinhCau);
        add(btnConfirm);
        add(lbX);
        add(lbY);
        add(lbZ);
        add(lbLength);
        add(lbWidth);
        add(lbHeight);
        add(lbRadius);
        add(coordinateInfoLabel);

        tfX.requestFocusInWindow();
    }

    void setNullAll() {
        rectangular = null;
        globular = null;
        cylinder = null;
    }

    void resetAll() {
        tfX.setText("");
        tfY.setText("");
        tfZ.setText("");
        tfLength.setText("");
        tfWidth.setText("");
        tfHeight.setText("");
        tfRadius.setText("");
        coordinateInfoLabel.setText("");
    }
}