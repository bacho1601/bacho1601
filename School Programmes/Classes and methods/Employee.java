import java.util.Scanner;

public class Employee {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        Scanner reader = new Scanner(System.in);

        System.out.print("Input First Name: ");
        String firstName = scanner.nextLine();
        System.out.print("Input Last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Input EmployeeID: ");
        String employeeID = scanner.nextLine();
        System.out.print("Input salary: ");
        double salary = scanner.nextDouble();
        System.out.print("Input Performance rating: ");
        double performanceRating = reader.nextDouble();

        Employeeid employeeid = new Employeeid(firstName, lastName, employeeID, salary, performanceRating);
        Employeeid.sendPerformanceEmail(firstName, lastName, salary, performanceRating);
    }
}

class Employeeid {
    String firstName;
    String lastName;
    String employeeID;
    double salary;
    double performanceRating;

    Employeeid(String firstName, String lastName, String employeeID, double salary, double performanceRating) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeID = employeeID;
        this.salary = salary;
        this.performanceRating = performanceRating;
    }

    public static boolean isHighPerformer (double performanceRating){
        if (performanceRating>4.5){
            return true;
        } else {return false;}
    }

    public static double calculateBonus (double performanceRating, double salary) {
        if(isHighPerformer(performanceRating)){
            return 0.2*salary;
        } else {return 0.05*salary;}
    }

    public static void sendPerformanceEmail (String firstName, String lastName, double salary, double performanceRating){
        if (isHighPerformer(performanceRating)){
            System.out.println("Dear " + firstName + " " + lastName +
                    "\nBased on your performance rating of " + performanceRating + " you are a valued member of our team. " +
                    "\nYour bonus for this period is " + calculateBonus(performanceRating, salary) + ". Keep up the great work!");
        }
    }
}
