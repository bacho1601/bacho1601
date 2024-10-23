import java.util.Scanner;

public class VendingMachine {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        for (int i = 1; i > 0; i++) {
            System.out.println("_________\nInput the product code: ");
            String code = scan.nextLine();
            switch (code) {
                case "A1":
                    System.out.println("Product \"Soda\" - Price: $1.50");
                    break;
                case "B2":
                    System.out.println("Product \"Chips\" - Price: $1.25");
                    break;
                case "C3":
                    System.out.println("Product \"Candy\" - Price: $0.75");
                    break;
                case "D4":
                    System.out.println("Product \"Water\" - Price: $1.00");
                    break;
                default:
                    System.out.println("Error. Please type existing code.");
            }
            System.out.println("---------\nTo prompt again, insert anything. \n" +
                                                "To exit the program, type \"end\".");
            String a = scan.nextLine();
            if (a.equals("end")){return;} 
            else if (a.equals("\n")) {System.out.println("Please press enter.");}
        }
    }
}
