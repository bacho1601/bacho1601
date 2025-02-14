import java.util.Scanner;
public class Array_initializer{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("how many items?:");
        int length = scanner.nextInt();
        int[] arr = new int[length+1];
        for(int i=0; i<length; i++) {
            System.out.println("enter item " + i);
            arr[i] = scanner.nextInt();
        }
        int sum = 0;
        for(int i = 0; i<=length; i++){
            sum += arr[i];
        }
        System.out.println("sum is: " + sum + " average is; " + sum/length);
    }
}
