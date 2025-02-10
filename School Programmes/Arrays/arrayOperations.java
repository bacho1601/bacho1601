public class arrayOperations {
    public static void main(String[] args) {
        int[] arr = {40,21,42,53,12,14,9,21};

        // 1. FINDING THE SUM OF ELEMENTS IN ARRAY
        System.out.println(sumOfItems(arr));

        // 2. FINDING A NUMBER IN AN ARRAY
        System.out.println(numberFinder(53, arr));

        // 3. CREATE AN ARRAY WITH ODD NUMBERS WITHIN A RANGE
        createOddArray(10);

        // 4. FINDING THE BIGGEST NUMBER IN ARRAY
        System.out.println(findMax(arr));

        // 5. FINDING THE AVERAGE OF ARRAY
        System.out.println(calculateAverage(arr));

        // 6. COUNTING EVEN NUMBERS IN ARRAY
        System.out.println(countEvenNumbers(arr));

        // 7. FINDING DUPLICATES
        System.out.println(findDuplicates(arr));
    }

    public static int sumOfItems(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    public static boolean numberFinder(int num, int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                return true;
            }
        }
        return false;
    }

    public static void createOddArray(int size) {
        int[] oddArray = new int[size];
        int oddNumber = 1;

        for (int i = 0; i < size; i++) {
            oddArray[i] = oddNumber;
            oddNumber += 2;
        }
        System.out.print("Odd Array: ");
        for (int i = 0; i < oddArray.length; i++) {
            System.out.print(oddArray[i] + " ");
        }
        System.out.println();
    }

    public static int findMax(int[] array) {
        int max = 0;
        for (int i = 0; i < array.length; i++){
            if(array[i] > max){
                max = array[i];
            }
        }
        return max;
    }

    public static int calculateAverage(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++){
            sum += array[i];
        }
        return sum / array.length;
    }

    public static int countEvenNumbers(int[] array) {
        int count = 0;
        for (int i = 0; i < array.length; i++){
            if(array[i] % 2 == 0)
                count++;
        }
        return count;
    }

    public static boolean findDuplicates(int[] array) {
        boolean hasDuplicate = false;
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < i; j++){
                if (array[i] == array[j])
                    hasDuplicate = true;
            }
        }
        return hasDuplicate;
    }
}
