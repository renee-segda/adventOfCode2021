import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HeightMap {
    
    private int[][] heightMapMatrix;
    private final int minRowIndex = 0;
    private final int minColIndex = 0;
    private final int maxHeight = 9;
    private int maxRowIndex;
    private int maxColIndex;

    public HeightMap(List<String> heightMapRows) {
        heightMapMatrix = new int[heightMapRows.size()][heightMapRows.get(0).length()];
        maxRowIndex = heightMapRows.size() - 1;
        maxColIndex = heightMapRows.get(0).length() - 1;
        for (int rowIndex = 0; rowIndex < heightMapRows.size(); rowIndex++) {
            String rowString = heightMapRows.get(rowIndex);
            for (int colIndex = 0; colIndex < rowString.length(); colIndex++) {
                heightMapMatrix[rowIndex][colIndex] = Integer.valueOf(String.valueOf(rowString.charAt(colIndex)));
            }
        }
    }

    public int[][] getHeightMapMatrix() {
        return heightMapMatrix;
    }

    public List<HeightMapCoordinate> getLowPoints() {
        List<HeightMapCoordinate> lowPointList = new ArrayList<>();
        for (int i = 0; i <= maxRowIndex; i++) {
            for (int j = 0; j <= maxColIndex; j++){
                if (isLowPoint(i, j)) {
                    lowPointList.add(new HeightMapCoordinate(i, j));
                }
            }
        }
        return lowPointList;
    }

    /**
     * Identifies all low points in the height map and sums the risk levels of all the points
     * @return The sum of all the risk levels
     */
    public int calculateRiskLevelSum() {
        int riskLevelSum = 0;
        List<HeightMapCoordinate> lowPoints = getLowPoints();
        for (HeightMapCoordinate coordinate : lowPoints) {
            riskLevelSum += getHeight(coordinate) + 1;
        }
        return riskLevelSum;
    }

    public int getHeight(HeightMapCoordinate coordinate) {
        return heightMapMatrix[coordinate.getRowIndex()][coordinate.getColIndex()];
    }

    public int getBasinSize(HeightMapCoordinate lowPoint) {
        Set<HeightMapCoordinate> basinCoordinateSet = new HashSet<>();
        basinCoordinateSet = getBasinCoordinates(lowPoint, basinCoordinateSet);
        return basinCoordinateSet.size();
    }

    private Set<HeightMapCoordinate> getBasinCoordinates(HeightMapCoordinate coordinate, 
                                                         Set<HeightMapCoordinate> basinCoordinateSet) {
        if (!basinCoordinateSet.contains(coordinate)) {
            basinCoordinateSet.add(coordinate);
        }
        if (coordinate.getRowIndex() != minRowIndex) {
            HeightMapCoordinate upperCoor = new HeightMapCoordinate(
                coordinate.getRowIndex() - 1, coordinate.getColIndex());
            if (getHeight(upperCoor) != maxHeight && !basinCoordinateSet.contains(upperCoor)) {
                getBasinCoordinates(upperCoor, basinCoordinateSet);
            }
        }
        if (coordinate.getColIndex() != maxColIndex) {
            HeightMapCoordinate rightCoor = new HeightMapCoordinate(
                coordinate.getRowIndex(), coordinate.getColIndex() + 1);
            if (getHeight(rightCoor) != maxHeight && !basinCoordinateSet.contains(rightCoor)) {
                getBasinCoordinates(rightCoor, basinCoordinateSet);
            }
        }
        if (coordinate.getRowIndex() != maxRowIndex) {
            HeightMapCoordinate lowerCoor = new HeightMapCoordinate(
                coordinate.getRowIndex() + 1, coordinate.getColIndex());
            if (getHeight(lowerCoor) != maxHeight && !basinCoordinateSet.contains(lowerCoor)) {
                getBasinCoordinates(lowerCoor, basinCoordinateSet);
            }
        }
        if (coordinate.getColIndex() != minColIndex) {
            HeightMapCoordinate leftCoor = new HeightMapCoordinate(
                coordinate.getRowIndex(), coordinate.getColIndex() - 1);
            if (getHeight(leftCoor) != maxHeight && !basinCoordinateSet.contains(leftCoor)) {
                getBasinCoordinates(leftCoor, basinCoordinateSet);
            }
        }
        return basinCoordinateSet;
    }

    /**
     * Determines if the point at the given rowIndex and colIndex has a height lower
     * than all its adjacent points
     * @param rowIndex The row index of the point to evaluate
     * @param colIndex The column index of the point to evaluate
     * @return True if the given point is a low point, false otherwise
     */
    private boolean isLowPoint(int rowIndex, int colIndex) {
        int currentHeight = heightMapMatrix[rowIndex][colIndex];
        if (rowIndex != minRowIndex && heightMapMatrix[rowIndex - 1][colIndex] <= currentHeight) {
            return false;
        }
        if (rowIndex != maxRowIndex && heightMapMatrix[rowIndex + 1][colIndex] <= currentHeight) {
            return false;
        }
        if (colIndex != minColIndex && heightMapMatrix[rowIndex][colIndex - 1] <= currentHeight) {
            return false;
        }
        if (colIndex != maxColIndex && heightMapMatrix[rowIndex][colIndex + 1] <= currentHeight) {
            return false;
        }
        return true;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < heightMapMatrix.length; i++) {
            s.append(Arrays.toString(heightMapMatrix[i]));
            s.append("\n");
        }
        return s.toString();
    }
}
