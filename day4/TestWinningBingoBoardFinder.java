package day4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestWinningBingoBoardFinder {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("day4/bingoInput.txt"));
        List<BingoBoard> bingoBoards = new ArrayList<>();
        String callValues = fileScanner.nextLine();
        while (fileScanner.hasNextLine()) {
            bingoBoards.add(new BingoBoard(getBoardStringFromScanner(fileScanner)));
        }
        fileScanner.close();
        Scanner lineScanner = new Scanner(callValues);
        lineScanner.useDelimiter(",");

        //Code below is for finding the score of the first winning board
       /*
        boolean winOccurred = false;
        while (lineScanner.hasNextInt() && !winOccurred) {
            int spaceValue = lineScanner.nextInt();
            winOccurred = false;
            for (BingoBoard b : bingoBoards) {
                if (b.hasSpace(spaceValue)) {
                    b.markSpace(spaceValue);
                    winOccurred = b.checkForWin(b.getSpaceCoordinate(spaceValue));
                    if (winOccurred) {
                        System.out.println(b.calculateScore(spaceValue));
                        break;
                    }
                }
            }
        }
        */

        //Code below is for finding the score of the last winning board
        int numWinningBoards = 0;
        int spaceValue = 0;
        int initialNumberOfBoards = bingoBoards.size();
        BingoBoard lastWinningBoard = null;
        while (lineScanner.hasNextInt() && numWinningBoards < initialNumberOfBoards) {
            spaceValue = lineScanner.nextInt();
            List<BingoBoard> boardsToRemove = new ArrayList<>();
            for (BingoBoard b : bingoBoards) {
                if (b.hasSpace(spaceValue)) {
                    b.markSpace(spaceValue);
                    boolean winOccurred = b.checkForWin(b.getSpaceCoordinate(spaceValue));
                    if (winOccurred) {
                        numWinningBoards++;
                        if (numWinningBoards == initialNumberOfBoards) {
                            lastWinningBoard = b;
                        } else {
                            boardsToRemove.add(b);
                        }
                    }
                }
            }
            bingoBoards.removeAll(boardsToRemove);
        }
        System.out.println(lastWinningBoard.calculateScore(spaceValue));
        
        lineScanner.close();
        
    }

    private static String getBoardStringFromScanner(Scanner fileScanner) {
        fileScanner.nextLine();
        StringBuilder boardStringBuilder = new StringBuilder();
        for (int i = 0; i < BingoBoard.getNumRows(); i++) {
            boardStringBuilder.append(fileScanner.nextLine());
            boardStringBuilder.append(System.lineSeparator());
        }
        return boardStringBuilder.toString();
    }

}
