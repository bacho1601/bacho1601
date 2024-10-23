import java.util.Scanner;
public class Ex1Moodle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //fizzbuzz fizzbuzz = new fizzbuzz();
        int n = scanner.nextInt();
        for (int i = 1; i <= n; i++) {
            fizzbuzz.Fizzbuzz(i);
            if (!fizzbuzz.override){
                System.out.println(i);
            } else {
                System.out.print("");
            }
            fizzbuzz.override = false;
        }
    }
}

class fizzbuzz {
    static boolean override = false;
    public static void Fizzbuzz(int n) {
        if (n%3==0 && n%5==0) {
            override = true;
            System.out.println("FizzBuzz");
        } else if (n % 3 == 0) {
            override = true;
            System.out.println("Fizz");
        } else if (n % 5 == 0) {
            override = true;
            System.out.println("Buzz");
        }
    }
}
