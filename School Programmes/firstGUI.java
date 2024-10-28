import javax.swing.*;
import java.awt.*;

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
        JLabel inputLabel = new JLabel();
        JLabel outputLabel = new JLabel();

        // place and size for components
        // setBounds(x position, y position, width, height)
        inputLabel.setBounds(300,100,400,50);
        inputLabel.setFont(new Font("Impact", Font.BOLD,32));
        inputLabel.setForeground(Color.MAGENTA);

        outputLabel.setBounds(100,100,400,50);
        outputLabel.setFont(new Font("Impact", Font.ITALIC, 32));
        outputLabel.setForeground(Color.BLUE);

        divideFuncButton.setBounds(350, 300, 100, 50);
        multiplyFuncButton.setBounds(150,300,100,50);
        addFuncButton.setBounds(250, 300, 100, 50);
        subtractFuncButton.setBounds(50, 300, 100, 50);

        // add components to JFrame f
        frame.add(multiplyFuncButton);
        frame.add(divideFuncButton);
        frame.add(addFuncButton);
        frame.add(subtractFuncButton);
        frame.add(inputLabel);
        frame.add(outputLabel);

        // make the frame visible
        frame.setVisible(true);
        outputLabel.setVisible(true);
        inputLabel.setVisible(true);
    }
}
