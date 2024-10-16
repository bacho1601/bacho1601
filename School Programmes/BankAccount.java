import java.util.Scanner;
public class BankAccount {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Scanner reader = new Scanner(System.in);
        System.out.print("Account Number: ");
        int acc_num = scanner.nextInt();
        System.out.print("Account Holder: ");
        String acc_name = reader.nextLine();
        System.out.print("Initial Balance: ");
        double acc_balance = scanner.nextDouble();

        bank bank = new bank(acc_num, acc_name, acc_balance);

        for (int i = 1; i > 0; i++) {
            System.out.println("Deposit or Withdraw transaction?:");
            String deporwith = reader.nextLine();
            double money = 0;
            if (deporwith.equals("Deposit") || deporwith.equals("deposit")) {
                System.out.print("Input deposit: ");
                money = scanner.nextDouble();
                bank.deposit(money);
            } else if (deporwith.equals("Withdraw") || deporwith.equals("withdraw")) {
                System.out.print("Input withdraw: ");
                money = scanner.nextDouble();
                bank.withdraw(money);
            }
        }

        double money = scanner.nextDouble();
        bank.deposit(money);
            bank.withdraw(money);
        //}
    }
}

class bank
{
    Scanner scan = new Scanner(System.in);
    int acc_num;
    String acc_holder;
    double balance;

    public bank(int acc_num, String acc_holder, double balance){
        this.acc_num = acc_num;
        this.acc_holder = acc_holder;
        this.balance = balance;
    }

    void deposit(double money){
        balance += money;
        System.out.println("Balance: " + balance);
    }
    void withdraw(double money){
        balance -= money;
        System.out.println("Balance: " + balance);
    }
    void display(){
        System.out.println("Account Number: " + acc_num);
        System.out.println("Account Holder: " + acc_holder);
        System.out.println("Balance: " + balance);
    }
    void change(){
        System.out.println("What do you wish to change? \n Account number? \n Account holder \n Balance");
        String ch = scan.nextLine();
        switch (ch) {
            case "Account Number":
                acc_num = scan.nextInt();
            case "Account Holder":
                acc_holder = scan.nextLine();
            case "Balance":
                balance = scan.nextDouble();
            default:

        }

    }
}
