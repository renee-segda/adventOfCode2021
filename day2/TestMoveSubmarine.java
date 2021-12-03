package day2;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestMoveSubmarine {
    
    public static void main(String[] args) throws FileNotFoundException {

        Submarine sub = new Submarine();
        Scanner s = new Scanner(new File("day2/subMovements.txt"));

        while (s.hasNextLine()) {
            String[] moveVals = s.nextLine().split(" ");
            int amountToMove = Integer.valueOf(moveVals[1]);
            switch (moveVals[0]) {
                case "forward":
                    sub.moveForward(amountToMove);
                    break;
                case "down":
                    sub.moveDown(amountToMove);
                    break;
                case "up":
                    sub.moveUp(amountToMove);
                    break;
                default:
                    break;
            }
        }
        s.close();
        
        System.out.println(sub.getHorizontalCoor() * sub.getDepthCoor());
    }
}
