import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Scanner;
import java.util.Random;

public class MathStudyCase{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        boolean correct = false;
        for (int i = 0; i < 5; i++) {
            correct = false;
            int int1 = (int) Math.random();
            int int2 = (int) Math.random();
            int pick = rand.nextInt(4)+1;
            int result = 0;
            char operat = ' ';
            switch (pick) {
                case 1:
                    result = int1 + int2;
                    operat = '+';
                    break;
                case 2:
                    result = int1 - int2;
                    operat = '-';
                    break;
                case 3:
                    result = int1 / int2;
                    operat = '/';
                    break;
                case 4:
                    result = int1 * int2;
                    operat = '*';
                    break;
            }
            System.out.print(pick);
            System.out.println(int1 + operat + int2 + " = ?");
            int guess = scanner.nextInt();
            FileWrite(int1 + operat + int2 + " = ?\n " + guess + "\n");
            if(result == guess){
                correct = true;
            }
            FileWrite(String.valueOf(correct) + "\n");
        }

    }
    public static void FileWrite(String input) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/home/sgochkov26/Desktop/sample.txt"));
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
