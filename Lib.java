import java.util.*;

enum Genre {
    HORROR,
    FANTASY,
    ROMANCE,
    SELF_HELP,
    BIOGRAPHY, 
}

enum BookStatus {
    AVAILABLE,
    BORROWED
}

interface InterfaceSearch {
    List<Book> searchByTitle(String titleQuery);
    List<Book> searchByGenre(Genre genre);
    List<Book> searchByAuthor(String authorQuery);
}

class Book {
    private String bookID;
    private String title;
    private String author;
    private Genre genre;
    BookStatus bookStatus;
    int quantity;

    public Book(String bookID, String title, String author, Genre genre, BookStatus bookStatus, int quantity)  {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookStatus = bookStatus;
        this.quantity = quantity;
    }
    
    public void updateStatus() {
        if (this.quantity > 0) {
            this.bookStatus = BookStatus.AVAILABLE;
        } else {
            this.bookStatus = BookStatus.BORROWED;
        }
    }
    
    // --- Getters ---
    public String getBookID() { 
        return bookID; 
    }

    public String getTitle() { 
        return title; 
    }

    public String getAuthor() { 
        return author; 
    }

    public Genre getGenre() { 
        return genre; 
    }

    public BookStatus getStatus() {
        return bookStatus; 
    }

    public int getQuantity() { 
        return quantity; 
    }
    
    public void borrowCopy() {
        if (this.quantity > 0) {
            this.quantity--;
        }
        updateStatus();
    }
    
    public void returnCopy() {
        this.quantity++;
        updateStatus();
    }

    public void addQuantity(int amount) {
        if (amount > 0) {
            this.quantity += amount;
            updateStatus();
        }
    }
    
    public void removeQuantity(int amount) {
        if (amount > 0) {
            this.quantity -= amount;
            if (this.quantity < 0) {
                this.quantity = 0;
            }
            updateStatus();
        }
    }
    
    public String toString(){
        return "Book [ID = " + bookID + ", Title = '" + title + "', Author = '" + author + "', Genre = " + genre + ", Status = " + bookStatus + ", Quantity = " + quantity + "]";
    }
}

abstract class User{
    protected String name;
    protected String userName;
    protected String password;

    public User(String name, String userName, String password){
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public String getName(){
        return name;
    }

    public String getUserName(){
        return userName;
    }
    
    public boolean checkPassword(String attempt) { // Checks for password
        return this.password.equals(attempt);
    }
}

class librarian extends User{
    private String librarianID;
    public librarian(String name, String userName, String password, String librarianID){
        super(name, userName, password);
        this.librarianID = librarianID;
    }   

    public String getLibrarianID() { 
        return librarianID; 
    }

