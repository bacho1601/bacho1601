import java.util.Scanner;

public class StudentGPAManagement
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.print("name: ");
        String stuname = scan.next();
        System.out.print("section: ");
        String section = scan.next();
        Student stu1 = new Student(stuname, section);
        stu1.calculateGPA();
    }
}

class Student {
    // ATTRIBUTES
    String section;
    double GPA;
    String stuname;
    double totalGrade;
    int gradeCount;


    // CONSTRUCTOR
    public Student(String stuname, String section){ //public = accessible to whole program
        this.section = section;
        this.stuname = stuname;
    }


    // METHODS

    void calculateGPA(){       // void = doesn't return anything
        System.out.println("------------");
        Scanner reader = new Scanner(System.in);
        System.out.println("How many grades would you like to enter?: ");
        int i = reader.nextInt();
        System.out.println("Please enter grades (" + i + "): ");

        for (int j = 0; j <= i; j++) {
            double exam1 = reader.nextDouble();
            totalGrade += exam1;
            gradeCount++;
        }
        GPA = totalGrade/gradeCount;
        System.out.println("The average GPA of " + stuname + " in " + section + " is: ");
        System.out.println("                    " + GPA);
    }
}
