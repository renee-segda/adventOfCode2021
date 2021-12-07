package day5;

import java.util.ArrayList;
import java.util.List;

public class VentLine {

    private Coordinate startCoor;
    private Coordinate endCoor;
    private double lineSlope;


    public VentLine(Coordinate startCoor, Coordinate endCoor) {
        this.startCoor = startCoor;
        this.endCoor = endCoor;
        lineSlope = calculateSlope();
    }

    public Coordinate getStartCoor() {
        return startCoor;
    }

    public Coordinate getEndCoor() {
        return endCoor;
    }

    public double getLineSlope() {
        return lineSlope;
    }

    /**
     * Returns a list of all the coordinates in the current line segment
     * @return All coordinates in the line segment
     */
    public List<Coordinate> getCoordinatesInLine() {
        List<Coordinate> lineCoors = new ArrayList<>();
        if (Double.isInfinite(lineSlope)) {
            double xVal = startCoor.getXVal();
            if (lineSlope > 0) {
                for (double i = startCoor.getYVal(); i <= endCoor.getYVal(); i++) {
                    lineCoors.add(new Coordinate(xVal, i));
                }
            } else {
                for (double i = startCoor.getYVal(); i >= endCoor.getYVal(); i--) {
                    lineCoors.add(new Coordinate(xVal, i));
                }
            }
        } else if (Math.abs(lineSlope) == 0) {
            double yVal = startCoor.getYVal();
            if (endCoor.getXVal() - startCoor.getXVal() > 0) {
                for (double i = startCoor.getXVal(); i <= endCoor.getXVal(); i++) {
                    lineCoors.add(new Coordinate(i, yVal));
                }
            } else {
                for (double i = startCoor.getXVal(); i >= endCoor.getXVal(); i--) {
                    lineCoors.add(new Coordinate(i, yVal));
                }
            }
        } else {
            double currentX = startCoor.getXVal();
            double currentY = startCoor.getYVal();
            if (lineSlope > 0) {
                if (startCoor.getYVal() > endCoor.getYVal()) {
                    while (currentX >= endCoor.getXVal() && currentY >= endCoor.getYVal()) {
                        lineCoors.add(new Coordinate(currentX, currentY));
                        currentX--;
                        currentY--;
                    }
                } else {
                    while (currentX <= endCoor.getXVal() && currentY <= endCoor.getYVal()) {
                        lineCoors.add(new Coordinate(currentX, currentY));
                        currentX++;
                        currentY++;
                    }
                }
            } else {
                if (startCoor.getXVal() > endCoor.getXVal()) {
                    while (currentX >= endCoor.getXVal() && currentY <= endCoor.getYVal()) {
                        lineCoors.add(new Coordinate(currentX, currentY));
                        currentX--;
                        currentY++;
                    }
                } else {
                    while (currentX <= endCoor.getXVal() && currentY >= endCoor.getYVal()) {
                        lineCoors.add(new Coordinate(currentX, currentY));
                        currentX++;
                        currentY--;
                    }
                }
            }
        }
        return lineCoors;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s");
    }

    private double calculateSlope() {
        return (endCoor.getYVal() - startCoor.getYVal()) / (endCoor.getXVal() - startCoor.getXVal());
    }
    
}
