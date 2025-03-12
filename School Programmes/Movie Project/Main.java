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

        JPanel cardPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        JPanel HomePanel = new JPanel();
        HomePanel.setLayout(new FlowLayout());

        JPanel MovieAdd = new JPanel();
        MovieAdd.setLayout(new FlowLayout());
        JButton homeButtMovie = new JButton("Home");



        // Add panels to the cardPanel (CardLayout container)
        cardPanel.add(cardPanel, "Card Panel");
        cardPanel.add(HomePanel, "Home Panel");
        cardPanel.add(MovieAdd, "Movie Add Panel");


        // Add action listeners to buttons to switch between panels
        homeButtMovie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Home Panel");
            }
        });


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
