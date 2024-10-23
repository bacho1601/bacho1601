//AverageCalculator
import java.util.Scanner;
public class Ex2Moodle {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of values:");
        int n = scan.nextInt();
        double sum = 0;
        for (int i = 1; i <= n; i++){
            System.out.println("Enter value " + i + ":");
            int num = scan.nextInt();
            sum += num;
            num = 0;
        }
        System.out.println("Average of the " + n + " values: " + sum/n);
    }
}
