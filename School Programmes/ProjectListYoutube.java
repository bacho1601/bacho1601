
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URLEncoder;
import java.util.*;

public class MusicPlaylistManager {

    //formats a duration (in minutes as a double) into "m:ss"
    public static String formatDuration(double duration) {
        int minutes = (int) duration;
        int seconds = (int) Math.round((duration - minutes) * 60);
        return String.format("%d:%02d", minutes, seconds);
    }

    //parses a string like "9:25" or "9.25" into a double number of minutes
    static double parseDuration(String input) {
        input = input.trim().replace(',', '.');
        if (input.contains(":")) {
            String[] parts = input.split(":");
            try {
                int minutes = Integer.parseInt(parts[0]);
                int seconds = Integer.parseInt(parts[1]);
                return Math.round((minutes + seconds / 60.0) * 100.0) / 100.0;
            } catch (NumberFormatException e) {
                return 0.0;
            }
        } else {
            try {
                double val = Double.parseDouble(input);
                return Math.floor(val * 100) / 100.0;
            } catch (NumberFormatException e) {
                return 0.0;
            }
        }
    }

    //class for a song with its properties
    static class Song {
        String title, artist, album, genre;
        double duration;
        boolean favorite;

        public Song(String title, String artist, String album, String genre, double duration) {
            this.title = title;
            this.artist = artist;
            this.album = album;
            this.genre = genre;
            this.duration = duration;
        }

        @Override
        public String toString() {
            return title + "," + artist + "," + album + "," + genre + "," + duration + (favorite ? ",true" : "");
        }

        static Song fromString(String line) {
            String[] p = line.split(",");
            Song s = new Song(p[0], p[1], p[2], p[3], Double.parseDouble(p[4]));
            if (p.length > 5 && p[5].equalsIgnoreCase("true")) {
                s.favorite = true;
            }
            return s;
        }
    }

    //manages playlists: CRUD, file I/O, sorting
    static class PlaylistManager {
        Map<String, ArrayList<Song>> playlists = new LinkedHashMap<>();
        String currentPlaylist = "Default";

        public PlaylistManager() {
            playlists.put(currentPlaylist, new ArrayList<>());
            loadFromFile();
        }

        void createPlaylist(String name) {
            if (name != null && !name.trim().isEmpty() && !playlists.containsKey(name)) {
                playlists.put(name, new ArrayList<>());
            }
            currentPlaylist = name;
        }

        void switchPlaylist(String name) {
            if (playlists.containsKey(name)) {
                currentPlaylist = name;
            }
        }

        ArrayList<Song> getSongs() {
            return playlists.get(currentPlaylist);
        }

        void addSong(Song s) {
            playlists.get(currentPlaylist).add(s);
            saveToFile();
        }

        void deleteSong(int index) {
            if (index >= 0 && index < getSongs().size()) {
                getSongs().remove(index);
                saveToFile();
            }
        }

        void updateSong(int index, Song newSong) {
            if (index >= 0 && index < getSongs().size()) {
                getSongs().set(index, newSong);
                saveToFile();
            }
        }

        void sortByTitle() {
            getSongs().sort(Comparator.comparing(song -> song.title.toLowerCase()));
        }

