import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessingGameGUI {
    public static void main(String[] args) {
        JFrame window = new JFrame("Guessing Game");
        window.setSize(300, 100);
        window.setLayout(new FlowLayout());
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel promptLabel = new JLabel("Enter your guess (1-100):");
        JTextField inputBox = new JTextField(10);
        JButton guessButton = new JButton("Guess");
        JLabel resultLabel = new JLabel("");

        window.add(promptLabel);
        window.add(inputBox);
        window.add(guessButton);
        window.add(resultLabel);

        int randomnum = randomnumber();

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int guess = Integer.parseInt(inputBox.getText());
                    String result = check(guess, randomnum);
                    resultLabel.setText(result);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Please enter a valid number!");
                }
            }
        });

        window.setVisible(true);
    }

    public static int randomnumber() {
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }

    public static String check(int guessedNum, int numberToGuess) {
        if (guessedNum == numberToGuess) {
            return "Correct! You guessed the number.";
        } else if (guessedNum < numberToGuess) {
            return "Too low! Try again.";
        } else {
            return "Too high! Try again.";
        }
    }
}
