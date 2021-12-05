package day3;

import java.util.List;
import java.util.stream.Collectors;

public class DiagnosticsCalculator {
    
    /**
     * Counts the number of strings that have a 0 or a 1 as the character at the given index. 
     * Returns the counts in an array, where the index of the array indicates the bit value 
     * the count is for ([countsFor0Bit, countsFor1Bit]).
     * @param index The index of the strings which should be checked
     * @param binaryStrings A collection containing the strings to evaluate
     * @return An array containing the count values
     */
    private static int[] getBitCountForIndex(int index, List<String> binaryStrings) {
        int[] bitCount = new int[2];
        for (String binaryString : binaryStrings) {
            int bitValue = Integer.valueOf(String.valueOf(binaryString.charAt(index)));
            bitCount[bitValue]++;
        }
        return bitCount;
    }

    /**
     * Builds the gamma and epsilon binary values using the algorithm provided by the problem
     * statement. Returns an array containing the binary values as strings (gamma value at '
     * index 0, epsilon value at index 1)
     * @param binaryStrings A collection containing the strings used to build the values
     * @return An array containing the binary values
     */
    private static String[] buildGammaAndEpsilonBinaries(List<String> binaryStrings) {
        String epsilonString = "";
        String gammaString = "";
        final int numBits = binaryStrings.get(0).length();
        for (int i = 0; i < numBits; i++) {
            int[] bitCount = getBitCountForIndex(i, binaryStrings);
            if (bitCount[0] < bitCount[1]) {
                gammaString += "1";
                epsilonString += "0";
            } else {
                gammaString += "0";
                epsilonString += "1";
            }
        }
        return new String[] {gammaString, epsilonString};
    }

    /**
     * Calculates the power consumption from the given binary values and returns it
     * @param binaryStrings A collection containing the strings to evaluate
     * @return The power consumption value
     */
    public static int calculatePowerConsumption(List<String> binaryStrings) {
        String[] gammaEpsilonStrings = buildGammaAndEpsilonBinaries(binaryStrings);
        return Integer.parseInt(gammaEpsilonStrings[0], 2) * Integer.parseInt(gammaEpsilonStrings[1], 2);
    }

    /**
     * Calculates the life support rating from the given binary values and returns it
     * @param binaryStrings A collection containing the strings to evaluate
     * @return The life support rating
     */
    public static int calculateLifeSupportRating(List<String> binaryStrings) {
        String oxygenString = getOxygenGeneratorBinary(0, binaryStrings);
        String co2String = getCO2ScrubberBinary(0, binaryStrings);
        return Integer.parseInt(oxygenString, 2) * Integer.parseInt(co2String, 2);
    }

    /**
     * Recursive method which determines the most common bit at the given index 
     * for the given collection of binary values and removes all binary values without
     * that bit until only one binary value remains, at which time the final value is returned.
     * @param index The index of the bit to evaluate
     * @param binaryStrings A collection containing the strings to evaluate
     * @return The oxygen generator binary string
     */
    private static String getOxygenGeneratorBinary(int index, List<String> binaryStrings) {
        if (binaryStrings.isEmpty()) {
            throw new IllegalArgumentException("List of binary strings cannot be empty");
        }
        if (binaryStrings.size() == 1 || index >= binaryStrings.get(0).length()) {
            return binaryStrings.get(0);
        }
        int[] bitCount = getBitCountForIndex(index, binaryStrings);
        final String searchBit = bitCount[0] <= bitCount[1] ? "1" : "0";
        List<String> filteredStrings = List.copyOf(binaryStrings).stream().filter((binaryString) -> String.valueOf(binaryString.charAt(index)).equals(searchBit)).collect(Collectors.toList());
        return getOxygenGeneratorBinary(index + 1, filteredStrings);
    }

    /**
     * Recursive method which determines the least common bit at the given index 
     * for the given collection of binary values and removes all binary values without
     * that bit until only one binary value remains, at which time the final value is returned.
     * @param index The index of the bit to evaluate
     * @param binaryStrings A collection containing the strings to evaluate
     * @return The CO2 scrubber binary string
     */
    private static String getCO2ScrubberBinary(int index, List<String> binaryStrings) {
        if (binaryStrings.isEmpty()) {
            throw new IllegalArgumentException("List of binary strings cannot be empty");
        }
        if (binaryStrings.size() == 1 || index >= binaryStrings.get(0).length()) {
            return binaryStrings.get(0);
        }
        int[] bitCount = getBitCountForIndex(index, binaryStrings);
        final String searchBit = bitCount[0] <= bitCount[1] ? "0" : "1";
        List<String> filteredStrings = List.copyOf(binaryStrings).stream().filter((binaryString) -> String.valueOf(binaryString.charAt(index)).equals(searchBit)).collect(Collectors.toList());
        return getCO2ScrubberBinary(index + 1, filteredStrings);
    }
    
}
