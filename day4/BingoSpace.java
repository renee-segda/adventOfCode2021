package day4;

public class BingoSpace {
    
    private boolean isMarked;
    private int spaceNumber;

    public BingoSpace() {
        setIsMarked(false);
    }

    public BingoSpace(int spaceNumber) {
        this();
        setSpaceNumber(spaceNumber);
    }

    public void setIsMarked(boolean isMarked) {
        this.isMarked = isMarked;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public int getSpaceNumber() {
        return spaceNumber;
    }

    public void setSpaceNumber(int spaceNumber) {
        this.spaceNumber = spaceNumber;
    }

    public String toString() {
        return spaceNumber + ":" + (isMarked ? "+" : "-");
    }

}
