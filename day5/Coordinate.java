package day5;

public class Coordinate {
    
    private double xVal;
    private double yVal;

    public Coordinate(double xVal, double yVal) {
        this.xVal = xVal;
        this.yVal = yVal;
    }

    public double getXVal() {
        return xVal;
    }

    public double getYVal() {
        return yVal;
    }

    @Override
    public boolean equals(Object o) {
        Coordinate c = (Coordinate)o;
        return this.xVal == c.xVal && this.yVal == c.yVal;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    @Override
    public String toString() {
        return String.format("%d,%d", (int)xVal, (int)yVal);
    }



}
