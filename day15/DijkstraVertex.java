package day15;

public class DijkstraVertex implements Comparable<DijkstraVertex> {
    
    private CavePosition position;
    private int minRisk;

    public DijkstraVertex(CavePosition c) {
        position = c;
        minRisk = Integer.MAX_VALUE;
    }

    public int getMinRisk() {
        return minRisk;
    }

    public void setMinRisk(int minRisk) {
        this.minRisk = minRisk;
    }

    public CavePosition getPosition() {
        return position;
    }

    @Override
    public int compareTo(DijkstraVertex other) {
        return Integer.compare(minRisk, other.minRisk);
    }

    public String toString() {
        return String.format("(%d,%d): min risk %d", position.getXCoor(), position.getYCoor(), minRisk);
    }

}
