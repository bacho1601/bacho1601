import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        byte gameNum = 5;
        int guessAmount = 3;
        byte guess = -1;

        game game = new game(gameNum, guess, guessAmount);

        System.out.println("Welcome to the game! You have 3 guesses to uncover the number I've picked from 1 to 10.");

        game.game();
    }
}

class game {

    byte gameNum = 5;
    byte guess;
    int guessAmount;

    public game(byte gameNum, byte guess, int guessAmount) {
        this.gameNum = gameNum;
        this.guess = guess;
        this.guessAmount = guessAmount;
    }

    void game() {
        Scanner scan = new Scanner(System.in);
        if (guessAmount > 0){
            System.out.println("What's your guess?: ");
            this.guess = scan.nextByte();
            guessAmount--;
          
            if (gameNum == guess) {
                System.out.print("Correct guess, congrats!~");
                return;
            } else {
                System.out.println("Wrong! You have " + guessAmount + " guesses left!");
                game();
            }
        } else {
            System.out.println("You're out of guesses! Better luck next time...");
            return;
        }
    }
}
