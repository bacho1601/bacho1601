import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        Movies[] movies;

        JFrame frame = new JFrame();
        frame.setSize(700, 500);
        frame.setVisible(true);
        frame.setLayout(new BorderLayout());

        //Card Panel creation (the general panel in which everything else is)
        JPanel cardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);
        
        //Home Panel creation
        JPanel HomePanel = new JPanel();
        HomePanel.setLayout(new FlowLayout());
        HomePanel.add(new JLabel("This is The Home Panel"));
        //redirect button creation
        JButton MovieAddHomeButt = new JButton("Go To Movie Add");
        HomePanel.add(MovieAddHomeButt);
        
        //Movie Add Panel creation
        JPanel MovieAddPanel = new JPanel();
        MovieAddPanel.setLayout(new FlowLayout());
        JButton homeButtMovie = new JButton("Go To Home");
        //redirect button creation
        MovieAddPanel.add(new JLabel("This is The Movie Add Panel"));
        MovieAddPanel.add(homeButtMovie);

        // Add panels to the cardPanel (CardLayout container)
        cardPanel.add(HomePanel, "Home Panel");
        cardPanel.add(MovieAddPanel, "Movie Add Panel");


        // Add action listeners to buttons to switch between panels
        homeButtMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Home Panel");
            }
        });
        MovieAddHomeButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Movie Add Panel");
            }
        });

        // Add the cardPanel to the frame
        frame.add(cardPanel);

        // Make the frame visible
        frame.setVisible(true);
    }
}

class Movies{
    String title;
    String genre;
    Movies(String title, String genre){
        this.title = title;
        this.genre = genre;
    }
}
