class MathOperations{
    public int add(int a, int b) {
        return a+b;
    }
    public int add(int a, int b, int c) {
        return a+b+c;
    }
    public double add(double a, double b){
        return a+b;    
    }
    public int multiply(int a, int b){
        return a*b;
    }
    public double multiply(double a, double b, double c){
        return a*b*c;
    }
}
public class OverrideOperations{
    public static void main(String[] args){
        
        MathOperations math = new MathOperations();
        
        System.out.println("Sum of two integers: " + math.add(5, 10));
        System.out.println("Sum of three integers: " + math.add(5, 10, 15));
        System.out.println("Sum of two doubles: " + math.add(3.5, 2.7));
        System.out.println("Product of two integers: " + math.multiply(4, 8));
        System.out.println("Product of three doubles: " + math.multiply(2.5, 1.5, 3.0));
    }
}
