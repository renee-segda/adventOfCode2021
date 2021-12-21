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
    private static int numGridRows;
    private static int numGridCols;
    private static final int GRID_MULTIPLIER = 5;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("day15/caveRiskInput.txt"));
        unselectedVertices = new ArrayList<>();
        vertexGrid = new ArrayList<>();
        
        /* Grid construction for part 1 */

        // int currentRowIndex = 0;
        // while (s.hasNextLine()) {
        //     String[] currentRowPositionRisks = s.nextLine().split("");
        //     vertexGrid.add(new DijkstraVertex[currentRowPositionRisks.length]);
        //     int currentColIndex = 0;
        //     for (String risk : currentRowPositionRisks) {
        //         CavePosition position = new CavePosition(currentRowIndex, currentColIndex, Integer.valueOf(risk));
        //         vertexGrid.get(currentRowIndex)[currentColIndex] = new DijkstraVertex(position);
        //         unselectedVertices.add(vertexGrid.get(currentRowIndex)[currentColIndex]);
        //         if (currentRowIndex == 0 && currentColIndex == 0) {
        //             vertexGrid.get(currentRowIndex)[currentColIndex].setMinRisk(0);
        //         }
        //         currentColIndex++;
        //     }
        //     currentRowIndex++;
        // }
        // s.close();
        
        // numGridRows = vertexGrid.size();
        // numGridCols = vertexGrid.get(0).length;

        /* Grid construction for part 2 */

        List<String> positionRows = new ArrayList<>();

        while (s.hasNextLine()) {
            positionRows.add(s.nextLine());
        }

        int numTileRows = positionRows.size();
        int numTileCols = positionRows.get(0).length();

        numGridRows = numTileRows * GRID_MULTIPLIER;
        numGridCols = numTileCols * GRID_MULTIPLIER;
        for (int i = 0; i < numGridRows; i++) {
            vertexGrid.add(new DijkstraVertex[numGridCols]);
        }

        int[] riskLevels = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};

        for (int bigGridRowIndex = 0; bigGridRowIndex < GRID_MULTIPLIER; bigGridRowIndex++) {
            for (int gridTileRowIndex = 0; gridTileRowIndex < numTileCols; gridTileRowIndex++) {
                String[] positionRisks = positionRows.get(gridTileRowIndex).split("");
                for (int bigGridColIndex = 0; bigGridColIndex < GRID_MULTIPLIER; bigGridColIndex++) {
                    for (int gridTileColIndex = 0; gridTileColIndex < numTileCols; gridTileColIndex++) {
                        int gridRow = (bigGridRowIndex * numTileRows) + gridTileRowIndex;
                        int gridCol = (bigGridColIndex * numTileCols) + gridTileColIndex;
                        int riskLevelIndex = (Integer.valueOf(positionRisks[gridTileColIndex]) + bigGridColIndex + bigGridRowIndex - 1) % riskLevels.length;
                        CavePosition p = new CavePosition(gridRow, gridCol, riskLevels[riskLevelIndex]);
                        vertexGrid.get(gridRow)[gridCol] = new DijkstraVertex(p);
                        unselectedVertices.add(vertexGrid.get(gridRow)[gridCol]);
                        if (gridRow == 0 && gridCol == 0) {
                            vertexGrid.get(gridRow)[gridCol].setMinRisk(0);
                        }
                    }
                }
            }
        }

        int numVertices = unselectedVertices.size();

        while (leastRiskPathVertices.size() != numVertices) {
            DijkstraVertex nearestVertex = unselectedVertices.remove(0);
            leastRiskPathVertices.add(nearestVertex);
            updateMinRiskAdjacentVertices(nearestVertex);
            Collections.sort(unselectedVertices);
        }

        for (DijkstraVertex v : leastRiskPathVertices) {
            if (v.getPosition().getXCoor() == numGridRows - 1 && v.getPosition().getYCoor() == numGridCols - 1) {
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
        if (position.getXCoor() + 1 < numGridRows) {
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
        if (position.getYCoor() + 1 < numGridCols) {
            neighbor = vertexGrid.get(position.getXCoor())[position.getYCoor() + 1];
            int currentMinRisk = neighbor.getMinRisk();
            if (v.getMinRisk() + neighbor.getPosition().getRisk() < currentMinRisk) {
                neighbor.setMinRisk(v.getMinRisk() + neighbor.getPosition().getRisk());
            }
        }
    }

}
