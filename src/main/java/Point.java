public class Point {

    Double x, y , z;

    public Point(Double x, Double y, Double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public Point(){
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getZ() {
        return z;
    }

@Override
    public String toString() {
        return "(" + x + ", " + y + ", " + z+ ")";
}
}
