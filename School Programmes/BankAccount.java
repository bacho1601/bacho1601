import java.util.Scanner;
public class BankAccount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner reader = new Scanner(System.in);
        System.out.print("Account Number: ");
        int acc_num = scanner.nextInt();
        System.out.print("Account Holder: ");
        String acc_name = reader.nextLine();
        System.out.print("Initial Balance: ");
        double acc_balance = scanner.nextDouble();
        System.out.println("");

     	bank bank = new bank(acc_num, acc_name, acc_balance);

        for (int i = 2; i > 1; i++) {
            System.out.print("(1) Deposit?\n(2) Withdraw?\n(3) Change info?\n(4) Display info?\n(5) Exit program\n-  ");
            byte choice = reader.nextByte();
            System.out.println("");
            double money = 0;
            switch (choice) {
                case 1:
                    bank.deposit(money);
                    break;
                case 2:
                    bank.withdraw(money);
                    break;
                case 3:
                    bank.change();
                    break;
                case 4:
                    bank.display();
                    break;
                case 5:
                    i = 0;
            }
        }
    }
}

class bank
{
    Scanner scan = new Scanner(System.in);
    Scanner string = new Scanner(System.in);
    int acc_num;
    String acc_holder;
    double balance;

    public bank(int acc_num, String acc_holder, double balance){
        this.acc_num = acc_num;
        this.acc_holder = acc_holder;
        this.balance = balance;
    }

    void deposit(double money){
        System.out.print("Input deposit amount: ");
        money = scan.nextDouble();
        balance += money;
        System.out.println("Balance: " + balance);
        System.out.println("");
    }

    void withdraw(double money){
        System.out.print("Input withdraw amount: ");
        money = scan.nextDouble();
        if (balance < money){
            System.out.println("   ---    Error: Insufficient Balance, try again.");
            withdraw(money);
        } else {
            balance -= money;
            System.out.println("Balance: " + balance);
            System.out.println("");
        }
    }

    void display(){
        System.out.println("---------------");
        System.out.println("Account Number: " + acc_num);
        System.out.println("Account Holder: " + acc_holder);
        System.out.println("Balance: " + balance);
        System.out.println("---------------");
        System.out.println("");
    }

    void change(){
        System.out.print("What do you wish to change?\n(1) Account number\n(2) Account holder\n(3) Balance\n- ");
        byte ch = scan.nextByte();
        System.out.println("");
        switch (ch) {
            case 1:
                System.out.print("Input new Account Number: ");
                acc_num = scan.nextInt();
                System.out.println("");
                break;
            case 2:
                System.out.print("Input new Account Holder: ");
                acc_holder = string.nextLine();
                System.out.println("");
                break;
            case 3:
                System.out.print("Type new Balance amount: ");
                balance = scan.nextDouble();
                System.out.println("");
                break;
            default:
                System.out.println("Invalid Choice");
                System.out.println("");
        }
    }
}
