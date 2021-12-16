package day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TransparentOrigami {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("day13/markedSpaces.txt"));
        List<MarkedSpace> initialMarkedSpaces = new ArrayList<>();
        List<Intercept> intercepts = new ArrayList<>();

        while (s.hasNextLine()) {
            String line = s.nextLine();
            if (!line.isBlank()){
                String[] spaceCoors = line.split(",");
                int x = Integer.valueOf(spaceCoors[0]);
                int y = Integer.valueOf(spaceCoors[1]);
                initialMarkedSpaces.add(new MarkedSpace(x, y));
            } else {
                break;
            }
        }
        while (s.hasNextLine()) {
            String line = s.nextLine();
            int equalsCharIndex = line.indexOf("=");
            InterceptAxis axis = line.charAt(equalsCharIndex - 1) == 'x' 
                ? InterceptAxis.X_AXIS : InterceptAxis.Y_AXIS;
            int interceptVal = Integer.valueOf(line.substring(equalsCharIndex + 1));
                intercepts.add(new Intercept(interceptVal, axis));
        }
        s.close();
        s = null;

        Set<MarkedSpace> foldedMarkedSpaces = new HashSet<>();

        for (Intercept intercept : intercepts) {
            for (MarkedSpace space : initialMarkedSpaces) {
                int initialCoor = intercept.getIntersectAxis() == InterceptAxis.X_AXIS ? space.getXCoor() : space.getYCoor();
                int finalCoor = initialCoor;
                if (initialCoor > intercept.getIntercept()) {
                    finalCoor = 2 * intercept.getIntercept() - initialCoor;
                }
                if (intercept.getIntersectAxis() == InterceptAxis.X_AXIS) {
                    foldedMarkedSpaces.add(new MarkedSpace(finalCoor, space.getYCoor()));
                } else {
                    foldedMarkedSpaces.add(new MarkedSpace(space.getXCoor(), finalCoor));
                }
            }
            initialMarkedSpaces = new ArrayList<>(foldedMarkedSpaces);
            foldedMarkedSpaces = new HashSet<>();
        }

        int maxRowIndex = 0;
        int maxColIndex = 0;

        for (MarkedSpace space : initialMarkedSpaces) {
            if (space.getXCoor() > maxColIndex) {
                maxColIndex = space.getXCoor();
            }
            if (space.getYCoor() > maxRowIndex) {
                maxRowIndex = space.getYCoor();
            }
        }

        int[][] spaceGrid = new int[maxRowIndex + 1][maxColIndex + 1];
        for (MarkedSpace space : initialMarkedSpaces) {
            spaceGrid[space.getYCoor()][space.getXCoor()]++;
        }

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < spaceGrid.length; i++) {
            for (int j = 0; j < spaceGrid[i].length; j++) {
                if (spaceGrid[i][j] > 0) {
                    builder.append("#");
                } else {
                    builder.append(".");
                }
                if (j != spaceGrid[i].length - 1) {
                    builder.append(" ");
                }
            }
            builder.append(System.lineSeparator());
        }

        System.out.println(builder.toString());
    }

}
