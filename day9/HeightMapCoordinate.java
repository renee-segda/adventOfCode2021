public class HeightMapCoordinate {
        
    private int rowIndex;
    private int colIndex;

    public HeightMapCoordinate(int row, int col) {
        rowIndex = row;
        colIndex = col;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColIndex() {
        return colIndex;
    }

    public boolean equals(Object s) {
        if (!(s instanceof HeightMapCoordinate)) {
            return false;
        }
        return ((HeightMapCoordinate)s).getRowIndex() == rowIndex && 
            ((HeightMapCoordinate)s).getColIndex() == colIndex;
    }

    public int hashCode() {
        return String.format("[%d,%d]", rowIndex, colIndex).hashCode();
    }
}