import java.util.Scanner;
public class StudentBonus {
    public static void main (String[] args){
        Scanner scanner = new Scanner(System.in);
        Scanner reader = new Scanner(System.in);

        System.out.print("Input name: ");
        String name = scanner.nextLine();
        System.out.print("Input last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Input grade level: ");
        String gradeLevel = scanner.nextLine();
        System.out.print("Input GPA: ");
        double gpa = scanner.nextDouble();
        System.out.print("Input School name: ");
        String schoolName = reader.nextLine();
        //scanner.nextLine();
        Students student = new Students(name, lastName, gradeLevel, gpa, schoolName);
        Students.HonorStudentEmail(name, schoolName, gpa);
    }
}

class Students {
    String name;
    String lastName;
    String gradeLevel;
    double gpa;
    String schoolName;

    Students(String name, String lastName, String gradeLevel, double gpa, String schoolName) {
        this.name = name;
        this.lastName = lastName;
        this.gradeLevel = gradeLevel;
        this.gpa = gpa;
        this.schoolName = schoolName;
    }

    public static boolean isHonorStudent (double gpa){
        if (gpa>3.8){
            return true;
        } else {return false;}
    }
    public static void HonorStudentEmail (String name, String schoolName, double gpa){
        if (isHonorStudent(gpa)){
            System.out.println("Dear " + name + " you are selected as one of the honor students of " + schoolName + " in class 2023 with the GPA of " + gpa + ". Congratulations and we look forward to celebrating your achievement in the graduation ceremony.");
        }
    }
}
