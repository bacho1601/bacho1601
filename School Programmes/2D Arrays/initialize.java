import java.util.*;

public class initialize{
    public static void main(String[] args){
        
        /* initiliaze 2D Array here.
        1 2 3
        4 5 6
        7 8 9
        */
        int[][] newarr = new int[3][3];
        int counter = 1;
        for(int i = 0; i<newarr.length; i++){
            for(int j = 0; j<newarr[i].length; j++){
                newarr[i][j] = counter;
                counter++;
            }
        }
        
        // iterate through the items with a nested loop as you add them to the sum  
        int sum = 0;
        for(int i = 0; i<newarr.length; i++){
            for(int j = 0; j<newarr[i].length; j++){
                sum += newarr[i][j];
            }
        }
        System.out.println(sum);
    }
}
