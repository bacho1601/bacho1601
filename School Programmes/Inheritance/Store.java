class ItemForSale
{
    private int stock;
    private int price;
    private double rating;
}

class Movie extends ItemForSale 
{
    private String title;
    private String genre;
    private String director;
}

class Book extends ItemForSale
{
    private String title;
    private String author;
    private int pages;
}

class Author 
{
    private String name;
    private String birthyear;
    private int bookcount;
}

public class Store
{
     public static void main(String[] args)
     {
        Store s = new Store();
        Book b = new Book();
        System.out.println(b instanceof ItemForSale);
        
     }
}
