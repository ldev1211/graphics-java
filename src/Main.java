import form.MainForm;
import three_d.Form3D;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Main {
    static JButton btn2D = new JButton("Chế độ 2D");
    static JButton btn3D = new JButton("Chế độ 3D");
    static MainForm twoDForm;
    static Form3D threeDForm;
    public static void main(String[] args) {
        // Create and set up a frame window
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("BoxLayout Example X_AXIS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo mới Panel
        JPanel panel = new JPanel();

        // Tạo Boxlayout với hằng số X_AXIS
        BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.X_AXIS);

        panel.setLayout(boxlayout);

        // Tạo Border cho panel
        panel.setBorder(new EmptyBorder(new Insets(150, 200, 150, 200)));

        // Thêm Button vào Panel
        panel.add(btn2D);
        panel.add(btn3D);

        btn3D.addActionListener(e -> threeDForm = new Form3D());
        btn2D.addActionListener(e -> twoDForm = new MainForm());
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}