import java.util.Scanner;
public class Ex5Moodle{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int start = scanner.nextInt();
        int end = scanner.nextInt();
        System.out.println(sumOddNumbers(start, end));
    }

    public static int sumOddNumbers(int start, int end) {
        int sumOdds = 0;
        for (int i = start; i <= end; i++) {
            if (i%2!=0) {
                sumOdds += i;
            }
        }
        return sumOdds;
    }
}