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
    String bookID;
    String title;
    String author;
    Genre genre;
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
    
    public String toString(){
        return "Book [ID = " + bookID + ", Title = '" + title + "', Author = '" + author + "', Genre = " + genre + ", Status = " + bookStatus + ", Quantity = " + quantity + "]";
    }
}

abstract class User{
    String name;
    String userName;
    String password;

    public User(String name, String userName, String password){
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    public boolean checkPassword(String attempt) { // Checks for password
        return this.password.equals(attempt);
    }
}

class librarian extends User{
    String librarianID;
    public librarian(String name, String userName, String password, String librarianID){
        super(name, userName, password);
        this.librarianID = librarianID;
    }   
}

class member extends User{
    String memberID;
    public member(String name, String userName, String password, String memberID){
        super(name, userName, password);
        this.memberID = memberID;
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
    String readDate;
    String progress;
    
    public readingList(String name, String memberID, String bookID, String readDate, String progress){
        this.name = name;
        this.memberID = memberID;
        this.bookID = bookID;
        this.readDate = readDate;
        this.progress = progress;
            
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

class lib implements InterfaceSearch{
    List <Book> bookList = new ArrayList<>();
     
    public void addBook (Book book){
        bookList.add(book);
    }

    //for borrowing the books
    public boolean borrowBook(String bookID){
        for (Book book : bookList ){
            if (book.bookID.equals(bookID) && book.bookStatus == BookStatus.AVAILABLE && book.quantity >0){
                book.quantity--;
                if (book.quantity == 0){
                    book.bookStatus = BookStatus.BORROWED;
                }
                System.out.println(book.title + "borrowed!");
                return true; 
            }
        }
        System.out.println("Book unavailable for borrowing.");
        return false;
    }
}

