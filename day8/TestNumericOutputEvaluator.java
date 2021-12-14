package day8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TestNumericOutputEvaluator {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("day8/problemSignalInput.txt"));
        List<String> signalPatternLines = new ArrayList<>();
        while (s.hasNextLine()) {
            signalPatternLines.add(s.nextLine());
        }
        s.close();

        /** Code from part 1 of the problem */
        Set<Integer> uniqueSignalNumbers = new HashSet<>() {{
            add(1);
            add(4);
            add(7);
            add(8);
        }};
        int numUniqueSignalNumbersFound = 0;

        for (String line : signalPatternLines) {
            String[] splitLine = line.split(" \\| ");
            String[] numberReadings = splitLine[1].split(" ");
            for (String reading : numberReadings) {
                Set<Integer> numberSet = NumericOutputEvaluator.getPossibleNumberValues(reading);
                if (uniqueSignalNumbers.containsAll(numberSet)) {
                    numUniqueSignalNumbersFound++;
                }
            }
        }
        System.out.println("Part 1 answer: " + numUniqueSignalNumbersFound);


        /** Code from part 2 of the problem */
        long outputSum = 0;

        for (String signalPatternLine : signalPatternLines) {
            String signalPatternSegment = signalPatternLine.split(" \\| ")[0];
            String outputValueSegment = signalPatternLine.split(" \\| ")[1];
            Map<String, Integer> signalIntegerMap = NumericOutputEvaluator.buildSignalIntegerMap(signalPatternSegment);
            String outputCode = "";
            for (String outputValue : outputValueSegment.split(" ")) {
                char[] charArray = outputValue.toCharArray();
                Arrays.sort(charArray);
                outputCode += String.valueOf(signalIntegerMap.get(new String(charArray)));
            }
            outputSum += Long.valueOf(outputCode);
        }
        System.out.println("Part 2 answer: " + outputSum);
    }

}
