package day10;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class InvalidLineFinder {
    
    private static final Map<Character, Integer> illegalCharScoreMap = new HashMap<>() {{
        put(')', 3);
        put(']', 57);
        put('}', 1197);
        put('>', 25137);
    }};

    private static final Map<Character, Integer> closingSequenceCharScoreMap = new HashMap<>() {{
        put(')', 1);
        put(']', 2);
        put('}', 3);
        put('>', 4);
    }};

    private static final Map<Character, Character> closeOpenCharMap = new HashMap<>() {{
        put(')', '(');
        put(']', '[');
        put('}', '{');
        put('>', '<');
    }};

    private static final Map<Character, Character> openCloseCharMap = new HashMap<>() {{
        put('(', ')');
        put('[', ']');
        put('{', '}');
        put('<', '>');
    }};

    private Stack<Character> chunkCharStack;

    public InvalidLineFinder() {
        initializeStack();
    }

    public Character getFirstIllegalChar(String chunkLine) {
        char[] chunkString = chunkLine.replace(" ", "").toCharArray();
        for (Character c : chunkString) {
            if (closeOpenCharMap.keySet().contains(c)) {
                if (chunkCharStack.peek().equals(closeOpenCharMap.get(c))) {
                    chunkCharStack.pop();
                    continue;
                } else {
                    initializeStack();
                    return c;
                }
            } else {
                chunkCharStack.push(c);
            }
        }
        initializeStack();
        return null;
    }

    public String getClosingSequence(String chunkLine) {
        char[] chunkString = chunkLine.replace(" ", "").toCharArray();
        for (Character c : chunkString) {
            if (closeOpenCharMap.keySet().contains(c)) {
                chunkCharStack.pop();
            } else {
                chunkCharStack.push(c);
            }
        }
        String closingSequence = "";
        while (!chunkCharStack.isEmpty()) {
            Character c = chunkCharStack.pop();
            closingSequence += openCloseCharMap.get(c);
        }
        initializeStack();
        return closingSequence;
    }

    public int getIllegalCharScore(Character illegalChar) {
        return illegalCharScoreMap.get(illegalChar);
    }

    public long getClosingSequenceScore(String closingSequence) {
        long score = 0;
        for (Character c : closingSequence.toCharArray()) {
            score = (score * 5) + closingSequenceCharScoreMap.get(c);
        }
        return score;
    }

    private void initializeStack() {
        this.chunkCharStack = new Stack<>();
    }

}
