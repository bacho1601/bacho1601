import java.util.InputMismatchException;
import java.util.Scanner;

public class EmployeeInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner reader = new Scanner(System.in);

        Employee employee = new Employee("John Dee", 101, 5000);
        System.out.println("Employee 1 Details:");
        employee.displayDetails("John Dee", 101, 5000);

        for (int i = 2; i >= 2; i++) {
            System.out.println("Enter details for a new employee:");
            try {
                System.out.print("Name: ");
                String name = scanner.nextLine();
                //check if user wants to end program
                Employee.endProgramCheck(name);

                System.out.print("Employee ID: ");
                int employeeId = reader.nextInt();
                System.out.print("Monthly Salary: ");
                double salary = reader.nextDouble();

                employee = new Employee(name, employeeId, salary);
                System.out.println("Employee " + i + " details:");
                employee.displayDetails(name, employeeId, salary);

            } //check for invalid input and "end" instances in any field, just in case
            catch (InputMismatchException e) {
                if(e.getMessage().equalsIgnoreCase("end")) {
                    System.out.println("Shutting down program...");
                    break;
                } else {
                    System.out.println("Invalid input");
                    scanner.next();
                    reader.next();
                }
            }
        }
        scanner.close();
        reader.close();
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
        if (string.equalsIgnoreCase("end")) {
            System.out.println("Shutting down program...");
            System.exit(0);        }
    }
}
