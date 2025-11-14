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

class Librarian extends User{
    private String librarianID;
    public Librarian(String name, String userName, String password, String librarianID){
        super(name, userName, password);
        this.librarianID = librarianID;
    }   

    public String getLibrarianID() { 
        return librarianID; 
    }

    public void displayLibrarianMenu(Library system) {
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
                system.addNewBookOrStock();
            } else if (choice == 7) {
                system.removeBookStock();
            } else if (choice == 0) {
                loggedIn = false;
                System.out.println("Logging out...");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Member extends User{
    String memberID;
    List <readingList> memberReadingList;
    public Member(String name, String userName, String password, String memberID){
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
            System.out.println("Book successfuly stored in reading list");
            System.out.println("Current reading list = ");
            for (readingList bookWishList : memberReadingList){
                System.out.println(bookWishList.name + " Book ID: " + bookWishList.bookID + ", Status: " + bookWishList.status );
            }
        }
    }
    
    public void displayMemberMenu(Library system) {
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
                System.out.println("Enter book ID you want to borrow");
                String bookID = In.nextLine(); 

                System.out.println("Enter rent date");
                String rentDate = In.nextLine();

                System.out.println("Enter due date");
                String dueDate = In.nextLine();

                system.borrowedBook(bookID, this.memberID,rentDate,dueDate);
            } else if (choice == 7) {
                system.returnBorrowedBook(this.memberID);
            } else if (choice == 8) {
                addReadingList();
            } else if (choice == 9) {
                system.showBorrowHistory(this.memberID);
            } else if (choice == 0) {
                loggedIn = false;
                System.out.println("Logging out...");
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
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
    String bookID;
    String rentDate;
    String dueDate;
    
    public History(String recordID, String memberID, String bookID, String rentDate, String dueDate){
        this.recordID = recordID;
        this.memberID = memberID;
        this.bookID = bookID;
        this.rentDate = rentDate;
        this.dueDate = dueDate;
    }
}

class Library implements InterfaceSearch{
    private List<Book> allBooks;
    List<History> allHistory;
    List <History> borrowHistory = new ArrayList<>();

    
    // Constructor
    public Library() {
        this.allBooks = new ArrayList<>();
        this.allHistory = new ArrayList<>();
    }
    
    //Display Methods
    public void displayAllBooks() {
        System.out.println("\n--- All Books in Library ---");
        if (allBooks.isEmpty()) {
            System.out.println("There are no books in the library system.");
            return;
        }
        for (Book book : allBooks) {
            System.out.println(book.toString());
        }
    }
    
    public Book findBookById(String bookId) {
        for (Book book : allBooks) {
            if (book.getBookID().equals(bookId)) {
                return book;
            }
        }
        return null;
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
        allBooks.add(book);
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
        for (Book book : allBooks) {
            if (book.getTitle().toLowerCase().contains(titleQuery.toLowerCase())) { // Case-insensitive search
                results.add(book);
            }
        }
        return results;
    }

    @Override
    public List<Book> searchByGenre(Genre genre) {
        List<Book> results = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getGenre() == genre) {
                results.add(book);
            }
        }
        return results;
    }

    @Override
    public List<Book> searchByAuthor(String authorQuery) {
        List<Book> results = new ArrayList<>();
        for (Book book : allBooks) {
            if (book.getAuthor().toLowerCase().contains(authorQuery.toLowerCase())) { 
                results.add(book);
            }
        }
        return results;
    }

    // Adding New Books/Stock
    public void addNewBookOrStock() {
        System.out.print("Enter Book ID (e.g., B001): ");
        String id = In.nextLine();
        
        Book existingBook = findBookById(id);
        
        if (existingBook != null) {
            System.out.println("Book already exists: " + existingBook.getTitle());
            System.out.print("Enter quantity to add: ");
            try {
                int amount = In.nextInt();
                In.nextLine();
                existingBook.addQuantity(amount);
                System.out.println("Stock updated. New quantity: " + existingBook.getQuantity());
            } catch (InputMismatchException e) {
                System.out.println("Invalid number. No stock added.");
                In.nextLine(); 
            }
        } else {
            System.out.println("Adding new book...");
            System.out.print("Enter Title: ");
            String title = In.nextLine();
            System.out.print("Enter Author: ");
            String author = In.nextLine();
            Genre genre = selectGenreFromInput();
            System.out.print("Enter initial quantity: ");
            try {
                int quantity = In.nextInt();
                In.nextLine(); // consume newline
                
                Book newBook = new Book(id, title, author, genre, BookStatus.AVAILABLE, quantity);
                this.addBook(newBook);
                System.out.println("New book added successfully!");
                System.out.println(newBook.toString());
            } catch (InputMismatchException e) {
                System.out.println("Invalid number. Book not added.");
                In.nextLine(); // clear bad input
            }
        }
    }

    // Remove Book
    public void removeBookStock() {
        System.out.print("Enter Book ID to remove stock from: ");
        String id = In.nextLine();
        
        Book book = findBookById(id);
        
        if (book != null) {
            System.out.println("Current stock for '" + book.getTitle() + "': " + book.getQuantity());
            System.out.print("Enter quantity to remove: ");
            try {
                int amount = In.nextInt();
                In.nextLine(); // consume newline
                book.removeQuantity(amount);
                System.out.println("Stock updated. New quantity: " + book.getQuantity());
            } catch (InputMismatchException e) {
                System.out.println("Invalid number. No stock removed.");
                In.nextLine(); // clear bad input
            }
        } else {
            System.out.println("Book not found with ID: " + id);
        }
    }
    
