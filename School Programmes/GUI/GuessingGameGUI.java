import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.*;
import java.util.Random;

public class GuessingGameGUI{
    public static void main(String[] args){
        JFrame window = new JFrame("Guessing game");
        window.setSize(500,500);
        window.setLayout(new GridLayout(3, 1));

        JPanel panel4all = new JPanel(new FlowLayout());
        JTextField returnbox = new JTextField(1);
        JTextField returnbox = new JTextField(1);
        
        panel4all.add(returnbox);
        

    }
    public static int randomnumber (int numberrand){
        Random rand = new Random();
        numberrand = rand.nextInt(99)+1;
        return numberrand;
    }


}
