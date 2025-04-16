public class Insertion_sort {
    public static void main(String[] args){
        int[] elements = {1, 2, 3, 5, 4};
        int counter = 0;
        for (int i = 1; i < elements.length; i++) {
            int temp = elements[i];
            int possibleIndex = i;

            while(possibleIndex > 0 && temp<elements[possibleIndex - 1]){
                elements[possibleIndex] = elements[possibleIndex - 1];
                possibleIndex--;
                counter++;
            }
            elements[possibleIndex] = temp;
        }

        for (int i = 0; i < elements.length; i++) {
            System.out.println(elements[i]);
        }
        System.out.println(counter);
    }
}
