public class ExampleSample {
    public static void main(String[] args) {

        // 1. CREATING AN ARRAY WITH THE INITIALIZER LIST
        int[] arr = {1, 1, 2, 3, 5, 8};
        System.out.println(arr[0]);
        System.out.println(arr[1]);
        System.out.println(arr[2]);
        System.out.println(arr[3]);

        // 2. CREATING AN EMPTY ARRAY
        int[] arr2 = new int[5];

        // You can see the default values for the int array is 0
        System.out.println(arr2[0]);
        System.out.println(arr2[3]);

        String[] strings = new String[4];

        // null is the default value for objects
        System.out.println(strings[0]);

        // 3. INDEXING INTO AN ARRAY
        
        // Make an array with 10 slots.
        int[] arr3 = new int[10];

        // Set the value at index 1.
        arr3[1] = 4;

        // Get the value at index 1.
        System.out.println(arr3[1]);

        arr3[2] = 10;
        arr3[3] = -12;
        System.out.println(arr3[2]);
        }

    // 4. CREATE A METHOD WITH ARRAY PARAMETER
    public static int findSize(int[] myArr){
        return myArr.length;

    }
}
