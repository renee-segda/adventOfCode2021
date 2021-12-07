package day6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Simulation {
    
    private static List<Long> fishTimeCount;
    private static final int HIGHEST_TIME_BEFORE_FIRST_CYCLE = 8;
    private static final int HIGHEST_TIME_AFTER_FIRST_CYCLE = 6;
    private static final int LOWEST_BIRTH_TIME = 0;
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("day6/lanternfishInput.txt"));
        String[] initialFishTimes = s.nextLine().split(",");
        s.close();

        fishTimeCount = new ArrayList<Long>(Collections.nCopies(
            HIGHEST_TIME_BEFORE_FIRST_CYCLE + 1, Long.valueOf(0)));
        for (String time : initialFishTimes) {
            int timeAsInt = Integer.valueOf(time);
            fishTimeCount.set(timeAsInt, Long.valueOf(fishTimeCount.get(timeAsInt) + 1));
        }

        int numIterations = 256;
        for (int i = 1; i <= numIterations; i++) {
            progressOneDay();
        }

        long fishSum = 0;
        for (int i = LOWEST_BIRTH_TIME; i <= HIGHEST_TIME_BEFORE_FIRST_CYCLE; i++) {
            fishSum += fishTimeCount.get(i);
        }
        System.out.println(fishSum);
    }

    /**
     * Decrements the birth time for existing fish, moving existing fish with a birth time
     * of 0 to the index for fish with birth time of HIGHEST_TIME_AFTER_FIRST_CYCLE. It also adds
     * newly born fish to the index for fish with birth time of HIGHEST_TIE_BEFORE_FIRST_CYCLE
     */
    private static void progressOneDay() {
        long numFishAtZero = fishTimeCount.remove(LOWEST_BIRTH_TIME);
        fishTimeCount.add(Long.valueOf(0));
        fishTimeCount.set(6, fishTimeCount.get(HIGHEST_TIME_AFTER_FIRST_CYCLE) + numFishAtZero);
        fishTimeCount.set(8, fishTimeCount.get(HIGHEST_TIME_BEFORE_FIRST_CYCLE) + numFishAtZero);
    }
}
