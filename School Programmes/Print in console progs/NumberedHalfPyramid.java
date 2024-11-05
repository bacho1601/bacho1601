import java.util.Scanner;
public class NumberedHalfPyramid{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberRows = scanner.nextInt();
        
        for(int i=1; i<=numberRows; i++) {
            for(int k=1; k<=i; k++){
                System.out.print(k + " ");
            }
            System.out.println("");
        }
    }
}
