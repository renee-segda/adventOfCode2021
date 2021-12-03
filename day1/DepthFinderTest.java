package day1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepthFinderTest {
    public static void main(String[] args) throws FileNotFoundException {
        List<Integer> measurements = new ArrayList<Integer>();

        Scanner s = new Scanner(new File("day1/measurements.txt"));

        while (s.hasNextLine()) {
            measurements.add(Integer.valueOf(s.nextLine().strip()));
        }
        s.close();

        System.out.println(DepthChangeEvaluator.numberOfWindowSumIncreases(measurements));
    }
}