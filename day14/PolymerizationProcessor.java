package day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PolymerizationProcessor {
    
    private Map<String, String> polymerElementMap;
    private Map<String, Long> polymerCountMap;
    private Map<String, Long> elementCountMap;

    public PolymerizationProcessor(List<String> polymerPairLines, String initialTemplate) {
        polymerElementMap = new HashMap<>();
        polymerCountMap = new HashMap<>();
        elementCountMap = new HashMap<>();
        for (String line : polymerPairLines) {
            String[] polymerElementArray = line.split(" -> ");
            polymerElementMap.put(polymerElementArray[0], polymerElementArray[1]);
        }
        for (String polymer : polymerElementMap.keySet()) {
            polymerCountMap.put(polymer, countNumSubstringOccurrences(initialTemplate, polymer));
        }
        for (String element : polymerElementMap.values()) {
            elementCountMap.put(element, countNumSubstringOccurrences(initialTemplate, element));
        }
    }

    private long countNumSubstringOccurrences(String parentString, String substring) {
        int count = 0;
        int foundIndex = 0;
        while (foundIndex >= 0) {
            foundIndex = parentString.indexOf(substring, foundIndex);
            if (foundIndex >= 0) {
                count++;
                foundIndex += substring.length();
            }
        }
        return count;
    }

    public void completePolymerizationStep() {
        Map<String, Long> polymerIncrementMap = new HashMap<>();
        for (String polymer : polymerCountMap.keySet()) {
            if (polymerCountMap.get(polymer) > 0) {
                long incrementValue = polymerCountMap.get(polymer);
                String leadingResultPolymer = polymer.charAt(0) + polymerElementMap.get(polymer);
                if (!polymerIncrementMap.keySet().contains(leadingResultPolymer)) {
                    polymerIncrementMap.put(leadingResultPolymer, Long.valueOf(0));
                }
                polymerIncrementMap.put(leadingResultPolymer, polymerIncrementMap.get(leadingResultPolymer) + incrementValue);
                String tailingResultPolymer = polymerElementMap.get(polymer) + polymer.charAt(1);
                if (!polymerIncrementMap.keySet().contains(tailingResultPolymer)) {
                    polymerIncrementMap.put(tailingResultPolymer, Long.valueOf(0));
                }
                polymerIncrementMap.put(tailingResultPolymer, polymerIncrementMap.get(tailingResultPolymer) + incrementValue);

                elementCountMap.put(polymerElementMap.get(polymer), elementCountMap.get(polymerElementMap.get(polymer)) + incrementValue);

                polymerCountMap.put(polymer, Long.valueOf(0));
            }
        }
        for (String polymer : polymerIncrementMap.keySet()) {
            polymerCountMap.put(polymer, polymerCountMap.get(polymer) + polymerIncrementMap.get(polymer));
        }
    }

    public Map<String, Long> getPolymerCountMap() {
        return polymerCountMap;
    }

    public Map<String, Long> getElementCountMap() {
        return elementCountMap;
    }
}
