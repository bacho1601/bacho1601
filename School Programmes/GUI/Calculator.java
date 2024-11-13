import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args){
        //creation of frame (remember! frame -> panel -> boxes, buttons & et.al.)
        JFrame window = new JFrame("calculator");
        window.setSize(500, 500);
        window.setLayout(new GridLayout(3, 1));

        //creation of panel for textboxes
        JPanel textboxPanel = new JPanel(new FlowLayout());
        JTextField textBox1 = new JTextField(10);
        JTextField textBox2 = new JTextField(10);
        textboxPanel.add(textBox1);
        textboxPanel.add(textBox2);

        window.add(textboxPanel);

        //panel for buttons and creation of buttons
        JPanel buttonsPanel = new JPanel(new FlowLayout());
        JButton addition = new JButton("+");
        JButton subtr = new JButton("-");
        JButton multiplic = new JButton("*");
        JButton div = new JButton("/");

        setStyle(addition);
        setStyle(subtr);
        setStyle(multiplic);
        setStyle(div);

        buttonsPanel.add(addition);
        buttonsPanel.add(subtr);
        buttonsPanel.add(multiplic);
        buttonsPanel.add(div);

        window.add(buttonsPanel);

        //panel for result
        JPanel resultPanel = new JPanel(new FlowLayout());
        JLabel result = new JLabel("result");

        resultPanel.add(result);
        window.add(resultPanel);

        window.setVisible(true);

        addition.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                // impolement addition
                calculate('+', textBox1, textBox2, result);
                calculate('-', textBox1, textBox2, result);
                calculate('/', textBox1, textBox2, result);
                calculate('*', textBox1, textBox2, result);
            }
        });
    }

    public static void setStyle(JButton button){
        button.setFont(new Font("Arial", Font.BOLD, 45));
        button.setForeground(Color.BLUE);
    }

    public static void calculate (char operator, JTextField textBox1, JTextField textBox2, JLabel result){
        double num1 = Double.parseDouble(textBox1.getText()); //converted text into double
        double num2 = Double.parseDouble(textBox2.getText());

        switch (operator) {
            case '+':
                result.setText(String.valueOf(num1+num2));
            case '-':
                result.setText(String.valueOf(num1-num2));
            case '/':
                result.setText(String.valueOf(num1/num2));
            case '*':
                result.setText(String.valueOf(num1*num2));
        }
        result.setText(String.valueOf(num1+num2));
    }
}
