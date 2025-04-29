import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;

public class Moviecollector {
    private static final Movies[] movies = new Movies[100];
    private static int movieCount = 0;
    private static final String[] genres = {"Action", "Comedy", "Horror", "Drama", "Sci-Fi"};

    public static void main(String[] args) {
        JFrame frame = new JFrame("Movie Collection Manager");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel cardPanel = new JPanel(new CardLayout());
        CardLayout cardLayout = (CardLayout) cardPanel.getLayout();

        //home panel
        JPanel homePanel = new JPanel(new FlowLayout());
        JButton toAddPanel = new JButton("Add Movie");
        JButton toTablePanel = new JButton("View Movies");
        homePanel.add(new JLabel("Welcome to Movie Manager!"));
        homePanel.add(toAddPanel);
        homePanel.add(toTablePanel);

        //add movie panel
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel titleLabel = new JLabel("Movie Title:");
        JTextField titleField = new JTextField(20);
        JLabel genreLabel = new JLabel("Genre:");
        JComboBox<String> genreBox = new JComboBox<>(genres);
        genreBox.setPreferredSize(new Dimension(200, 25));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(titleLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(titleField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(genreLabel, gbc);
        gbc.gridx = 1;
        formPanel.add(genreBox, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton addMovieBtn = new JButton("Add Movie");
        JButton backToHome1 = new JButton("Home");
        buttonPanel.add(addMovieBtn);
        buttonPanel.add(backToHome1);

        JLabel countDisplayLabel = new JLabel("🎬 Total Movies: 0", JLabel.CENTER);
        countDisplayLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        addPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        addPanel.add(formPanel);
        addPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        addPanel.add(buttonPanel);
        addPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        addPanel.add(countDisplayLabel);

        //table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        String[] columnNames = {"Title", "Genre"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        JPanel controlPanel = new JPanel();
        JButton backToHome2 = new JButton("Home");
        JButton sortTitleBtn = new JButton("Sort by Title");
        JButton sortGenreBtn = new JButton("Sort by Genre");
        JTextField searchField = new JTextField(10);
        JButton searchBtn = new JButton("Search");
        JLabel countLabel = new JLabel("Total Movies: 0");
        controlPanel.add(sortTitleBtn);
        controlPanel.add(sortGenreBtn);
        controlPanel.add(new JLabel("Search by Title:"));
        controlPanel.add(searchField);
        controlPanel.add(searchBtn);
        controlPanel.add(countLabel);
        controlPanel.add(backToHome2);
        tablePanel.add(controlPanel, BorderLayout.SOUTH);

        //card panel add
        cardPanel.add(homePanel, "Home");
        cardPanel.add(addPanel, "Add");
        cardPanel.add(tablePanel, "Table");

        //frame add
        frame.add(cardPanel);
        frame.setVisible(true);

        //button functions
        toAddPanel.addActionListener(e -> cardLayout.show(cardPanel, "Add"));
        toTablePanel.addActionListener(e -> {
            updateTable(model);
            countLabel.setText("🎬 Total Movies: " + movieCount);
            cardLayout.show(cardPanel, "Table");
        });
        backToHome1.addActionListener(e -> cardLayout.show(cardPanel, "Home"));
        backToHome2.addActionListener(e -> cardLayout.show(cardPanel, "Home"));

        addMovieBtn.addActionListener(e -> {
            String title = titleField.getText().trim();
            String genre = (String) genreBox.getSelectedItem();
            if (!title.isEmpty() && movieCount < movies.length) {
                movies[movieCount++] = new Movies(title, genre);
                titleField.setText("");
                countLabel.setText("🎬 Total Movies: " + movieCount);
                countDisplayLabel.setText("🎬 Total Movies: " + movieCount);
                JOptionPane.showMessageDialog(frame, "Movie Added!");
            }
        });

        sortTitleBtn.addActionListener(e -> {
            Arrays.sort(movies, 0, movieCount, Comparator.comparing(Movies::getTitle));
            updateTable(model);
        });

        sortGenreBtn.addActionListener(e -> {
            Arrays.sort(movies, 0, movieCount, Comparator.comparing(Movies::getGenre));
            updateTable(model);
        });

        searchBtn.addActionListener(e -> {
            String search = searchField.getText().trim().toLowerCase();
            for (int i = 0; i < movieCount; i++) {
                if (movies[i].getTitle().toLowerCase().contains(search)) {
                    table.setRowSelectionInterval(i, i);
                    table.scrollRectToVisible(table.getCellRect(i, 0, true));
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Movie not found.");
        });
    }

    static void updateTable(DefaultTableModel model) {
        model.setRowCount(0);
        for (int i = 0; i < movieCount; i++) {
            model.addRow(new Object[]{movies[i].getTitle(), movies[i].getGenre()});
        }
    }
}

class Movies {
    private final String title;
    private final String genre;

    public Movies(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }
}
