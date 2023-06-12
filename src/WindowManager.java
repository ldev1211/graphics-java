import java.awt.*;
import java.util.Stack;

public class WindowManager {
    public static Stack<Shape> stackShape = new Stack<>();
    public static int getScreenWidth(){
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    public static int getScreenHeight(){
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }

    public static void undo(){

    }
}
