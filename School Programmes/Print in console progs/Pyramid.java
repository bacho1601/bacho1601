import java.util.Scanner;
public class Pyramid{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberRows = scanner.nextInt();
        
        for(int i=1; i<=numberRows; i++) {
            
            for(int j=2*(numberRows-i); j>0 ;j--) {
                System.out.print(" ");
            }
            for(int k = 1; k<=i*2-1; k++){
                System.out.print("* ");
            }
            System.out.println("");
        }
    }
}
