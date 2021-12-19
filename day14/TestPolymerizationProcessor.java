package day14;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TestPolymerizationProcessor {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("day14/polymerizationInput.txt"));
        String template = s.nextLine();
        s.nextLine(); //skip blank line
        List<String> polymerPairRulesLines = new ArrayList<>();
        while (s.hasNextLine()) {
            polymerPairRulesLines.add(s.nextLine());
        }
        s.close();

        PolymerizationProcessor processor = new PolymerizationProcessor(polymerPairRulesLines, template);
        
        int numSteps = 40;
        for (int i = 0; i < numSteps; i++) {
            processor.completePolymerizationStep();
        }

        Map<String, Long> elementCount = processor.getElementCountMap();

        long maxNumOccurrences = 0;
        long minNumOccurrences = Long.MAX_VALUE;

        for (Long count : elementCount.values()) {
            if (count > maxNumOccurrences) {
                maxNumOccurrences = count;
            }
            if (count < minNumOccurrences) {
                minNumOccurrences = count;
            }
        }

        System.out.println(String.format("Max - Min Count after %d iterations: %d", numSteps, maxNumOccurrences - minNumOccurrences));

    }
    
}
