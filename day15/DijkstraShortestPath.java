package day15;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DijkstraShortestPath {

    private static final Set<DijkstraVertex> leastRiskPathVertices = new HashSet<>();
    private static List<DijkstraVertex> unselectedVertices;
    private static List<DijkstraVertex[]> vertexGrid;
    private static int numRows;
    private static int numCols;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("day15/caveRiskInput.txt"));
        unselectedVertices = new ArrayList<>();
        vertexGrid = new ArrayList<>();
        int currentRowIndex = 0;
        while (s.hasNextLine()) {
            String[] currentRowPositionRisks = s.nextLine().split("");
            vertexGrid.add(new DijkstraVertex[currentRowPositionRisks.length]);
            int currentColIndex = 0;
            for (String risk : currentRowPositionRisks) {
                CavePosition position = new CavePosition(currentRowIndex, currentColIndex, Integer.valueOf(risk));
                vertexGrid.get(currentRowIndex)[currentColIndex] = new DijkstraVertex(position);
                unselectedVertices.add(vertexGrid.get(currentRowIndex)[currentColIndex]);
                if (currentRowIndex == 0 && currentColIndex == 0) {
                    vertexGrid.get(currentRowIndex)[currentColIndex].setMinRisk(0);
                }
                currentColIndex++;
            }
            currentRowIndex++;
        }
        s.close();
        numRows = vertexGrid.size();
        numCols = vertexGrid.get(0).length;

        int numVertices = unselectedVertices.size();

        while (leastRiskPathVertices.size() != numVertices) {
            DijkstraVertex nearestVertex = unselectedVertices.remove(0);
            leastRiskPathVertices.add(nearestVertex);
            updateMinRiskAdjacentVertices(nearestVertex);
            Collections.sort(unselectedVertices);
        }

        for (DijkstraVertex v : leastRiskPathVertices) {
            if (v.getPosition().getXCoor() == numRows - 1 && v.getPosition().getYCoor() == numCols - 1) {
                System.out.println("Risk level of least risky path: " + v.getMinRisk());
            }
        }

    }

    private static void updateMinRiskAdjacentVertices(DijkstraVertex v) {
        CavePosition position = v.getPosition();
        DijkstraVertex neighbor;
        if (position.getXCoor() - 1 >= 0) {
            neighbor = vertexGrid.get(position.getXCoor() - 1)[position.getYCoor()];
            int currentMinRisk = neighbor.getMinRisk();
            if (v.getMinRisk() + neighbor.getPosition().getRisk() < currentMinRisk) {
                neighbor.setMinRisk(v.getMinRisk() + neighbor.getPosition().getRisk());
            }
        }
        if (position.getXCoor() + 1 < numRows) {
            neighbor = vertexGrid.get(position.getXCoor() + 1)[position.getYCoor()];
            int currentMinRisk = neighbor.getMinRisk();
            if (v.getMinRisk() + neighbor.getPosition().getRisk() < currentMinRisk) {
                neighbor.setMinRisk(v.getMinRisk() + neighbor.getPosition().getRisk());
            }
        }
        if (position.getYCoor() - 1 >= 0) {
            neighbor = vertexGrid.get(position.getXCoor())[position.getYCoor() - 1];
            int currentMinRisk = neighbor.getMinRisk();
            if (v.getMinRisk() + neighbor.getPosition().getRisk() < currentMinRisk) {
                neighbor.setMinRisk(v.getMinRisk() + neighbor.getPosition().getRisk());
            }
        }
        if (position.getYCoor() + 1 < numCols) {
            neighbor = vertexGrid.get(position.getXCoor())[position.getYCoor() + 1];
            int currentMinRisk = neighbor.getMinRisk();
            if (v.getMinRisk() + neighbor.getPosition().getRisk() < currentMinRisk) {
                neighbor.setMinRisk(v.getMinRisk() + neighbor.getPosition().getRisk());
            }
        }
    }

}