        void saveToFile() {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("songs_export.csv"))) {
                for (Map.Entry<String, ArrayList<Song>> entry : playlists.entrySet()) {
                    bw.write("#" + entry.getKey());
                    bw.newLine();
                    for (Song s : entry.getValue()) {
                        bw.write(s.toString());
                        bw.newLine();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        void loadFromFile() {
            loadFromCustomFile("songs_export.csv");
        }

        void loadFromCustomFile(String path) {
            playlists.clear();
            String current = "Default";
            playlists.put(current, new ArrayList<>());
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.startsWith("#")) {
                        current = line.substring(1);
                        playlists.put(current, new ArrayList<>());
                    } else {
                        playlists.get(current).add(Song.fromString(line));
                    }
                }
            } catch (IOException e) {
                //start fresh when no previous data
            }
        }
    }

    //JLabel to display song count and total duration
    public static JLabel statsLabel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                //adjust some Nimbus colors for a better UI look
                UIManager.put("nimbusBase", new Color(45, 45, 60));
                UIManager.put("nimbusAlertYellow", new Color(248, 187, 0));
                UIManager.put("nimbusDisabledText", new Color(100, 100, 100));
                UIManager.put("nimbusFocus", new Color(115, 164, 209));
                UIManager.put("nimbusGreen", new Color(176, 179, 50));
                UIManager.put("nimbusInfoBlue", new Color(66, 139, 221));
                UIManager.put("nimbusLightBackground", UIManager.getColor("nimbusLightBackground"));
                UIManager.put("nimbusOrange", new Color(191, 98, 4));
                UIManager.put("nimbusRed", new Color(169, 46, 34));
                UIManager.put("nimbusSelectedText", Color.WHITE);
                UIManager.put("nimbusSelectionBackground", new Color(70, 70, 90));
                //default control and text colors
                UIManager.put("control", UIManager.getColor("control"));
                UIManager.put("text", UIManager.getColor("text"));
            } catch (Exception e) {
                e.printStackTrace();
            }

            PlaylistManager manager = new PlaylistManager();

            JFrame frame = new JFrame("🎵 Music Playlist Manager Pro");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1100, 600);

            //JTable and TableModel setup
            DefaultTableModel model = new DefaultTableModel(
                new String[]{"Title", "Artist", "Album", "Genre", "Duration", "Favorite"}, 0
            );
            JTable table = new JTable(model);
            //disabling cell editing
            table.setDefaultEditor(Object.class, null);
            table.setAutoCreateRowSorter(true);
            table.setDragEnabled(true);
            table.setDropMode(DropMode.INSERT_ROWS);
            table.setTransferHandler(new TransferHandler() {
                public boolean canImport(TransferHandler.TransferSupport support) {
                    return support.isDrop();
                }

                public boolean importData(TransferHandler.TransferSupport support) {
                    JTable.DropLocation dl = (JTable.DropLocation) support.getDropLocation();
                    int toRow = dl.getRow();
                    int fromRow = table.getSelectedRow();
                    if (fromRow < 0 || toRow == fromRow) return false;
                    DefaultTableModel m = (DefaultTableModel) table.getModel();
                    Vector<?> rowData = (Vector<?>) m.getDataVector().elementAt(fromRow);
                    m.removeRow(fromRow);
                    m.insertRow(toRow, rowData);
                    return true;
                }
            });

            //renderer for fav colunm
            table.getColumn("Favorite").setPreferredWidth(60);
            DefaultTableCellRenderer favoriteRenderer = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                    if (Boolean.TRUE.equals(value)) {
                        label.setText("✔");
                        label.setForeground(Color.GREEN.darker());
                    } else {
                        label.setText("");
                        label.setForeground(Color.GRAY);
                    }
                    return label;
                }
            };
            table.getColumn("Favorite").setCellRenderer(favoriteRenderer);

            //playlist combo box
            JComboBox<String> playlistCombo = new JComboBox<>(
                manager.playlists.keySet().toArray(new String[0])
            );
            playlistCombo.addActionListener(e -> {
                manager.switchPlaylist((String) playlistCombo.getSelectedItem());
                reloadTable(manager, model);
            });

            //for new playlist button
            JButton newPlaylistBtn = new JButton("➕ New Playlist");
            newPlaylistBtn.addActionListener(e -> {
                String name = JOptionPane.showInputDialog("Enter playlist name:");
                if (name != null && !name.trim().isEmpty() && !manager.playlists.containsKey(name)) {
                    manager.createPlaylist(name);
                    playlistCombo.addItem(name);
                    playlistCombo.setSelectedItem(name);
                }
            });

            //for add song button
            JButton add = new JButton("➕ Add Song");
            add.addActionListener(e -> showSongDialog(null, -1, manager, model));

            //for edit song button
            JButton update = new JButton("✏️ Edit Song");
            update.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    showSongDialog(manager.getSongs().get(row), row, manager, model);
                }
            });

            // for delete song button
            JButton delete = new JButton("🗑️ Delete Song");
            delete.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    int confirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to delete this song?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        manager.deleteSong(row);
                        reloadTable(manager, model);
                    }
                }
            });

            //for sort a-z button
            JButton sort = new JButton("🔤 Sort A-Z");
            sort.addActionListener(e -> {
                manager.sortByTitle();
                reloadTable(manager, model);
            });

            //for sort by favorite button
            JButton favSort = new JButton("⭐ Sort by Favorite");
            favSort.addActionListener(e -> {
                manager.getSongs().sort((a, b) -> Boolean.compare(b.favorite, a.favorite));
                reloadTable(manager, model);
            });

            //for import button
            JButton importBtn = new JButton("📂 Import");
            importBtn.addActionListener(e -> {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    manager.loadFromCustomFile(selectedFile.getAbsolutePath());
                    reloadTable(manager, model);
                }
            });

            //for export button
            JButton exportBtn = new JButton("💾 Export");
            exportBtn.addActionListener(e -> {
                manager.saveToFile();
                JOptionPane.showMessageDialog(frame, "🎉 Export complete! File: songs_export.csv");
            });

            //for search fields and buttons
            JTextField titleField = new JTextField(10);
            JTextField artistField = new JTextField(10);
            JTextField albumField = new JTextField(10);
            JTextField genreField = new JTextField(10);
            JButton searchBtn = new JButton("🔍 Search");
            JButton resetBtn = new JButton("↩ Reset");

            searchBtn.addActionListener(e -> {
                model.setRowCount(0);
                for (Song s : manager.getSongs()) {
                    if (s.title.toLowerCase().contains(titleField.getText().toLowerCase())
                     && s.artist.toLowerCase().contains(artistField.getText().toLowerCase())
                     && s.album.toLowerCase().contains(albumField.getText().toLowerCase())
                     && s.genre.toLowerCase().contains(genreField.getText().toLowerCase())) {
                        model.addRow(new Object[]{
                            s.title, s.artist, s.album, s.genre,
                            formatDuration(s.duration), s.favorite
                        });
                    }
                }
            });

            resetBtn.addActionListener(e -> {
                titleField.setText("");
                artistField.setText("");
                albumField.setText("");
                genreField.setText("");
                reloadTable(manager, model);
            });

            //top panel (playlist selector & new playlist & search fields)
            JPanel topPanel = new JPanel();
            topPanel.add(newPlaylistBtn);
            topPanel.add(new JLabel("Playlist:"));
            topPanel.add(playlistCombo);
            topPanel.add(new JLabel("Title:"));
            topPanel.add(titleField);
            topPanel.add(new JLabel("Artist:"));
            topPanel.add(artistField);
            topPanel.add(new JLabel("Album:"));
            topPanel.add(albumField);
            topPanel.add(new JLabel("Genre:"));
            topPanel.add(genreField);
            topPanel.add(searchBtn);
            topPanel.add(resetBtn);
            frame.add(topPanel, BorderLayout.NORTH);

            //context menu (right-click on table row)
            JPopupMenu contextMenu = new JPopupMenu();
            JMenuItem editItem = new JMenuItem("Edit");
            JMenuItem deleteItem = new JMenuItem("Delete");
            JMenuItem youtubeItem = new JMenuItem("Search on YouTube");

            editItem.addActionListener(e -> update.doClick());
            deleteItem.addActionListener(e -> delete.doClick());
            youtubeItem.addActionListener(e -> {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    String title = (String) model.getValueAt(row, 0);
                    String artist = (String) model.getValueAt(row, 1);
                    String query = title + " " + artist;
                    try {
                        Desktop.getDesktop().browse(
                            new URI("https://www.youtube.com/results?search_query=" +
                                    URLEncoder.encode(query, "UTF-8"))
                        );
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            contextMenu.add(editItem);
            contextMenu.add(deleteItem);
            contextMenu.add(youtubeItem);
            table.setComponentPopupMenu(contextMenu);

            //align table to center
            frame.add(new JScrollPane(table), BorderLayout.CENTER);

            //bottom panel (buttons + stats)
            statsLabel = new JLabel("🎵 Songs: 0 | ⏱ Total Duration: 0:00");
            JPanel bottomPanel = new JPanel();
            bottomPanel.add(add);
            bottomPanel.add(update);
            bottomPanel.add(delete);
            bottomPanel.add(sort);
            bottomPanel.add(favSort);
            bottomPanel.add(importBtn);
            bottomPanel.add(exportBtn);
            bottomPanel.add(statsLabel);
            frame.add(bottomPanel, BorderLayout.SOUTH);

            //initial table load & stats update
            reloadTable(manager, model);

            frame.setVisible(true);
        });
    }

    //dialog for adding or updating a Song
    static void showSongDialog(Song existing, int index, PlaylistManager manager, DefaultTableModel model) {
        JTextField f1 = new JTextField(existing != null ? existing.title : "");
        JTextField f2 = new JTextField(existing != null ? existing.artist : "");
        JTextField f3 = new JTextField(existing != null ? existing.album : "");
        JTextField f4 = new JTextField(existing != null ? existing.genre : "");
        JTextField f5 = new JTextField(existing != null ? formatDuration(existing.duration) : "");
        JCheckBox fav = new JCheckBox("Favorite", existing != null && existing.favorite);

        Object[] fields = {
            "Title:", f1,
            "Artist:", f2,
            "Album:", f3,
            "Genre:", f4,
            "Duration (m:ss or decimal):", f5,
            fav
        };

        int res = JOptionPane.showConfirmDialog(
            null, fields,
            existing == null ? "Add Song" : "Update Song",
            JOptionPane.OK_CANCEL_OPTION
        );

        if (res == JOptionPane.OK_OPTION) {
            Song s = new Song(
                f1.getText(), f2.getText(), f3.getText(), f4.getText(),
                parseDuration(f5.getText())
            );
            s.favorite = fav.isSelected();

            if (existing == null) {
                manager.addSong(s);
            } else {
                manager.updateSong(index, s);
            }
            reloadTable(manager, model);
        }
    }

    //reloads the JTable and updates the stats label
    static void reloadTable(PlaylistManager manager, DefaultTableModel model) {
        model.setRowCount(0);
        for (Song s : manager.getSongs()) {
            model.addRow(new Object[]{
                s.title,
                s.artist,
                s.album,
                s.genre,
                formatDuration(s.duration),
                s.favorite
            });
        }
        if (statsLabel != null) {
            double totalDuration = manager.getSongs()
                .stream().mapToDouble(song -> song.duration).sum();
            int totalSongs = manager.getSongs().size();
            statsLabel.setText(
                "🎵 Songs: " + totalSongs +
                " | ⏱ Total Duration: " + formatDuration(totalDuration)
            );
        }
    }
}
