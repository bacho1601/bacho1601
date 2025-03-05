import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChangingWindowsExample {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ChangingWindowsExample().createGUI());
    }

    public void createGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Changing Windows Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        // Create a CardLayout container (main panel)
        JPanel cardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Create different panels that will be shown in the CardLayout
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("This is Panel 1"));
        JButton nextButton1 = new JButton("Go to Panel 2");
        panel1.add(nextButton1);

        JPanel panel2 = new JPanel();
        panel2.add(new JLabel("This is Panel 2"));
        JButton nextButton2 = new JButton("Go to Panel 1");
        panel2.add(nextButton2);

        // Add panels to the cardPanel (CardLayout container)
        cardPanel.add(panel1, "Panel 1");
        cardPanel.add(panel2, "Panel 2");

        // Add action listeners to buttons to switch between panels
        nextButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Panel 2");
            }
        });

        nextButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Panel 1");
            }
        });

        // Add the cardPanel to the frame
        frame.add(cardPanel);

        // Make the frame visible
        frame.setVisible(true);
    }
}
