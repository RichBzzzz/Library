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

class Book{
    String bookID;
    String title;
    String author;
    Genre genre;
    double price;
    BookStatus bookStatus;
    int quantity

    public Book(String bookID, String title, String author, Genre genre, BookStatus bookStatus, int quantity)  {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.bookStatus = bookStatus;
        this.quantity = quantity;

    public String toString(){
        return "Book [ID = " + bookID + ", Title = '" + title + "', Author = '" + author + "', Genre = " + genre + ", Status = " + bookStatus + ", Quantity = " + quantity + "]";
    }
}

abstract class user{
    String name;
    String userName;
}

class librarian extends user{
    String librarianID;
    public librarian(String name, String userName, String librarianID){
        super(name, userName);
        this.librarianID = librarianID;
    }   
}

class member extends user{
    String memberID;
    public member(String name, String userName, String memberID){
        super(name, userName);
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

class record{
    String recordID;
    String memberID;
    String librarianID;
    String bookID;
    String rentDate;
    Stirng dueDate;
    public record(String recordID, String memberID, String librarianID, String bookID, String rentDate, String dueDate){
        this.recordID = recordID;
        this.memberID = memberID;
        this.liibrarianID = librarianID;
        this.bookID = bookID;
        this.rentDate = rentDate;
        this.dueDate = dueDate;
    }
}





