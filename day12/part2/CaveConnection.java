package day12.part2;

public class CaveConnection {
    
    private Cave vertex1;
    private Cave vertex2;

    public CaveConnection(Cave v1, Cave v2) {
        vertex1 = v1;
        vertex2 = v2;
    }

    public Cave getVertex1() {
        return vertex1;
    }

    public void setVertex1(Cave c) {
        vertex1 = c;
    }

    public Cave getVertex2() {
        return vertex2;
    }
    
    public void setVertex2(Cave c) {
        vertex2 = c;
    }

    public boolean containsCave(Cave c) {
        return vertex1 == c || vertex2 == c;
    }

    public Cave getOtherVertex(Cave c) {
        if (!containsCave(c)) {
            throw new IllegalArgumentException(
                "CaveConnection doesn't contain " + c.toString());
        }
        if (c == vertex1) {
            return vertex2;
        } else {
            return vertex1;
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof CaveConnection)) {
            return false;
        }
        CaveConnection c = (CaveConnection)o;
        return (c.vertex1 == vertex1 && c.vertex2 == vertex2) ||
            (c.vertex1 == vertex2 && c.vertex2 == vertex1);
    }

    public String toString() {
        String connectionFormat = "%s-%s";
        if (vertex1.getCaveName().compareTo(vertex2.getCaveName()) < 0) {
            return String.format(connectionFormat, vertex1.getCaveName(), vertex2.getCaveName());
        } else {
            return String.format(connectionFormat, vertex2.getCaveName(), vertex1.getCaveName());
        }
    }

    public int hashCode() {
        return toString().hashCode();
    }
}
