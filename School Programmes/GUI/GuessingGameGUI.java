import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;
import java.util.concurrent.Flow;

public class GuessingGameGUI{
    public static void main(String[] args){

        int numberrand = randomnumber();
        final int[] attempts = {10};

        JFrame window = new JFrame("Guessing game");
        window.setSize(500,500);
        window.setLayout(new GridLayout(4, 1));

        JPanel paneloutput = new JPanel();
        paneloutput.setLayout(null);
        JLabel returnbox = new JLabel();
        //JLabel.se
        paneloutput.add(returnbox, BorderLayout.SOUTH);

        JPanel panelcheck = new JPanel(new FlowLayout());
        JButton checkerinit = new JButton("Guess");
        panelcheck.add(checkerinit);

        JPanel panelinput = new JPanel(new FlowLayout());
        JTextField inputbox = new JTextField(10);
        JLabel attemptsshow = new JLabel("You have " + " attemps left");
        panelinput.add(inputbox);
        panelinput.add(attemptsshow);

        window.add(paneloutput);
        window.add(panelcheck);
        window.add(panelinput);

        checkerinit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                int guessednum = Integer.parseInt(inputbox.getText());
                if(attempts[0] > 0) {
                    try {
                        if(guessednum==numberrand) {
                            returnbox.setText("Correct! You Guessed The Number!");
                        } else if (guessednum<numberrand){
                            returnbox.setText("Too low. Try again!");
                            attempts[0]--;
                            attemptsshow.setText("You have " + attempts[0] + " attemps left");
                        } else {
                            returnbox.setText("Too high. Try again!");
                            attempts[0]--;
                            attemptsshow.setText("You have " + attempts[0] +" attemps left");
                        }
                    } catch (NumberFormatException ee) {
                        returnbox.setText("Please enter a valid number.");
                    }
                }

            }
        });

        window.setVisible(true);

    }
    public static int randomnumber (){
        Random rand = new Random();
        int numberrand = rand.nextInt(99)+1;
        return numberrand;
    }
}
