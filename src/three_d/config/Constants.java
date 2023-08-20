package three_d.config;

import java.awt.*;

public final class Constants {
    public Constants() {
    }

    public static final int RESOLUTION_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
    public static final int RESOLUTION_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

    public static final int USER_PIXEL = 5; // 1 user_pixel = n machine pixel
    public static final int DOT_RADIUS = 5;
    public static final int DASH_RADIUS = 3;

    public static final int ORIGIN_X = RESOLUTION_WIDTH * 35 / 100;
    public static final int ORIGIN_Y = RESOLUTION_HEIGHT * 60 / 100;

}
