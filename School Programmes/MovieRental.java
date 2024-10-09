import java.util.Scanner;
public class MovieRental {
    public static void main(String[] args){
        Scanner read = new Scanner(System.in);

            System.out.println("Please input the title, the genre, and the stock amount:");
            String title = read.nextLine();
            String genre = read.nextLine();
            int stock = read.nextInt();

            //CREATE AN OBJECT BASED ON USER INPUT
            Movies movie = new Movies(title, genre, stock);
            movie.displayInfo();
            movie.rentMovie();
    }
}

class Movies
{
    String title;
    String genre;
    int stock;

    public Movies(String title, String genre, int stock)
    {
        this.title = title;
        this.genre = genre;
        this.stock = stock;
    }

    void displayInfo(){
        System.out.println("----------");
        System.out.println("The movie is: " + title + "\nThe genre is: " + genre + "\nStock status: " + stock);
    }

    void rentMovie(){
        System.out.println("----------");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to rent? (Y/N): ");
        String rentyn = scanner.nextLine();
        switch (rentyn){
            case "Y":
                this.stock--;
                displayInfo();
                rentMovie();
            case "N":
                System.out.println("cool");
                displayInfo();
                rentMovie();
            default:
                System.out.println("Please type in Y or N");
                rentMovie();
        }
    }
}
