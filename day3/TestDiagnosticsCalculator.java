package day3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TestDiagnosticsCalculator {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("day3/diagnosticReport.txt"));
        List<String> binaryVals = new ArrayList<>();
        while (s.hasNextLine()) {
            binaryVals.add(s.nextLine().strip());
        }
        s.close();
        
        System.out.println(DiagnosticsCalculator.calculatePowerConsumption(binaryVals));
        System.out.println(DiagnosticsCalculator.calculateLifeSupportRating(binaryVals));
    }

}
