package day5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import day5.ItemAlreadyCheckedOutException;
import day5.ItemNotCheckedOutException;

// Using custom exceptions defined in separate files

// Base LibraryItem class
class LibraryItem {
    private int itemId;
    private String title;
    private String creator;
    public boolean checkedOut;

    public LibraryItem(int itemId, String title, String creator) {
        setitemId(itemId);
        setCreator(creator);
        setTitle(title);
    }
    
    public int getitemId() {
        return itemId;
    }
    
    public void setitemId(int itemId) {
        this.itemId = itemId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getCreator() {
        return creator;
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void checkOut() {
        if (checkedOut) {
            throw new ItemAlreadyCheckedOutException(getitemId());
        }
        checkedOut = true;
    }

    public void returnItem() {
        if (!checkedOut) {
            throw new ItemNotCheckedOutException(getitemId());
        }
        checkedOut = false;
    }

    @Override
    public String toString() {
        return "Item ID: " + itemId + "\nTitle: " + title + "\nCreator: " + creator + "\nChecked Out: " + checkedOut;
    }
}

// Book class
class Book extends LibraryItem {
    private int numPages;

    public Book(int itemId, String title, String author, int numPages) {
        super(itemId, title, author);
        this.numPages = numPages;
    }

    public int getNumPages() {
        return numPages;
    }

    @Override
    public String toString() {
        return super.toString() + "\nType: Book\nNumber of Pages: " + numPages;
    }
}

// DVD class
class DVD extends LibraryItem {
    private int duration;

    public DVD(int itemId, String title, String director, int duration) {
        super(itemId, title, director);
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return super.toString() + "\nType: DVD\nDuration: " + duration + " minutes";
    }
}

// Magazine class
class Magazine extends LibraryItem {
    private int issueNumber;

    public Magazine(int itemId, String title, String publisher, int issueNumber) {
        super(itemId, title, publisher);
        this.issueNumber = issueNumber;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    @Override
    public String toString() {
        return super.toString() + "\nType: Magazine\nIssue Number: " + issueNumber;
    }
}

// Library class
class Library<T extends LibraryItem> {
    private List<T> items = new ArrayList<>();

    public void addItem(T item) {
        items.add(item);
    }

    public void displayItems() {
        for (T item : items) {
            System.out.println(item);
            System.out.println("-------------");
        }
    }

    public boolean checkOutItem(int itemId) {
        T item = findItem(itemId);
        if (item != null) {
            try {
                item.checkOut();
                return true;
            } catch (ItemAlreadyCheckedOutException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public boolean returnItem(int itemId) {
        T item = findItem(itemId);
        if (item != null) {
            try {
                item.returnItem();
                return true;
            } catch (ItemNotCheckedOutException e) {
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    private T findItem(int itemId) {
        for (T item : items) {
            if (item.getitemId() == itemId) {
                return item;
            }
        }
        return null;
    }
}

// Main class
public class EnhancedLibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int ch = scanner.nextInt();
        
        if(ch == 1) {
            Library<Book> bookLibrary = new Library<>();
            int n = scanner.nextInt();
            for(int i = 1; i <= n; i++) {
                int id = scanner.nextInt();
                scanner.nextLine();
                String name = scanner.nextLine();
                String author = scanner.nextLine();
                int numpages = scanner.nextInt();
                Book book = new Book(id, name, author, numpages);
                bookLibrary.addItem(book);
            }
            bookLibrary.displayItems();
        }
        else if(ch == 2) {
            Library<DVD> dvdLibrary = new Library<>();
            int n = scanner.nextInt();
            for(int i = 1; i <= n; i++) {
                int id = scanner.nextInt();
                scanner.nextLine();
                String name = scanner.nextLine();
                String author = scanner.nextLine();
                int duration = scanner.nextInt();
                DVD dvd = new DVD(id, name, author, duration);
                dvdLibrary.addItem(dvd);
            }
            
            Library<Magazine> magazineLibrary = new Library<>();
            n = scanner.nextInt();
            for(int i = 1; i <= n; i++) {
                int id = scanner.nextInt();
                scanner.nextLine();
                String name = scanner.nextLine();
                String author = scanner.nextLine();
                int issueno = scanner.nextInt();
                Magazine magazine = new Magazine(id, name, author, issueno);
                magazineLibrary.addItem(magazine);
            }
            dvdLibrary.displayItems();
            magazineLibrary.displayItems();
        }
        else if(ch == 3) {
            Library<Book> bookLibrary = new Library<>();
            int n = scanner.nextInt();
            for(int i = 1; i <= n; i++) {
                int id = scanner.nextInt();
                scanner.nextLine();
                String name = scanner.nextLine();
                String author = scanner.nextLine();
                int numpages = scanner.nextInt();
                Book book = new Book(id, name, author, numpages);
                bookLibrary.addItem(book);
            }
            
            int itemId = scanner.nextInt();
            if(bookLibrary.checkOutItem(itemId)) {
                System.out.println("Item " + itemId + " checked out successfully.");
            } else {
                System.out.println("Item " + itemId + " not found in the library.");
            }
        }
        else if(ch == 4) {
            Library<Magazine> magazineLibrary = new Library<>();
            int n = scanner.nextInt();
            for(int i = 1; i <= n; i++) {
                int id = scanner.nextInt();
                scanner.nextLine();
                String name = scanner.nextLine();
                String author = scanner.nextLine();
                int issueno = scanner.nextInt();
                Magazine magazine = new Magazine(id, name, author, issueno);
                magazine.checkedOut = true;
                magazineLibrary.addItem(magazine);
            }
            
            int itemId = scanner.nextInt();
            if(magazineLibrary.returnItem(itemId)) {
                System.out.println("Item " + itemId + " returned successfully.");
            } else {
                System.out.println("Item " + itemId + " not found in the library.");
            }
        }
        scanner.close();
    }
}