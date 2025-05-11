import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Random;

public class MathStudyCase{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        Random rand1 = new Random();
        boolean correct = false;
        for (int i = 0; i < 5; i++) {
            correct = false;
            int int1 = rand.nextInt(100);
            int int2 = rand1.nextInt(100);
            int pick = rand.nextInt(2)+1;
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
            }
            System.out.println(int1 + String.valueOf(operat) + int2 + " = ?");
            int guess = scanner.nextInt();
            //System.out.println(guess);
            //System.out.println(result);
            if(result == guess){
                correct = true;
            }
            FileWrite(int1 + operat + int2 + " = ?\n " + guess + "\n");
            System.out.println(correct);
            FileWrite(String.valueOf(correct) + "\n");
        }

    }
    public static void FileWrite(String input) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("/home/sgochkov26/Desktop/sample.txt", true));
            writer.write(input);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
