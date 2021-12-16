package day13;

public class MarkedSpace {
    
    private int xCoor;
    private int yCoor;

    public MarkedSpace(int x, int y) {
        xCoor = x;
        yCoor = y;
    }

    public int getXCoor() {
        return xCoor;
    }

    public int getYCoor() {
        return yCoor;
    }

    public String toString() {
        return String.format("%d,%d", xCoor, yCoor);
    }

    public boolean equals(Object o) {
        if (!(o instanceof MarkedSpace)) {
            return false;
        }
        MarkedSpace other = (MarkedSpace)o;
        return xCoor == other.xCoor && yCoor == other.yCoor;
    }

    public int hashCode() {
        return toString().hashCode();
    }

}
