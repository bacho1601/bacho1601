import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class printwrite {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        filewriterTest("ms. Yavuz is the best teacher!\uD83E\uDD20","/home/sgochkov26/Desktop/sample.txt");

    }
    public static void filewriterTest(String message, String fileName){
        try {
            FileWriter outStream = new FileWriter(fileName, true);
            outStream.write(message);
            System.out.println("The write was successful");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }
    public static void printWriterTest(String message){
        try (PrintWriter outStream = new PrintWriter(new FileOutputStream("/home/sgochkov26/Desktop", true))) {
            outStream.print(message);
            System.out.println("The write was successful");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
