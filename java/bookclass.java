public class bookclass {
    private static int bookCount = 0;
    private String title;
    private String author;
    private double price;
    public bookclass(String title, String author, double price) {
        this.title = title;
        this.author = author;
        this.price = price;
        bookCount++; 
    }

    public static int getBookCount() {
        return bookCount;
    }

    public void displayBookInfo() {
        System.out.println("Book Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Price: $" + price);
    }

    public static void main(String[] args) {
        bookclass book1 = new bookclass("Java Programming", "John Doe", 49.99);
        bookclass book2 = new bookclass("Python Basics", "Jane Smith", 39.99);
        bookclass book3= new bookclass("python","abc",99);

        System.out.println("Book 1 Details:");
        book1.displayBookInfo();
        System.out.println("\nBook 2 Details:");
        book2.displayBookInfo();

        System.out.println("\nTotal number of books: " + bookclass.getBookCount());
    }
}
