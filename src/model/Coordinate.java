package model;

import config.Constants;

public class Coordinate {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean equals(Coordinate coordinate) {
        return (coordinate.getX() == getX() && coordinate.getY() == getY());
    }

    //   chuyen doi toa do user ve toa do diem tren he toa do may
    public Coordinate cvtUserCoorToMachineCoor(int origin_x, int origin_y) {
        return new Coordinate(origin_x + this.x * Constants.USER_PIXEL, origin_y - this.y * Constants.USER_PIXEL);
    }

    //    chuyen toa do may ve toa do user
    public Coordinate cvtMachineCoorToUserCoor(int origin_x, int origin_y) {
        int x = this.getX() - origin_x;
        int y = origin_y - this.getY();

        x = Math.round((float)x / Constants.USER_PIXEL);
        y = Math.round((float)y / Constants.USER_PIXEL);

//        x = (int) Math.ceil((double)x / (double)h);
//        y = (int) Math.ceil((double)y / (double)h);
        return new Coordinate(x, y);
    }
}
