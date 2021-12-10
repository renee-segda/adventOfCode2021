import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class NumericOutputEvaluator {

    /**
     * Map of signal length to a set containing all numbers whose signal is of that length
     */
    private static final Map<Integer, Set<Integer>> signalLengthMap = new HashMap<>() {{
        put(2, new HashSet<>() {{
            add(1);
        }});
        put(3, new HashSet<>() {{
            add(7);
        }});
        put(4, new HashSet<>() {{
            add(4);
        }});
        put(5, new HashSet<>() {{
            add(2);
            add(3);
            add(5);
        }});
        put(6, new HashSet<>() {{
            add(0);
            add(6);
            add(9);
        }});
        put(7, new HashSet<>() {{
            add(8);
        }});
    }};

    /**
     * Returns a set of all integers that could be represented by the given signal
     * 
     * @param signal The signal whose possible integer values will be identified
     * @return A set containing all integers that could be represented by the signal
     */
    public static Set<Integer> getPossibleNumberValues(String signal) {
        int wireLength = signal.length();
        return signalLengthMap.get(wireLength);
    }

    /**
     * Constructs and returns a map associating integer signal lengths with lists of all signals in
     * the given signal line which are of that length. The characters of the signal strings are sorted
     * in ascending alphabetical order.
     * 
     * @param signalLine String containing all ten output signals
     * @return Map where the keys are signal lengths and the values are lists of signal strings
     */
    private static Map<Integer, List<String>> mapSignalLengthsToSignalLists(String signalLine) {
        Map<Integer, List<String>> signalLengthMap = new HashMap<>();
        String[] signalArray = signalLine.split(" ");
        for (String signal : signalArray) {
            char[] signalChars = signal.toCharArray();
            Arrays.sort(signalChars);
            if (!signalLengthMap.keySet().contains(signal.length())) {
                signalLengthMap.put(signal.length(), new ArrayList<>());
            }
            signalLengthMap.get(signal.length()).add(new String(signalChars));
        }
        return signalLengthMap;
    }

    /**
     * Constructs and returns a map associating signal strings from the given signalLine with 
     * the integers they represent
     * 
     * @param signalLine String containing all ten output signals
     * @return Map where the keys are signals and the values are the integers they represent
     */
    public static Map<String, Integer> buildSignalIntegerMap(String signalLine) {
        Map<Integer, List<String>> signalLengthMap = mapSignalLengthsToSignalLists(signalLine);
        Map<String, Integer> identifiedSignalsMap = new HashMap<>();
        String[] identifiedSignalsArray = new String[10];

        // Identifying signals that are recognizable by length
        // (only one number fits signals of those lengths)
        identifiedSignalsArray[1] = signalLengthMap.get(2).get(0);
        identifiedSignalsMap.put(identifiedSignalsArray[1], 1);
        identifiedSignalsArray[4] = signalLengthMap.get(4).get(0);
        identifiedSignalsMap.put(identifiedSignalsArray[4], 4);
        identifiedSignalsArray[7] = signalLengthMap.get(3).get(0);
        identifiedSignalsMap.put(identifiedSignalsArray[7], 7);
        identifiedSignalsArray[8] = signalLengthMap.get(7).get(0);
        identifiedSignalsMap.put(identifiedSignalsArray[8], 8);

        for (String signal : signalLengthMap.get(5)) {
            if (signalContainsAllChars(signal, identifiedSignalsArray[7].toCharArray())) {
                identifiedSignalsArray[3] = signal;
                identifiedSignalsMap.put(signal, 3);
                break;
            }
        }
        for (String signal : signalLengthMap.get(6)) {
            if (signalContainsAllChars(signal, identifiedSignalsArray[3].toCharArray())) {
                identifiedSignalsArray[9] = signal;
                identifiedSignalsMap.put(signal, 9);
            } else if (!signalContainsAllChars(signal, identifiedSignalsArray[1].toCharArray())) {
                identifiedSignalsArray[6] = signal;
                identifiedSignalsMap.put(signal, 6);
            } else {
                identifiedSignalsArray[0] = signal;
                identifiedSignalsMap.put(signal, 0);
            }
        }

        // Identify the char for each segment of the seven-segment display
        String topHoriz = signalDifference(identifiedSignalsArray[7], identifiedSignalsArray[1]);
        String topLeft = signalDifference(identifiedSignalsArray[9], identifiedSignalsArray[3]);
        String bottomLeft = signalDifference(identifiedSignalsArray[8], identifiedSignalsArray[9]);
        String topRight = signalDifference(identifiedSignalsArray[8], identifiedSignalsArray[6]);
        String bottomRight = signalDifference(identifiedSignalsArray[1], topRight);
        String middleHoriz = signalDifference(identifiedSignalsArray[8], identifiedSignalsArray[0]);
        String bottomHoriz = signalDifference(identifiedSignalsArray[3], middleHoriz + topHoriz + identifiedSignalsArray[1]);

        // Build remaining number signals (2 & 5)
        char[] charArray = (topHoriz + topRight + middleHoriz + bottomLeft + bottomHoriz).toCharArray();
        Arrays.sort(charArray);
        identifiedSignalsMap.put(new String(charArray), 2);

        charArray = (topHoriz + topLeft + middleHoriz + bottomRight + bottomHoriz).toCharArray();
        Arrays.sort(charArray);
        identifiedSignalsMap.put(new String(charArray), 5);

        return identifiedSignalsMap;

    }

    /**
     * Checks that the given signal contains all characters in the given charArray
     * @param signal The signal whose contents will be examined
     * @param charArray An array of characters to look for in the signal
     * @return True if the signal contains all characters in charArray, false otherwise
     */
    private static boolean signalContainsAllChars(String signal, char[] charArray) {
        for (char c : charArray) {
            if (!signal.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Returns a String containing the characters that are in the larger of the two
     * signals (s1 or s2) and not the smaller of the two signals
     * @param s1 First signal in difference operation
     * @param s2 Second signal in difference operation
     * @return Difference between the larger and smaller of the signals
     */
    private static String signalDifference(String s1, String s2) {
        Set<Character> s1Set = new HashSet<>();
        Set<Character> s2Set = new HashSet<>();
        for (Character c : s1.toCharArray()) {
            s1Set.add(c);
        }
        for (Character c : s2.toCharArray()) {
            s2Set.add(c);
        }
        Set<Character> editedSet;
        if (s1Set.size() > s2Set.size()) {
            s1Set.removeAll(s2Set);
            editedSet = s1Set;
        } else {
            s2Set.removeAll(s1Set);
            editedSet = s2Set;
        }
        char[] remainderArray = new char[editedSet.size()];
        int index = 0;
        for (char c : editedSet) {
            remainderArray[index] = c;
            index++;
        }
        Arrays.sort(remainderArray);
        return new String(remainderArray);


    }


}