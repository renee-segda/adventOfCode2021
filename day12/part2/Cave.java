package day12.part2;
public class Cave {
    
    private boolean isSmallCave;
    private String caveName;
    private int numTimesVisited;

    public Cave(String caveId) {
        this.caveName = caveId;
        isSmallCave = caveId.toLowerCase().equals(caveId);
        numTimesVisited = 0;
    }

    public int getNumTimesVisited() {
        return numTimesVisited;
    }

    public void incrementTimeVisited() {
        numTimesVisited += 1;
    }

    public void decrementTimeVisited() {
        numTimesVisited -= 1;
    }

    public String getCaveName() {
        return caveName;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Cave)) {
            return false;
        }
        Cave otherCave = (Cave)o;
        return caveName.equals(otherCave.caveName);
    }

    public int hashCode() {
        return caveName.hashCode();
    }

    public String toString() {
        return caveName;
    }

    public boolean isSmallCave() {
        return isSmallCave;
    }


}
