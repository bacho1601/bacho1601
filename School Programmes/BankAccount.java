import java.util.Scanner;
public class BankAccount {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Scanner reader = new Scanner(System.in);
        System.out.println("Account Number: ");
        int acc_num = scanner.nextInt();
        System.out.println("Account Holder: ");
        String acc_name = scanner.nextLine();
//        System.out.println("Initial Balance: ");
//        int acc_balance = scanner.nextInt();

        bank bank = new bank(acc_num, acc_name, 0);
        System.out.println("Deposit or Withdraw transaction?:");
        String deporwith = reader.nextLine();

        bank.deposit(money);
            System.out.print("Input withdraw: ");
            double money = scanner.nextDouble();
            bank.withdraw(money);
        }
    }
}

class bank
{
    int acc_num;
    String acc_holder;
    double balance;

    public bank(int acc_num, String acc_holder, double balance){
        this.acc_num = acc_num;
        this.acc_holder = acc_holder;
        this.balance = balance;
    }

    void deposit(double money){
        balance =+ money;
        System.out.println("");
    }
    void withdraw(double money){
        balance =- money;
        System.out.println("");
    }
}
