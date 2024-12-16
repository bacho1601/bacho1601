class Person
  {
     private String name;
     // add another instance here.
     private String address;
  }

  // How can we make the Student class inherit from class Person?
public class Student extends Person
  {
     private int id;
     // add another instance here.
     private double gpa;
    
     public static void main(String[] args)
     {
        Student s = new Student();
        System.out.println(s instanceof Student);
        System.out.println(s instanceof Person);
        
     }
  }
