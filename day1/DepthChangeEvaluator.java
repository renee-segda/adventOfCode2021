package day1;

import java.util.List;

/**
 * Evaluates the change in depth given a list of integer depths
 */
public class DepthChangeEvaluator {

    private static final int WINDOW_SIZE = 3;

    /**
     * Returns a count of the number of times that the measured depth values 
     * increase (i.e. when measurement 1 is greater than measurement 2).
     * @param depthMeasurements List of integer depth measurements
     * @return The number of times the depth increases
     */
    public static int numberOfDepthIncreases(List<Integer> depthMeasurements) {
        int numIncreases = 0;
        for (int i = 1; i < depthMeasurements.size(); i++) {
            if (depthMeasurements.get(i) - depthMeasurements.get(i -1) > 0) {
                numIncreases++;
            }
        }
        return numIncreases;
    }

    /**
     * Returns a count of the number of times that the sum of WINDOW_SIZE 
     * consecutive depth values increases between measurements (i.e. when 
     * the sum of measurements 1, 2, and 3 is greater than the sum of 
     * measurements 2, 3, and 4)
     * @param depthMeasurements List of integer depth measurements
     * @return The number of times the sum increases
     */
    public static int numberOfWindowSumIncreases(List<Integer> depthMeasurements) {
        int numIncreases = 0;
        if (depthMeasurements.size() > WINDOW_SIZE) {
            for (int i = 0; i < depthMeasurements.size() - WINDOW_SIZE; i++) {
                if (calculateWindow(depthMeasurements, i) < calculateWindow(depthMeasurements, i + 1)) {
                    numIncreases++;
                }
            }
        }
        return numIncreases;
    }

    /**
     * Returns the sum of the WINDOW_SIZE consecutive depth values starting 
     * at the given startIndex
     * @param depthMeasurements List of integer depth measurements
     * @param startIndex The index of the value which will begin the count
     * @return The sum of the values between (and including) indices startIndex and
     *          startIndex + WINDOW_SIZE - 1
     */
    private static int calculateWindow(List<Integer> depthMeasurements, int startIndex) {
        return depthMeasurements.get(startIndex) + depthMeasurements.get(startIndex + 1) + 
            depthMeasurements.get(startIndex + 2);
    }

}