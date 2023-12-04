import java.util.ArrayList;

public class User {
    private String name;
    private String email;
    private String phoneNo;
    private String cardNumber;
    private static int lastCardNumber = 1000;
    public final int maxNbOfBorrows = 5;
    private String password;
    public ArrayList<Book> borrowedBooks;
    public ArrayList<Book> returnedBooks;
    public double fine;

    public ArrayList<Book> getReturnedBooks(){
        return returnedBooks;
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public String getPassword(){
        return password;
    }
    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public boolean hasReachedMaxNbOfBorrows(){
        return borrowedBooks.size() >= maxNbOfBorrows;
    }
    public void addBorrowedBook(Book book){
        borrowedBooks.add(book);
    }
    public void removeBorrowedBook(Book book){
        borrowedBooks.remove(book);
        returnedBooks.add(book);
    }

    public User(String name, String email, String phoneNo, String password) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.cardNumber = generateCardNumber();
    }

    public String generateCardNumber(){
        lastCardNumber++;
        return String.format("%04d", lastCardNumber);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                '}';
    }
}
