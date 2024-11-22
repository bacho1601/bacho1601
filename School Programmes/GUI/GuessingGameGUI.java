import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Random;

public class GuessingGameGUI{
    public static void main(String[] args){
        JFrame window = new JFrame("Guessing game");
        window.setSize(500,500);
        window.setLayout(new GridLayout(3, 1));

        JPanel panel4all = new JPanel(new FlowLayout());
        JLabel returnbox = new JLabel();
        JTextField inputbox = new JTextField(10);
        JButton checkerinit = new JButton("Guess");

        panel4all.add(returnbox);
        panel4all.add(inputbox);
        panel4all.add(checkerinit);

        int numberrand = randomnumber();

        checkerinit.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // impelement addition
                calculate('+', textBox1, textBox2, result);
            }
        });




        window.setVisible(true);

    }
    public static int randomnumber (){
        Random rand = new Random();
        int numberrand = rand.nextInt(99)+1;
        return numberrand;
    }
    public static void check(int guessednum, int numberrand){
        if (guessednum == numberrand) {
            
        }
    }


}
