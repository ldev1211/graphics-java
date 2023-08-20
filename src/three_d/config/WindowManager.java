package three_d.config;

import model.PixelPoint;
import model.shape.Shape;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class WindowManager {
    public static Stack<Shape> stackShape = new Stack<>();
    public static Map<String, Stack<PixelPoint>> mapPixel = new HashMap<>();
    public static void saveShape(Shape shape){
        Set<Map.Entry<String, PixelPoint>> entries = shape.coordinateHashMap.entrySet();
        for(Map.Entry<String, PixelPoint> keyValue: entries){
            if(WindowManager.mapPixel.containsKey(keyValue.getKey())){
                Stack<PixelPoint> tmp = WindowManager.mapPixel.get(keyValue.getKey());
                tmp.push(keyValue.getValue());
                mapPixel.put(keyValue.getKey(),tmp);
            } else {
                WindowManager.mapPixel.put(keyValue.getKey(),new Stack<>());
                Stack<PixelPoint> tmp = WindowManager.mapPixel.get(keyValue.getKey());
                tmp.push(keyValue.getValue());
                mapPixel.put(keyValue.getKey(),tmp);
            }
        }
        stackShape.push(shape);
    }
    public static int getScreenWidth(){
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    }

    public static int getScreenHeight(){
        return (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
    }
    public static void undo(){

    }
}
