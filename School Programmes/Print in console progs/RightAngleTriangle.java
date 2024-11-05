public class RightAngleTriangle {
    public static void main(String[] args) {
        System.out.println("*");
        int space = 0;
        for(int i=0; i<=5; i++) {
            System.out.print("*  ");
            for (int j = 1; j <= i*3; j++) {
                System.out.print(" ");
                space = j;
            }
            System.out.println("*");
        }
        for (int i = 0; i < space+2+2+3; i++) {
            System.out.print("*");
        }
    }
}
