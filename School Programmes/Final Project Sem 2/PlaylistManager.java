import java.io.*;
import java.util.*;

public class MusicPlaylistConsoleApp {

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

    static class PlaylistManager {
        List<Song> songs = new ArrayList<>();

        void addSong(Song s) {
            songs.add(s);
        }

        void deleteSong(int index) {
            if (index >= 0 && index < songs.size()) {
                songs.remove(index);
            }
        }

        void listSongs() {
            if (songs.isEmpty()) {
                System.out.println("No songs in the playlist.");
                return;
            }
            for (int i = 0; i < songs.size(); i++) {
                Song s = songs.get(i);
                System.out.printf("%d. %s - %s (%s, %s) [%.2f mins]%s\n",
                        i + 1, s.title, s.artist, s.album, s.genre, s.duration, s.favorite ? " ★" : "");
            }
        }

        void saveToFile() {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter("songs_export.csv"))) {
                for (Song s : songs) {
                    bw.write(s.toString());
                    bw.newLine();
                }
            } catch (IOException e) {
                System.out.println("Failed to save songs.");
            }
        }

        void loadFromFile() {
            songs.clear();
            try (BufferedReader br = new BufferedReader(new FileReader("songs_export.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    songs.add(Song.fromString(line));
                }
            } catch (IOException e) {
                //if file not found, start over
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PlaylistManager manager = new PlaylistManager();
        manager.loadFromFile();

        while (true) {
            System.out.println("\n🎵 Music Playlist Manager (Console Version)");
            System.out.println("1. List Songs");
            System.out.println("2. Add Song");
            System.out.println("3. Delete Song");
            System.out.println("4. Save and Exit");
            System.out.print("Choose an option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    manager.listSongs();
                    break;
                case "2":
                    System.out.print("Title: ");
                    String title = sc.nextLine();
                    System.out.print("Artist: ");
                    String artist = sc.nextLine();
                    System.out.print("Album: ");
                    String album = sc.nextLine();
                    System.out.print("Genre: ");
                    String genre = sc.nextLine();
                    System.out.print("Duration (minutes): ");
                    double duration = Double.parseDouble(sc.nextLine());
                    System.out.print("Favorite (y/n): ");
                    boolean fav = sc.nextLine().trim().equalsIgnoreCase("y");
                    Song s = new Song(title, artist, album, genre, duration);
                    s.favorite = fav;
                    manager.addSong(s);
                    break;
                case "3":
                    manager.listSongs();
                    System.out.print("Enter song number to delete: ");
                    int index = Integer.parseInt(sc.nextLine()) - 1;
                    manager.deleteSong(index);
                    break;
                case "4":
                    manager.saveToFile();
                    System.out.println("Playlist saved. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
