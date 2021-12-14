package day11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class OctopusFlashCounter {
    
    private static int numSteps;
    private static long totalFlashes;
    private static Set<Octopus> flashedOctopiSet;
    private static Octopus[][] octopusGrid;
    private static final int MIN_ROW_INDEX = 0;
    private static final int MIN_COL_INDEX = 0;
    private static int maxRowIndex;
    private static int maxColIndex;

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("day11/octopusEnergies.txt"));
        List<String> energyLines = new ArrayList<>();
        while (s.hasNextLine()) {
            energyLines.add(s.nextLine());
        }
        s.close();
        int numRows = energyLines.size();
        maxRowIndex = numRows - 1;
        int numCols = energyLines.get(0).length();
        maxColIndex = numCols - 1;
        octopusGrid = new Octopus[numRows][numCols];
        int numOctopi = numRows * numCols;

        for (int i = 0; i <= maxRowIndex; i++) {
            String[] energyArray = energyLines.get(i).split("");
            for (int j = 0; j <= maxColIndex; j++) {
                octopusGrid[i][j] = new Octopus(Integer.valueOf(energyArray[j]), i, j);
            }
        }

        /** Code for part 1 */

    //     numSteps = 100;
    //     totalFlashes = 0;
    //     for (int currentStep = 1; currentStep <= numSteps; currentStep++) {
    //         flashedOctopiSet = new HashSet<>();
    //         for (int i = 0; i <= maxRowIndex; i++) {
    //             for (int j = 0; j <= maxColIndex; j++) {
    //                 Octopus currentOctopus = octopusGrid[i][j];
    //                 currentOctopus.incrementEnergyLevel();
    //                 if (currentOctopus.hasEnergyToFlash()) {
    //                     flashedOctopiSet.add(currentOctopus);
    //                 }
    //             }
    //         }
    //         List<Octopus> preFlashOctopi = new ArrayList<>(flashedOctopiSet);
    //         Set<Octopus> postFlashOctopi = new HashSet<>();

    //         while (!preFlashOctopi.isEmpty()) {
    //             for (Octopus o : preFlashOctopi) {
    //                 checkAdjacentOctopi(o);
    //                 postFlashOctopi.add(o);
    //             }
    //             Set<Octopus> tempSet = new HashSet<>(flashedOctopiSet);
    //             tempSet.removeAll(postFlashOctopi);
    //             preFlashOctopi = new ArrayList<>(tempSet);
    //         }
    //         postFlashOctopi = null;

    //         for (Octopus o : flashedOctopiSet) {
    //             o.flash();
    //         }

    //         totalFlashes += flashedOctopiSet.size();
    //     }

    //     System.out.println("Part 1 answer: " + totalFlashes);
    // }

    // private static void checkAdjacentOctopi(Octopus referenceOctopus) {
    //     for (int rowDiff = -1; rowDiff <= 1; rowDiff++) {
    //         for (int colDiff = -1; colDiff <= 1; colDiff++) {
    //             Octopus adjacentOctopi = null;
    //             try {
    //                 adjacentOctopi = octopusGrid[referenceOctopus.getGridRow() + rowDiff][referenceOctopus.getGridCol() + colDiff];
    //             } catch(IndexOutOfBoundsException e) {
    //                 continue;
    //             }
    //             adjacentOctopi.incrementEnergyLevel();
    //             if (adjacentOctopi.hasEnergyToFlash() && !flashedOctopiSet.contains(adjacentOctopi)) {
    //                 flashedOctopiSet.add(adjacentOctopi);
    //             }
    //         }
    //     }

    /** Code for part 2 */
    boolean simultaneousFlashOccurred = false;
    int step = 1;

    while (!simultaneousFlashOccurred) {
        flashedOctopiSet = new HashSet<>();
        for (int i = 0; i <= maxRowIndex; i++) {
            for (int j = 0; j <= maxColIndex; j++) {
                Octopus currentOctopus = octopusGrid[i][j];
                currentOctopus.incrementEnergyLevel();
                if (currentOctopus.hasEnergyToFlash()) {
                    flashedOctopiSet.add(currentOctopus);
                }
            }
        }
        List<Octopus> preFlashOctopi = new ArrayList<>(flashedOctopiSet);
        Set<Octopus> postFlashOctopi = new HashSet<>();

        while (!preFlashOctopi.isEmpty()) {
            for (Octopus o : preFlashOctopi) {
                checkAdjacentOctopi(o);
                postFlashOctopi.add(o);
            }
            Set<Octopus> tempSet = new HashSet<>(flashedOctopiSet);
            tempSet.removeAll(postFlashOctopi);
            preFlashOctopi = new ArrayList<>(tempSet);
        }
        postFlashOctopi = null;

        for (Octopus o : flashedOctopiSet) {
            o.flash();
        }

        simultaneousFlashOccurred = flashedOctopiSet.size() == numOctopi;

        if (simultaneousFlashOccurred) {
            System.out.println("Part 2 answer: Simultaneous flash occurred on turn " + step);
        }
        step++;
    }
}

private static void checkAdjacentOctopi(Octopus referenceOctopus) {
    for (int rowDiff = -1; rowDiff <= 1; rowDiff++) {
        for (int colDiff = -1; colDiff <= 1; colDiff++) {
            Octopus adjacentOctopi = null;
            try {
                adjacentOctopi = octopusGrid[referenceOctopus.getGridRow() + rowDiff][referenceOctopus.getGridCol() + colDiff];
            } catch(IndexOutOfBoundsException e) {
                continue;
            }
            adjacentOctopi.incrementEnergyLevel();
            if (adjacentOctopi.hasEnergyToFlash() && !flashedOctopiSet.contains(adjacentOctopi)) {
                flashedOctopiSet.add(adjacentOctopi);
            }
        }
    }




        
    }

    public static String printOctopusGrid() {
        StringBuilder builder = new StringBuilder();
        for (int i = MIN_ROW_INDEX; i <= maxRowIndex; i++) {
            for (int j = MIN_COL_INDEX; j <= maxColIndex; j++) {
                builder.append(octopusGrid[i][j].getEnergyLevel());
                builder.append(",");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
