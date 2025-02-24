public class carManagement {
    public static void main(String[] args){

          //array of objects

        car[] carArr = {
            new car("toyota","yaris",2020),
            new car("honda", "jazz", 2017),
            new car("ford", "fusion", 2021)
        };

        for (car item: carArr) {
            item.displayInfo();
            System.out.println("");
        }
    }
}

class car{
      //attribution
    public String make;
    public String model;
    public int year;

    public car(String make, String model, int year){
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public void displayInfo(){
        System.out.println(make + " " + model + " " + year);
    }
}
