//task example letter counter
public class ExampleInClass {
    public static void main(String[] args) {
        String string = "titulqr titev";
        int counter = 0;
        for (int i = 0; i < string.length(); i++) {
            String letter = String.valueOf(string.charAt(i));
            if (letter.equalsIgnoreCase("t")) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
