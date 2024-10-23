import java.util.Scanner;
public class QuizGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = "";

        System.out.println("Welcome to the Quiz! Welcome to the quiz! Choose a category:\n(1) Science\n(2) History\n(3) Sports");
        //I researched, on my own, how to handle errors in a controlled manner
        for (int i = 1; i > 0; i++) {
            try {
                System.out.print("- ");
                byte chosenCategory = scanner.nextByte();
                if (chosenCategory > 3 || chosenCategory < 1 ) {
                    System.out.println("Invalid category choice. Try again.");
                    continue;
                }
                Quiz quiz = new Quiz(chosenCategory, input);
                quiz.game(chosenCategory, input);
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid category choice. Try again.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}

class Quiz {
    Scanner scanner = new Scanner(System.in);

    byte chosenCategory;
    String input;

    public Quiz(byte chosenCategory, String input) {
        this.chosenCategory = chosenCategory;
    }

    void game(byte chosenCategory, String input) {
        switch (chosenCategory) {
            case 1:
                Science();
                break;
            case 2:
                History();
                break;
            case 3:
                Sports();
                break;
        }
    }
    void Science()
    {
        System.out.println("What is the name for the anti-matter equivalent of the proton?");
        System.out.print("- ");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("The Antiproton") || input.equalsIgnoreCase("antiproton"))
        {
            System.out.println("Congrats, you won!");
            System.exit(0);
        } else {
            System.out.println("Sorry, you lost!");
        }
    }
    void History()
    {
        System.out.println("When was the final battle of Constantinople held?");
        System.out.print("- ");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("in 1453") || input.equalsIgnoreCase("year 1453")
                || input.equalsIgnoreCase("the year 1453") || input.equalsIgnoreCase("1453"))
        {
            System.out.println("Congrats, you won!");
            System.exit(0);
        } else {
            System.out.println("Sorry, you lost!");
        }
    }
    void Sports()
    {
        System.out.println("What is the name of the fault in football in which the ball is illegally passed to a player," +
                "\nwhen said player is in front of all enemy footballers?");
        System.out.print("- ");
        input = scanner.nextLine();
        if (input.equalsIgnoreCase("an offside") || input.equalsIgnoreCase("offside"))
        {
            System.out.println("Congrats, you won!");
            System.exit(0);
        } else {
            System.out.println("Sorry, you lost!");
        }
    }
}
