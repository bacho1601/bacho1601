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

        JPanel MovieAdd = new JPanel();
        MovieAdd.setLayout(new FlowLayout());

        

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
