import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class FindLowPoints {
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("day9/heightMap.txt"));
        List<String> rowList = new ArrayList<>();
        while (s.hasNextLine()) {
            rowList.add(s.nextLine());
        }
        s.close();

        HeightMap heightMap = new HeightMap(rowList);
        System.out.println("Part 1 answer: " + heightMap.calculateRiskLevelSum());

        List<Integer> basinSizes = new ArrayList<>();
        List<HeightMapCoordinate> lowPoints = heightMap.getLowPoints();
        for (HeightMapCoordinate lowPoint : lowPoints) {
            basinSizes.add(heightMap.getBasinSize(lowPoint));
        }
        Collections.sort(basinSizes);
        Collections.reverse(basinSizes);
        System.out.println("Part 2 answer: " + (basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2)));
    }

}