   //for borrowing the books
    public boolean borrowedBook(String bookID, String memberID, String rentDate, String dueDate){
        for (Book book : allBooks ){
            if (book.getBookID().equals(bookID) && book.bookStatus == BookStatus.AVAILABLE && book.quantity >0){
                book.quantity--;
                if (book.quantity == 0){
                    book.bookStatus = BookStatus.BORROWED;
                }
                String recordID = "Record" + (borrowHistory.size() + 1);
                History record = new History(recordID, memberID,bookID, rentDate, dueDate);
                borrowHistory.add(record);
                System.out.println(book.getTitle() + "borrowed by member " + memberID);
                return true; 
            }
        }
        System.out.println("Book unavailable");
        return false;
    }

    public void showBorrowHistory(String memberID){
        System.out.println("Borrow history for member " + memberID);
        boolean anyHistory = false;

        for (History h : borrowHistory){
            if (h.memberID.equals(memberID)){
                System.out.println("Record ID = " + h.recordID + " ,Book ID = " + h.bookID + ", Rent date = " + h.rentDate +", Due date = " + h.dueDate );
                anyHistory = true;
            }
        }
        if (!anyHistory){
            System.out.println("No borrowing history");
        }
    }

    //Return borrowed book
    public boolean returnBorrowedBook(String memberID){
        System.out.println("Enter the book ID you would like to return");
        String bookID = In.nextLine();

        History borrowedRecord = null;
        for (History h : borrowHistory) {
            if (h.memberID.equals(memberID) && h.bookID.equals(bookID)) {
            borrowedRecord = h;
            break;
            }
        }
        if (borrowedRecord == null){
            System.out.println("Book was not borrowed");
            return false;
        }

        Book bookBeingReturned = null;
        for (Book b: allBooks){
            if (b.getBookID().equals(bookID)){
                bookBeingReturned = b;
                break;
            }
        }
        if (bookBeingReturned == null){
            System.out.println("Error");
            return false;
        }
        bookBeingReturned.returnCopy();
        System.out.println("Successfuly returned book!");
        return true;
    }
    
    // Registration Methods
    public static boolean isUsernameTaken(String username, List<Member> memberList, List<Librarian> librarianList) {
        for (Member m : memberList) {
            if (m.getUserName().equals(username)) {
                return true;
            }
        }
        for (Librarian l : librarianList) {
            if (l.getUserName().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public static void registerNewUser(List<Member> memberList, List<Librarian> librarianList) {
        System.out.println("\n--- New Account Registration ---");
        System.out.println("Are you a 1.Member or 2.Librarian?");
        int userType = In.nextInt();

        System.out.print("Enter your full name: ");
        String name = In.nextLine();
        System.out.print("Enter a new username: ");
        String username = In.nextLine();

        if (isUsernameTaken(username, memberList, librarianList)) {
            System.out.println("Error: This username is already taken. Please try again.");
            return;
        }

        System.out.print("Enter a new password: ");
        String password = In.nextLine();

        if (userType == 1) {
            String memberID = "M" + (memberList.size() + 1);
            Member newMember = new Member(name, username, password, memberID);
            memberList.add(newMember);
            System.out.println("Member account created successfully! Your Member ID is " + memberID);

        } else if (userType == 2) {
            String librarianID = "L" + (librarianList.size() + 1);
            Librarian newLibrarian = new Librarian(name, username, password, librarianID);
            librarianList.add(newLibrarian);
            System.out.println("Librarian account created successfully! Your Librarian ID is " + librarianID);

        } else {
            System.out.println("Invalid user type. Registration cancelled.");
        }
    }

    public static void loginUser(Library system, List<Member> memberList, List<Librarian> librarianList) {
        System.out.println("\n--- User Login ---");
        System.out.println("Are you a 1.Member or 2.Librarian?");
        int whichUser = In.nextInt();

        System.out.print("\nEnter username: ");
        String username = In.nextLine();
        System.out.print("Enter password: ");
        String password = In.nextLine();

        if (whichUser == 1) {
            Member loggedInMember = null;
            for (Member m : memberList) {
                if (m.userName.equals(username) && m.checkPassword(password)) {
                    loggedInMember = m;
                    break;
                }
            }
            if (loggedInMember != null) {
                System.out.println("Login successful!");
                loggedInMember.displayMemberMenu(system);
            } else {
                System.out.println("Invalid username/password.");
            }
        } else if (whichUser == 2) {
            Librarian loggedInLibrarian = null;
            for (Librarian l : librarianList) {
                if (l.userName.equals(username) && l.checkPassword(password)) {
                    loggedInLibrarian = l;
                    break;
                }
            }
            if (loggedInLibrarian != null) {
                System.out.println("Login successful!");
                loggedInLibrarian.displayLibrarianMenu(system);
            } else {
                System.out.println("Invalid username/password");
            }
        } else {
            System.out.println("Invalid user type.");
        }
    }

    
    public static void main(String[] args) {
        Library system = new Library();
        List<Member> memberList = new ArrayList<>();
        List<Librarian> librarianList = new ArrayList<>();

        boolean running = true;
        while (running) {
            System.out.println("\nWelcome to the Library System");
            System.out.println("1. Login");
            System.out.println("2. Register new account");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            try {
                 choice = In.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }
            
            switch (choice) {
                case 1:
                    loginUser(system, memberList, librarianList);
                    break;
                case 2:
                    registerNewUser(memberList, librarianList);
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}






