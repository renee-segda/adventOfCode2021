package day12.part1;
public class Cave {
    
    private boolean isSmallCave;
    private String caveName;
    private boolean visited;

    public Cave(String caveId) {
        this.caveName = caveId;
        isSmallCave = caveId.toLowerCase().equals(caveId);
        visited = false;
    }

    public boolean wasVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
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
