package day12.part1;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class CaveGraph {
    private Set<CaveConnection> edges;
    private Set<Cave> vertices;
    private Cave startCave;
    private Cave endCave;

    private static final String START_CAVE_NAME = "start";
    private static final String END_CAVE_NAME = "end";

    public CaveGraph() {
        edges = new HashSet<>();
        vertices = new HashSet<>();
    }

    public void addConnection(String caveName1, String caveName2) {
        Cave c1 = getCaveByName(caveName1);
        Cave c2 = getCaveByName(caveName2);
        if (c1 == null) {
            c1 = new Cave(caveName1);
            vertices.add(c1);
        }
        if (c2 == null) {
            c2 = new Cave(caveName2);
            vertices.add(c2);
        }
        CaveConnection c1c2Connection = new CaveConnection(c1, c2);
        if (caveName1.equals(START_CAVE_NAME)) {
            if (startCave == null) {
                startCave = c1;
            }
            c1c2Connection.setVertex1(startCave);
        }
        if (caveName1.equals(END_CAVE_NAME)) {
            if (endCave == null) {
                endCave = c1;
            }
            c1c2Connection.setVertex1(endCave);
        }
        if (caveName2.equals(START_CAVE_NAME)) {
            if (startCave == null) {
                startCave = c2;
            }
            c1c2Connection.setVertex2(startCave);
        }
        if (caveName2.equals(END_CAVE_NAME)) {
            if (endCave == null) {
                endCave = c2;
            }
            c1c2Connection.setVertex2(endCave);
        }
        edges.add(c1c2Connection);
    }

    public Cave getCaveByName(String caveName) {
        for (Cave c : vertices) {
            if (c.getCaveName().equals(caveName)) {
                return c;
            }
        }
        return null;
    }

    public List<CaveConnection> getEdgesContainingVertex(Cave c) {
        List<CaveConnection> connections = new ArrayList<>();
        for (CaveConnection edge : edges) {
            if (edge.containsCave(c)) {
                connections.add(edge);
            }
        }
        return connections;
    }

    public CaveConnection getEdgeFromVertices(Cave c1, Cave c2) {
        for (CaveConnection edge : edges) {
            if (edge.containsCave(c1) && edge.containsCave(c2)) {
                return edge;
            }
        }
        throw new NoSuchElementException(String.format(
            "No edge in graph with vertices %s and %s", 
            c1.toString(), c2.toString()));
    }

    public void removeEdge(CaveConnection connection) {
        edges.remove(connection);
    }

    public void removeVertex(Cave c) {
        vertices.remove(c);
    }

    public Set<Cave> getVertices() {
        return vertices;
    }

    public Set<CaveConnection> getEdges() {
        return edges;
    }

    public Cave getStartCave() {
        return startCave;
    }

    public Cave getEndCave() {
        return endCave;
    }
    
}
