package day12.part1;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class GraphPathFinder {

    private static CaveGraph caveGraph;
    private static long numPaths = 0;

    public static void main(String[] args) throws FileNotFoundException {
        caveGraph = new CaveGraph();
        Scanner s = new Scanner(new File("day12/problemGraph.txt"));
        while (s.hasNextLine()) {
            String[] startEndArray = s.nextLine().split("-");
            caveGraph.addConnection(startEndArray[0], startEndArray[1]);
        }
        s.close();
        
        Cave startCave = caveGraph.getStartCave();
        startCave.setVisited(true);

        goToNextVertex(startCave);

        System.out.println("Part 1 answer: " + numPaths);
    }

    private static void goToNextVertex(Cave currentCave) {
        Set<Cave> unvisitedCaves = getUnvisitedCaves(currentCave);
        if (currentCave == caveGraph.getEndCave() || (unvisitedCaves.size() == 1 && unvisitedCaves.contains(caveGraph.getEndCave()))) {
            numPaths++;
            return;
        }
        if (unvisitedCaves.isEmpty()) {
            return;
        }
        for (Cave c : unvisitedCaves) {
            c.setVisited(true);
            goToNextVertex(c);
            c.setVisited(false);
        }
    }

    private static Set<Cave> getUnvisitedCaves(Cave startCave) {
        List<CaveConnection> connectedCaves = caveGraph.getEdgesContainingVertex(startCave);
        Set<Cave> unvisitedCaves = new HashSet<>();
        connectedCaves = connectedCaves.stream().filter((connection) -> {
            Cave otherCave = connection.getOtherVertex(startCave);
            return !otherCave.isSmallCave() || 
                (otherCave.isSmallCave() && !otherCave.wasVisited());
        }).collect(Collectors.toList());
        for (CaveConnection connection : connectedCaves) {
            unvisitedCaves.add(connection.getOtherVertex(startCave));
        }
        return unvisitedCaves;

    }

}
