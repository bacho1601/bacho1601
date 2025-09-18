import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;

public class Problem1_StudentWorks {
    public static void main(String[] args) {
        int n = 45;
        int[] arr = {-99, -98, -95, -89, -82, -69, -68, -64, -53, -52, -50, -42, -40,
                -38, -31, -30, -27, -21, -7, 2, 4, 13, 16, 19, 21, 22, 42, 43, 44, 45,
                46, 50, 53, 59, 62, 63, 76, 77, 83, 86, 87, 90, 93, 97, 98};

        StudentWorks studentWorks = new StudentWorks(n, arr);
        System.out.println("valid works - " + studentWorks.ReadPoints());
        studentWorks.MinDPoints();
        studentWorks.Laureates();

    }
}

class StudentWorks {
    int n;
    int[] arr;

    public StudentWorks(int n, int[] arr){
        this.n = n;
        this.arr = arr;
    }

    public int ReadPoints(){
        int j = 0;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] >= 0){
                j++;
            }
        }
        return j;
    }

    public void MinDPoints(){
        int[] n = new int[3];

        //determination of highest 3 scores from the array
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>n[0]){
                n[0] = arr[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>n[1] && arr[i]!=n[0]){
                n[1] = arr[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>n[2] && arr[i]!=n[0] && arr[i]!=n[1]){
                n[2] = arr[i];
            }
        }
        int[] p = {n[0]-n[1], n[1]-n[2], n[0]-n[2]};
        int max = 0; int min = 0; int mid = 0;

        for (int i = 0; i < p.length; i++) {
            if(p[i]>max){
                max = p[i];
            }
        }
        for (int i = 0; i < p.length; i++) {
            if(p[i]>mid && p[i]!=max){
                mid = p[i];
            }
        }
        for (int i = 0; i < p.length; i++) {
            if(p[i]>min && p[i]!=max && p[i]!=mid){
                min = p[i];
            }
        }

        System.out.println("minimal difference - " + max + " p_1, " + mid + " p_2, " + min + " p_3");

    }

    public void Laureates(){
        int count = 0;
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;
        ArrayList<Integer> laurs = new ArrayList<Integer>();

        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>n1){
                n1 = arr[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>n2 && arr[i]!=n1){
                n2 = arr[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>n3 && arr[i]!=n1 && arr[i]!=n2){
                n3 = arr[i];
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == n1 || arr[i] == n2 || arr[i] == n3){
               laurs.add(i+1) ;
            }
        }
        System.out.println("laurates - ");
        for (int i = 0; i < laurs.size(); i++) {
            System.out.print(laurs.get(i) + ", ");
        }
    }
}