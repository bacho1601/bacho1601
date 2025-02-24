import java.util.Scanner;
public class carGallery{

    Scanner scanner = new Scanner(System.in);
    Car[] carArr = {
        new Car("Toyota", "Prius", 1999, 3000.12, false),
        new Car("Ford", "Ranger Raptor", 2020, 4013.41, true),
        new Car("Renault", "Captur", 2015, 5000, false),
        new Car("Toyota", "Prius", 1999, 10423.42, true)
    };



}

class Car{
    String make;
    String model;
    int year;
    double price;
    boolean isLeased;

    Car(String make, String model, int year, double price, boolean isLeased){
        this.make = make;
        this.model = model;
        this.year = year;
        this.price = price;
        this.isLeased = isLeased;
    }

    public void displayInfo(){
        System.out.println(make + " " + model + " "  + year + " " + price + " " + isLeased);
    }

    public void mostExpensive(Car[] carArr){
        int min = 0;
        double minprice = carArr[0].price;
        for (int i = 0; i < carArr.length; i++) {
            if(minprice > carArr[i].price){
                minprice = carArr[i].price;
                min = i;
            }
        }
        carArr[min].displayInfo();
    }

    public double AveragePrice(Car[] carArr){
        double total = 0;
        for(int i = 0; i<carArr.length; i++){
            total += carArr[i];
        }
        return total/carArr.length;
    }

    public void leaseCar(Car[] carArr, int num){
        carArr[num].isLeased = !carArr[num].isLeased;
    }

}
