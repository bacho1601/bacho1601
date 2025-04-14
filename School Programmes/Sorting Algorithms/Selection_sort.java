public class Selection_sort {
    public static void main(String[] args) {
        int[] elements = {5, 3, 4, 2, 1};
        for(int j = 0; j < elements.length-1; j++){
            int minIndex = j;
            for(int k = j+1; k < elements.length; k++){
                if(elements[k] < elements[minIndex]){
                    minIndex = k;
                }
            }
            int temp = elements[j];
            elements[j] = elements[minIndex];
            elements[minIndex] = temp;
        }
    }
}
