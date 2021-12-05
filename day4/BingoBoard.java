package day4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class BingoBoard {
    
    private static final int NUM_ROWS = 5;
    private static final int NUM_COLS = 5;

    private BingoSpace[][] boardSpaces;
    private Set<Integer> boardNumbers;
    private Map<Integer, Integer> boardNumberCoordinateMap;

    public BingoBoard(String boardString) {
        boardSpaces = new BingoSpace[NUM_ROWS][NUM_COLS];
        boardNumbers = new HashSet<>();
        boardNumberCoordinateMap = new HashMap<>();
        Scanner s = new Scanner(boardString);
        int numSpacesFilled = 0;
        while (s.hasNextInt()) {
            int boardValue = s.nextInt();
            boardSpaces[numSpacesFilled / NUM_COLS][numSpacesFilled % NUM_COLS] = new BingoSpace(boardValue);
            boardNumbers.add(boardValue);
            boardNumberCoordinateMap.put(boardValue, numSpacesFilled);
            numSpacesFilled++;
        }
        s.close();
    }

    public boolean hasSpace(int spaceValue) {
        return boardNumbers.contains(spaceValue);
    }

    public static int getNumRows() {
        return NUM_ROWS;
    }

    public int getSpaceCoordinate(int spaceValue) {
        return boardNumberCoordinateMap.get(spaceValue);
    }

    public void markSpace(int spaceValue) {
        int spaceCoordinate = getSpaceCoordinate(spaceValue);
        boardSpaces[spaceCoordinate / NUM_COLS][spaceCoordinate % NUM_COLS].setIsMarked(true);
    }

    public boolean checkForWin(int lastCoordinatePlayed) {
        return checkVertical(lastCoordinatePlayed % NUM_COLS) == NUM_ROWS || 
            checkHorizontal(lastCoordinatePlayed / NUM_ROWS) == NUM_COLS;
    }

    public int calculateScore(int winningSpaceValue) {
        int unmarkedSpaceValueSum = 0;
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (!boardSpaces[i][j].isMarked()) {
                    unmarkedSpaceValueSum += boardSpaces[i][j].getSpaceNumber();
                }
            }
        }
        return unmarkedSpaceValueSum * winningSpaceValue;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                s.append(boardSpaces[i][j].toString());
                s.append(" ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    private int checkVertical(int colNum) {
        int numSpacesMarked = 0;
        for (int i = 0; i < NUM_ROWS; i++) {
            if (boardSpaces[i][colNum].isMarked()) {
                numSpacesMarked++;
            }
        }
        return numSpacesMarked;
    }

    private int checkHorizontal(int rowNum) {
        int numSpacesMarked = 0;
        for (int i = 0; i < NUM_COLS; i++) {
            if (boardSpaces[rowNum][i].isMarked()) {
                numSpacesMarked++;
            }
        }
        return numSpacesMarked;
    }



}
