package day2;

/**
 * Data representation of a submarine; it can move horizontally and up/down to 
 * update its position and aim.
 */
public class Submarine {
    
    /**
     * Current horizontal position of the submarine
     */
    private int horizontalCoor;

    /**
     * Current depth of the submarine
     */
    private int depthCoor;

    /**
     * Current aim of the submarine
     */
    private int aim;

    /**
     * Constructor which initializes all position and aim values to 0
     */
    public Submarine() {
        horizontalCoor = 0;
        depthCoor = 0;
        aim = 0;
    }

    /**
     * Increases the submarine's horizontal position by the given value and 
     * the depth by the product of the current aim and the given value
     * @param moveUnits The number of units to change the position by
     */
    public void moveForward(int moveUnits) {
        horizontalCoor += moveUnits;
        depthCoor += aim * moveUnits;
    }

    /**
     * Increases the submarine's depth by the given value
     * @param moveUnits The number of units to change the position by
     */
    public void moveDown(int moveUnits) {
        aim += moveUnits;
    }

    /**
     * Decreases the submarine's depth by the given value
     * @param moveUnits The number of units to change the position by
     */
    public void moveUp(int moveUnits) {
        aim -= moveUnits;
    }

    /**
     * Retrieves the submarine's current horizontal position
     * @return The submarine's horizontal position
     */
    public int getHorizontalCoor() {
        return horizontalCoor;
    }

    /**
     * Retrieves the current depth of the submarine
     * @return The submarine's depth
     */
    public int getDepthCoor() {
        return depthCoor;
    }
}
