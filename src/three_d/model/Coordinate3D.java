package three_d.model;

public class Coordinate3D {
    private int x;
    private int y;
    private int z;

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y + ", z=" + z+
                '}';
    }

    public Coordinate3D(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Coordinate3D coordinate) {
        return (coordinate.getX() == getX() && coordinate.getY() == getY() && coordinate.getZ() == getZ());
    }

    public Coordinate convertTo2D() {
        return new Coordinate(this.x - this.z/2,this.y - this.z/2);
    }

    public int getX() {
        return x;
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

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }
}
