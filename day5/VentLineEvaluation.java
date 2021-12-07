package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VentLineEvaluation {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("day5/ventInput.txt"));
        List<VentLine> ventLines = new ArrayList<>();
        while (s.hasNextLine()) {
            String line = s.nextLine();
            String[] coordinateStrings = line.split(" -> ");
            String[] startCoors = coordinateStrings[0].split(",");
            Coordinate start = new Coordinate(Integer.valueOf(startCoors[0]), Integer.valueOf(startCoors[1]));
            String[] endCoors = coordinateStrings[1].split(",");
            Coordinate end = new Coordinate(Integer.valueOf(endCoors[0]), Integer.valueOf(endCoors[1]));
            ventLines.add(new VentLine(start, end));
        }
        s.close();
        Map<Coordinate, Integer> coordinateVentCount = new HashMap<>();
        for (VentLine v : ventLines) {
            List<Coordinate> lineCoors = v.getCoordinatesInLine();
            for (Coordinate c : lineCoors) {
                if (!coordinateVentCount.keySet().contains(c)) {
                    coordinateVentCount.put(c, 0);
                }
                coordinateVentCount.put(c, coordinateVentCount.get(c) + 1);
            }
        }
        int ventCounter = 0;
        for (Integer count : coordinateVentCount.values()) {
            if (count >= 2) {
                ventCounter++;
            }
        }
        System.out.println(ventCounter);
    }

}
