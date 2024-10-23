//Vowel counter and amount printer
import java.util.Scanner;
public class Ex4Moodle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(countAndPrintVowels(input));
    }
    public static int countAndPrintVowels(String input) {
        int counter = 0;
        for (int i = 0; i < input.length(); i++) {
            String character = String.valueOf(input.charAt(i));
            if (character.equalsIgnoreCase("a")||
                character.equalsIgnoreCase("e")||
                character.equalsIgnoreCase("i")||
                character.equalsIgnoreCase("o")||
                character.equalsIgnoreCase("u")) {
                counter++;
            }
        }
        return counter;
    }
}
