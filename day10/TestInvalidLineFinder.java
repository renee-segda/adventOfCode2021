package day10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner; 

public class TestInvalidLineFinder {
    
    public static void main(String[] args) throws FileNotFoundException {
        List<String> chunkLines = new ArrayList<>();
        Scanner s = new Scanner(new File("day10/chunkFile.txt"));
        while (s.hasNextLine()) {
            chunkLines.add(s.nextLine());
        }
        s.close();

        InvalidLineFinder finder = new InvalidLineFinder();
        List<String> corruptedLines = new ArrayList<>();
        int scoreSum = 0;
        for (String chunkLine : chunkLines) {
            Character illegalChar = finder.getFirstIllegalChar(chunkLine);
            if (illegalChar != null) {
                scoreSum += finder.getIllegalCharScore(illegalChar);
                corruptedLines.add(chunkLine);
            }
        }

        System.out.println("Part 1 answer: " + scoreSum);

        chunkLines.removeAll(corruptedLines);
        List<Long> closingSequenceScores = new ArrayList<>();
        for (String chunkLine : chunkLines) {
            String closingSequence = finder.getClosingSequence(chunkLine);
            if (closingSequence != null && !closingSequence.isEmpty()) {
                closingSequenceScores.add(finder.getClosingSequenceScore(closingSequence));
            }
        }

        Collections.sort(closingSequenceScores);
        System.out.println("Part 2 answer: " + closingSequenceScores.get(closingSequenceScores.size() / 2));
    }

}
