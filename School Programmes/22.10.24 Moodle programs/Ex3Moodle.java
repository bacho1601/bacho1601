//number decreaser
import java.util.Scanner;
public class Ex3Moodle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        int range = start-end;
        int start1 = start;
        for (int i = 0; i <= range; i++) {
            decreasenums(end, start);
            start--;
        }
    }

    public static void decreasenums(int end, int start) {
        System.out.println(start);
    }
}
