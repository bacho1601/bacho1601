import java.util.Scanner;
import java.util.Random;
public class BetterRandomGame {



    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        int RandomNumber = rand.nextInt(99)+1;
        int guess = 0;

        System.out.println("Welcome to Better Random Game!");
        for(int guesses = 3; guesses > 0; guesses--) {
            System.out.println("You have "+ guesses +" guesses left");
            System.out.print("Input your guess:");

            for (int j = 1; j > 0; j++) {
                if(scanner.hasNextInt()) {
                    guess = scanner.nextInt();
                    System.out.println("You entered the following number: " + guess);
                } else {
                    String consumator = scanner.next();
                    System.out.println("Error: " + consumator + " is not a number.");
                }
            }

        }






    }
}

