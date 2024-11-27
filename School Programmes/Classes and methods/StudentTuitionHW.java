public class StudentTuitionHW{
    public static void main(String[] args){
        Student s1 = new Student("Svenio",20,"123123","part-time", 10000);
        s1.generateDiscountEmail();
        Student s2 = new Student("Barbaros",28,"234234","full-time", 10000);
        s2.generateDiscountEmail();
        Student s3 = new Student("Arriba",13,"456456","full-time", 10000);
        s3.generateDiscountEmail();
    }
}

class Student {
    String name;
    int age;
    String studentID;
    String course;
    double tuitionFee;

    Student(String name, int age, String studentID, String course, double tuitionFee){
        this.name = name;
        this.age = age;
        this.studentID = studentID;
        this.course = course;
        this.tuitionFee = tuitionFee;
    }

    boolean isEligibleForDiscount(){
        return (age<25)&&(course.equalsIgnoreCase("full-time"));
    }
    double calculateDiscount(){
        return tuitionFee*0.75;
    }

    void generateDiscountEmail(){
        if(isEligibleForDiscount()){
            System.out.println("Dear "+ name + ",\n" +
                "We are pleased to inform you that you are eligible for a 15% discount on your tuition fee for the course " + course + ". \n" +
                "Your discounted tuition fee is " + calculateDiscount() + ". We hope this helps in supporting your academic journey!\n" +
                "Best regards,\n" +
                "American Boilage of Atrophia Administration\n");
        }
    }
}


