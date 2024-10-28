import javax.swing.*;
import java.awt.*;
import java.lang.*;

import static java.lang.String.valueOf;

public class firstGUI{
    public static void main(String[] args) {
        // A JFrame is a window where we can design our UI
        JFrame frame = new JFrame("My app");
        frame.setSize(500, 500);
        frame.setLayout(null);

        // create a Button and a Label
        JButton divideFuncButton = new JButton("/");
        JButton multiplyFuncButton = new JButton("*");
        JButton addFuncButton = new JButton("+");
        JButton subtractFuncButton = new JButton("-");
        JButton resetButton = new JButton("Reset");
        JButton equalsButton = new JButton("=");
        JTextField displayNum1 = new JTextField();
        JTextField displayNum2 = new JTextField();
        JTextField displayFunc = new JTextField();
        JTextField displayResult = new JTextField();

        // place and size for components
        // setBounds(x position, y position, width, height)
        displayNum1.setBounds(50,100,100,50);
        displayNum1.setFont(new Font("Impact", Font.BOLD,20));
        displayNum1.setForeground(Color.MAGENTA);

        displayNum2.setBounds(180,100,100,50);
        displayNum2.setFont(new Font("Impact", Font.ITALIC, 20));
        displayNum2.setForeground(Color.BLUE);

        displayFunc.setBounds(150, 100, 30, 50);
        displayFunc.setFont(new Font("Roboto", Font.BOLD, 25));
        displayFunc.setForeground(Color.CYAN);

        displayResult.setBounds(170, 200, 300, 50);
        displayResult.setFont(new Font("Roboto", Font.BOLD, 25));
        displayResult.setForeground(Color.BLACK);

        divideFuncButton.setBounds(350, 300, 100, 50);
        multiplyFuncButton.setBounds(150,300,100,50);
        addFuncButton.setBounds(250, 300, 100, 50);
        subtractFuncButton.setBounds(50, 300, 100, 50);
        equalsButton.setBounds(350, 100, 100, 50);
        resetButton.setBounds(100, 100, 100, 50);

        // add components to JFrame f
        frame.add(multiplyFuncButton);
        frame.add(divideFuncButton);
        frame.add(addFuncButton);
        frame.add(subtractFuncButton);
        frame.add(displayNum1);
        frame.add(displayNum2);
        frame.add(displayFunc);
        frame.add(displayResult);

        // make the frame visible
        frame.setVisible(true);
        displayNum2.setVisible(true);
        displayNum1.setVisible(true);
    }
}
