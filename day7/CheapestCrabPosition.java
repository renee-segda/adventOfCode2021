package day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CheapestCrabPosition {
    
    private static Map<Integer, Integer> positionCountMap;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("day7/crabPositions.txt"));
        String[] positions = s.nextLine().split(",");
        s.close();

        // Construct map of integer positions to the number of crabs in that position
        positionCountMap = new HashMap<>();
        for (String positionString : positions) {
            int position = Integer.valueOf(positionString);
            if (!positionCountMap.keySet().contains(position)) {
                positionCountMap.put(position, 1);
            } else {
                positionCountMap.put(position, positionCountMap.get(position) + 1);
            }
        }
        
        // Identify position that all crabs can move to for minimum cost
        long minCost = Long.MAX_VALUE;
        int minPosition = 0;
        int maxPosition = Collections.max(positionCountMap.keySet());
        for (int staticPosition = minPosition; staticPosition <= maxPosition; staticPosition++) {
            long currentCost = 0;
            for (Integer movingPosition : positionCountMap.keySet()) {
                if (staticPosition != movingPosition) {
                    currentCost += calculateComplexMovementCost(staticPosition, movingPosition);
                }
            }
            if (currentCost < minCost) {
                minCost = currentCost;
            }
        }

        System.out.println(minCost);
    }

    /**
     * Using the constant cost formula (1 move = 1 cost unit), this method calculates the total
     * cost of all crabs with the initial position movingPosition moving to the staticPosition
     * @param staticPosition The position the crabs will be moving to
     * @param movingPosition The position the crabs originated at
     * @return The cost of all crabs at movingPosition moving to staticPosition
     */
    private static long calculateConstantMovementCost(int staticPosition, int movingPosition) {
        return positionCountMap.get(movingPosition) * Math.abs(movingPosition - staticPosition);
    }

    /**
     * Using the complex cost formula (# of move = # cost units), this method calculates the total
     * cost of all crabs with the initial position movingPosition moving to the staticPosition
     * @param staticPosition The position the crabs will be moving to
     * @param movingPosition The position the crabs originated at
     * @return The cost of all crabs at movingPosition moving to staticPosition
     */
    private static long calculateComplexMovementCost(int staticPosition, int movingPosition) {
        int numMovements = positionCountMap.get(movingPosition);
        int moveDistance = Math.abs(movingPosition - staticPosition);
        int costSum = 0;
        for (int i = 1; i <= moveDistance; i++) {
            costSum += i;
        }
        return numMovements * costSum;
    }

}
