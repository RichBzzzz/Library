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

    public Book(String bookID, String title, String author, Genre genre, double price, BookStatus bookStatus)  {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.bookStatus = bookStatus;
        this.quantity = quantity;
    }
}

class user{
    String name;
    String userName;
}

class librarian extends user{
    String librarianID;
    public librararian(String name, String userName, String librarianID){
        super(name, userName);
        this.librarianID = librarianID;
    }   
}

class member extends User{
    String memberID;
    public member(String name, String userName, String memberID){
        super(name, userName);
        this.memberID = memberID;
    }
}