    public void displayMenu(Library system) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- Librarian Menu ---");
            System.out.println("1. List all books");
            System.out.println("2. View book details");
            System.out.println("3. Search by author");
            System.out.println("4. Search by genre");
            System.out.println("5. Search by title");
            System.out.println("6. Add a new book / Add stock");
            System.out.println("7. Remove book stock");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = In.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 1) {
                system.displayAllBooks();
            } else if (choice == 2) {
                system.viewBookDetails();
            } else if (choice == 3) {
                system.searchAndDisplayByAuthor();
            } else if (choice == 4) {
                system.searchAndDisplayByGenre();
            } else if (choice == 5) {
                system.searchAndDisplayByTitle();
            } else if (choice == 6) {
                ;
            } else if (choice == 7) {
                ;
            } else if (choice == 8) {
                ;
            } else if (choice == 0) {
                loggedIn = false;
                System.out.println("Logging out...");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class member extends User{
    String memberID;
    List <readingList> memberReadingList;
    public member(String name, String userName, String password, String memberID){
        super(name, userName, password);
        this.memberID = memberID;
        this.memberReadingList = new ArrayList<>();
    }

    public void addReadingList(){
        boolean addMore = true;
        while (addMore){
            System.out.println("Enter the book title you want to add in your reading list");
            String title = In.nextLine();
            System.out.println("Enter the book ID");
            String bookID = In.nextLine();
            String status = "To read";

            readingList book = new readingList(title, memberID, bookID, status);
            memberReadingList.add(book);

            System.out.println("Would you like to add another book? (y/n)");
            String yesNo = In.nextLine();
            if (yesNo.equalsIgnoreCase("no")){
                addMore = false;
            }
            System.out.println("Book successfuly stored in reading list.\n");
            System.out.println("Current reading list = ");
            for (readingList bookWishList : memberReadingList){
                System.out.println(bookWishList.name + " Book ID: " + book.bookID + ", Status: " + book.status + ")");
            }
        }
    }
    public void displayMenu(Library system) {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- Member Menu ---");
            System.out.println("Welcome, " + this.name + "!");
            System.out.println("1. List all books");
            System.out.println("2. View book details");
            System.out.println("3. Search by author");
            System.out.println("4. Search by genre");
            System.out.println("5. Search by title");
            System.out.println("6. Borrow a book");
            System.out.println("7. Return a book");
            System.out.println("8. Reading wish list");
            System.out.println("9. Borrowing history");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                choice = In.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            if (choice == 1) {
                system.displayAllBooks();
            } else if (choice == 2) {
                system.viewBookDetails();
            } else if (choice == 3) {
                system.searchAndDisplayByAuthor();
            } else if (choice == 4) {
                system.searchAndDisplayByGenre();
            } else if (choice == 5) {
                system.searchAndDisplayByTitle();
            } else if (choice == 6) {
                ;
            } else if (choice == 7) {
                ;
            } else if (choice == 8) {
                ;
            } else if (choice == 9) {
                ;
            } else if (choice == 0) {
                loggedIn = false;
                System.out.println("Logging out...");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
     }       
}

class bill{
    String billID;
    String date;
    String memberID;
    String librarianID;
    double amount;
    
    public bill(String billID, String date, String memberID, String librarianID, double amount){
        this.billID = billID;
        this.date = date;
        this.memberID = memberID;
        this.librarianID = librarianID;
        this.amount = amount;
    }
}

class readingList{
    String name;
    String memberID;
    String bookID;
    String status;
    
    public readingList(String name, String memberID, String bookID, String status){
        this.name = name;
        this.memberID = memberID;
        this.bookID = bookID;
        this.status = status;
            
    }
}

class History{
    String recordID;
    String memberID;
    String librarianID;
    String bookID;
    String rentDate;
    String dueDate;
    
    public History(String recordID, String memberID, String librarianID, String bookID, String rentDate, String dueDate){
        this.recordID = recordID;
        this.memberID = memberID;
        this.librarianID = librarianID;
        this.bookID = bookID;
        this.rentDate = rentDate;
        this.dueDate = dueDate;
    }
}

class Library implements InterfaceSearch{
    List <Book> bookList = new ArrayList<>();
    Map<String, Book> bookMap;
    Map<String, User> userMap;     
    List<History> allHistory;
    List<bill> allBills;
    List <History> borrowHistory = new ArrayList<>();

    
    // Constructor
    public Library() {
        this.bookMap = new HashMap<>();
        this.userMap = new HashMap<>();
        this.allHistory = new ArrayList<>();
        this.allBills = new ArrayList<>();
    }
    
    //Display Methods
    public void displayAllBooks() {
        System.out.println("\n--- All Books in Library ---");
        for (Book book : bookMap.values()) {
            System.out.println(book.toString());
        }
    }
    
    public Book findBookById(String bookId) {
        return bookMap.get(bookId);
    }

    public void viewBookDetails() {
        System.out.print("Enter Book ID to view details: ");
        String bookId = In.nextLine();
        Book book = findBookById(bookId);
        if (book != null) {
            System.out.println("--- Book Details ---");
            System.out.println(book.toString());
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }
    }

    public void addBook (Book book){
        bookList.add(book);
    }

    // Search Methods
    public void searchAndDisplayByTitle() {
        System.out.print("Enter title to search for: ");
        String query = In.nextLine();
        List<Book> results = searchByTitle(query);
        System.out.println("\nSearch Results (" + results.size() + " found)");
        if (results.isEmpty()) {
            System.out.println("No books found matching that title.");
        } else {
            for (Book book : results) {
                System.out.println(book.toString());
            }
        }
    }
    
    public void searchAndDisplayByAuthor() {
        System.out.print("Enter author to search for: ");
        String query = In.nextLine();
        List<Book> results = searchByAuthor(query);
        System.out.println("\nSearch Results (" + results.size() + " found)");
        if (results.isEmpty()) {
            System.out.println("No books found by that author.");
        } else {
            for (Book book : results) {
                System.out.println(book.toString());
            }
        }
    }
    
    public void searchAndDisplayByGenre() {
        System.out.println("Select a Genre to filter by:");
        Genre genre = selectGenreFromInput();
        
        List<Book> results = searchByGenre(genre);
        System.out.println("\nSub-collection for " + genre.name() + " (" + results.size() + " found)");
        if (results.isEmpty()) {
            System.out.println("No books found in this genre.");
        } else {
            for (Book book : results) {
                System.out.println(book.toString());
            }
        }
    }

    // Helper Methods
    private Genre selectGenreFromInput() {
        while (true) {
            System.out.println("Available Genres:");
            for (Genre g : Genre.values()) {
                System.out.print(g.name() + "  ");
            }
            System.out.println();
            System.out.print("Enter genre name: ");
            String genreInput = In.nextLine();
            try {
                Genre genre = Genre.valueOf(genreInput.toUpperCase());
                return genre; 
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid genre name. Please try again.");
            }
        }
    }
    
    // Interface Methods 
    @Override
    public List<Book> searchByTitle(String titleQuery) {
        List<Book> results = new ArrayList<>();
        // Search the map's values
        for (Book book : bookMap.values()) {
            if (book.title.toLowerCase().contains(titleQuery.toLowerCase())) { //toLowerCase for case-insensitive search
                results.add(book);
            }
        }
        return results;
    }

    @Override
    public List<Book> searchByGenre(Genre genre) {
        List<Book> results = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.genre == genre) {
                results.add(book);
            }
        }
        return results;
    }

    @Override
    public List<Book> searchByAuthor(String authorQuery) {
        List<Book> results = new ArrayList<>();
        for (Book book : bookMap.values()) {
            if (book.author.toLowerCase().contains(authorQuery.toLowerCase())) { //toLowerCase for case-insensitive search
                results.add(book);
            }
        }
        return results;
    }
    
   //for borrowing the books
    public boolean borrowedBook(String bookID, String memberID, String librarianID, String rentDate, String dueDate){
        for (Book book : bookList ){
            if (book.bookID.equals(bookID) && book.bookStatus == BookStatus.AVAILABLE && book.quantity >0){
                book.quantity--;
                if (book.quantity == 0){
                    book.bookStatus = BookStatus.BORROWED;
                }
                String recordID = "Record" + (borrowHistory.size() + 1);
                History record = new History(recordID, memberID, librarianID, bookID, rentDate, dueDate);
                borrowHistory.add(record);
                System.out.println(book.title + "borrowed by member " + memberID);
                return true; 
            }
        }
        System.out.println("Book unavailable for borrowing.");
        return false;
    }

    public void showBorrowHistory(String bookID, String memberID, String librarianID, String rentDate, String dueDate, String recordID){
        System.out.println("Borrow history for member " + memberID);
        boolean anyHistory = false;

        for (History h : borrowHistory){
            if (h.memberID.equals(memberID)){
                System.out.println("Record ID = " + recordID + " ,Book ID = " + bookID + ", Rent date = " + rentDate +", Due date = " + dueDate );
                anyHistory = true;
            }
        }
        if (!anyHistory){
            System.out.println("No borrowing history");
        }
    }
    
    public static void main(String[] args) {
        List<member> memberList = new ArrayList<>();
        List<librarian> librarianList = new ArrayList<>();
        System.out.println("Welcome to the library system");
        System.out.println("Are you a (1) Member or (2) Librarian?");
        int whichUser = In.nextInt();
        
        System.out.print("Enter username: ");
        String username = In.nextLine();
        System.out.print("Enter password: ");
        String password = In.nextLine();
    }
}



