import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Employee employee = new Employee("John Dee", 101, 5000);
        System.out.println("Employee 1 Details:");
        employee.displayDetails("John Dee", 101, 5000);

        for (int i = 2; i >= 2; i++) {
            System.out.println("!Type in \"end.program\" wherever to end the program.");
            System.out.println("Enter details for a new employee:");
            System.out.print("Name: ");
            String name = scanner.nextLine();
            //check if user wants to end program
            Employee.endProgramCheck(name);

            System.out.print("Employee ID: ");
            String intemployeeId = scanner.nextLine();
            Employee.endProgramCheck(intemployeeId);
            int employeeId = Integer.parseInt(intemployeeId);

            System.out.print("Monthly Salary: ");
            String doublesalary = scanner.nextLine();
            Employee.endProgramCheck(doublesalary);
            double salary = Double.parseDouble(doublesalary);
            /* parsing as int is the only option
            i found for the error catch checks to work. Otherwise, using try {} catch () {},
            e.getMessage() = null (and subsequently I get a console error message, not mine) */
            System.out.println("");

            employee = new Employee(name, employeeId, salary);
            System.out.println("Employee " + i + " details:");
            employee.displayDetails(name, employeeId, salary);

            //check for invalid input and "end" instances in any field, just in case
        }
        scanner.close();
        // saw online that this helps prevent data leaks,
        // I'll try to implement it in my practices from now on
    }
}


class Employee {
    String name;
    int employeeId;
    double salary;

    public Employee(String name, int employeeId, double salary){
        this.name = name;
        this.employeeId = employeeId;
        this.salary = salary;
    }

    public double calculateYearlySalary() {
        return salary*12;
    }
    void displayDetails(String name, int employeeId, double salary) {
        System.out.println("Name: " + name);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Monthly Salary: " + salary);
        System.out.println("Yearly Salary: " + calculateYearlySalary()+"\n");
    }

    static void endProgramCheck(String string) {
        if (string.equalsIgnoreCase("end.program")) {
            System.out.println("Shutting down program...");
            System.exit(0);
        }
    }
}
