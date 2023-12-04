import java.util.Date;

public class Book{
    private String title;
    private String author;
    private String ISBN;
    private boolean available;
    private String pubDate;
    private Genre genre;
    private int nbOfPages;
    protected int nbOfCopies;
    private String publisher;
    public User borrower;
    private Date dateBorrowed;
    private Date dueDate;
    public double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Book(String title, String author, String ISBN, boolean available, String pubDate, Genre genre, int nbOfPages, int nbOfCopies, String publisher) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.available = available;
        this.pubDate = pubDate;
        this.genre = genre;
        this.nbOfPages = nbOfPages;
        this.nbOfCopies = nbOfCopies;
        this.publisher = publisher;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public int getNbOfPages() {
        return nbOfPages;
    }

    public void setNbOfPages(int nbOfPages) {
        this.nbOfPages = nbOfPages;
    }

    public int getNbOfCopies() {
        return nbOfCopies;
    }

    public void setNbOfCopies(int nbOfCopies) {
        this.nbOfCopies = nbOfCopies;
    }

    public User getBorrower() {
        return borrower;
    }

    public void setBorrower(User borrower) {
        this.borrower = borrower;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getDateBorrowed(){
        return dateBorrowed;
    }

    public void setDateBorrowed(Date dateBorrowed){
        this.dateBorrowed = dateBorrowed;
    }

    public Date getDueDate(){
        return dueDate;
    }

    public void setDueDate(Date dueDate){
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", available=" + available +
                ", pubDate=" + pubDate +
                ", genre=" + genre +
                ", nbOfPages=" + nbOfPages +
                ", nbOfCopies=" + nbOfCopies +
                ", publisher='" + publisher + '\'' +
                ", borrower='" + borrower + '\'' +
                ", dateBorrowed=" + dateBorrowed +
                ", dueDate=" + dueDate +
                '}';
    }
}
