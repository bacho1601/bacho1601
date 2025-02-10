import java.util.Scanner;
public class gradebook {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] students = {"Nikoleta", "Vasil", "Martin","Svetoslav","Irina", "Aleksandar"};
        int[] grades = {99, 49, 89, 45, 35, 78 };

        /* Implement a switch case for the menu:
        * 1. Calculate Average
        * 2. Display the student with the highest grade
        * 3. Display the student with the lowest grade
        * 4. Display the count of students who are failing (>50 is failing)
        * 5. Look up student grade with user input.
        * 6. Exit. */

        int num = scanner.nextInt();
        switch (num) {
            case 1:
                System.out.println(calculateAverage(grades));
                break;
            case 2:
                System.out.println(findHighestGradeStudent(students, grades));
                break;
            case 3:
                System.out.println(findLowestGradeStudent(students, grades));
                break;
            case 4:
                System.out.println(countFailedStudents(grades));
                break;
            case 5:
                String whatStudent = scanner.nextLine();
                System.out.println(lookupStudentGrade(students, grades, whatStudent));
                break;
            case 6:
                break;
        }



    }

    private static double calculateAverage(int[] grades) {
        int sum = 0;
        for(int i = 0; i<grades.length; i++){
            sum =+ grades[i];
        }
        return sum/(grades.length+1);
    }

    private static String findHighestGradeStudent(String[] studentNames, int[] grades) {
        int position = 0;
        int max = 0;
        for(int i = 0; i<grades.length; i++){
            if(max < grades[i]){
                max = grades[i];
                position = i;
            }
        }
        return studentNames[position];
    }

    private static String findLowestGradeStudent(String[] studentNames, int[] grades) {
        int posit = 0;
        int min = 101;
        for(int i = 0; i<grades.length; i++){
            if(min > grades[i]){
                min = grades[i];
                posit = i;
            }
        }
        return studentNames[posit];
    }

    private static int countFailedStudents(int[] grades) {
        int counter = 0;
        for(int i = 0; i<grades.length; i++){
            if(grades[i]<50){
                counter++;
            }
        }
        return counter;
    }

    private static int lookupStudentGrade(String[] studentNames, int[] grades, String studentToLookup) {
        int position = 0;
        for(int i = 0; i < studentNames.length; i++){
            if(studentToLookup == studentNames[i]){
                position = i;
            }
        }
        return grades[position];
    }
}
