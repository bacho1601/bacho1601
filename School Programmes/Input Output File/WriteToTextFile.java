import java.io.*;

public class WriteToTextFile {

    public static void main(String[] args) {
        fileWriterTest("ms. Yavuz is the best teacher!\uD83E\uDD20","/Users/eda/Desktop/fileWriterTester.txt");
        printWriterTest("ms. Yavuz is still the best teacher!\uD83E\uDD20\uD83E\uDD20\uD83E\uDD20","/Users/eda/Desktop/printWriterTester.txt" );
    }

    // since so many things can go wrong working with files, we'll have to handle exceptions.

    private static void fileWriterTest(String message, String fileName){
        try(FileWriter outStream = new FileWriter(fileName, true)){ // Create stream & open file
            outStream.write(message); // pass the message into the write method.
            System.out.println("fileWriterTest method is successful!");
            }
        catch (IOException e){
            System.out.println("oppa: " + e);
        }
    }

    private static void printWriterTest(String message, String fileName){
        try(PrintWriter outStream = new PrintWriter(new FileOutputStream(fileName))) {
            outStream.print(message);
            System.out.println("printWriterTest method is successful!");
        }
        catch (IOException e){
            System.out.println("oppa: " + e);
        }
    }
}
