package day15;

public class CavePosition {

    private int xCoor;
    private int yCoor;
    private int risk;

    public CavePosition(int x, int y, int risk) {
        xCoor = x;
        yCoor = y;
        this.risk = risk;
    }

    public int getXCoor() {
        return xCoor;
    }

    public int getYCoor() {
        return yCoor;
    }

    public int getRisk() {
        return risk;
    }

    public String toString() {
        return String.format("(%d,%d): %d", xCoor, yCoor, risk);
    }

    public boolean equals(Object o) {
        if (!(o instanceof CavePosition)) {
            return false;
        }
        CavePosition v = (CavePosition)o;
        return v.xCoor == xCoor && v.yCoor == yCoor && v.risk == risk;
    }

    public int hashCode() {
        return toString().hashCode();
    }
    
}
