import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WriterGroceries {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> listproducts = new ArrayList<>();
        String input;
        for (int i = 2; i > 1; i++) {
            input = scanner.nextLine();
            if (input.equals("done")) {
                i = 0;
            }
            listproducts.add(input);
        }
        filewriterTest(listproducts, "sample.txt");
    }
    public static void filewriterTest(ArrayList<String> arrayList, String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("sample.txt"));
            for (String item : arrayList) {
                writer.write(item);
                writer.write("\n");
            }
            System.out.println("The write was successful");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
