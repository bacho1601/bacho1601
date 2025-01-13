import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorProject {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setSize(800, 600);
        window.setLayout(new GridLayout(3,1));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //panel for textboxes and creation of textboxes
        JPanel textboxPanel = new JPanel(new FlowLayout());
        JTextField textBox1  = new JTextField(10);
        JTextField textBox2  = new JTextField(10);
        textboxPanel.add(textBox1);
        textboxPanel.add(textBox2);

        window.add(textboxPanel);

        //panel for buttons and creation of buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addition = new JButton("+");
        JButton subtraction = new JButton("-");
        JButton multiplication = new JButton("*");
        JButton division = new JButton("/");
        buttonPanel.add(addition);
        buttonPanel.add(subtraction);
        buttonPanel.add(multiplication);
        buttonPanel.add(division);

        window.add(buttonPanel);


        //panel for result
        JPanel resultPanel = new JPanel();
        JLabel result = new JLabel("Result");

        resultPanel.add(result);
        window.add(resultPanel);

        window.setVisible(true);

        addition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate('+', textBox1, textBox2, result)
            }
        });
        subtraction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate('+', textBox1, textBox2, result)
            }
        });
        multiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate('+', textBox1, textBox2, result)
            }
        });
        division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculate('+', textBox1, textBox2, result)
            }
        });

        public static void setStyle(JButton button){
            button.setFont(new Font("Arial", Font.BOLD, 45));
            button.setForeground(Color.BLUE);
        }

    }
}
