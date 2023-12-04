import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Library{
    private String name;
    private String address;
    private Date dateEst;
    private ArrayList<Book> books;
    private int nbOfBooks = books.size();

    public Library(String name, String address, Date dateEst, ArrayList<Book> books) {
        this.name = name;
        this.address = address;
        this.dateEst = dateEst;
        this.books = books;
    }

    public void borrowBook(ArrayList<Book> books, User borrower, String date, String query){
        ArrayList<Book> bookResults = search(books, query);
        Book book = bookResults.get(0);
        if (bookResults.size() == 1){
            if(book.isAvailable() && book.getNbOfCopies() > 1 && !borrower.hasReachedMaxNbOfBorrows()){
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
                Date dateBorrowed = null;
                try {
                    dateBorrowed = formatter.parse(date);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                book.setBorrower(borrower);
                book.setDateBorrowed(dateBorrowed);
                nbOfBooks--;
                book.nbOfCopies--;
                borrower.addBorrowedBook(book);
                Date newDate = new Date(dateBorrowed.getTime()+ 14 * 24 * 60 * 60 * 1000);
                book.setDueDate(newDate);
            } else {
                System.out.println("Sorry book isn't available.");
            }
        } else{
            System.out.println("Sorry, book isn't available.");
        }

    }

    public void returnBook(ArrayList<Book> books, User borrower, String query){
        ArrayList<Book> bookResults = search(books, query);
        Book book = bookResults.get(0);
        if ((book.getDateBorrowed()).before(book.getDueDate())){
            book.setBorrower(null);
            book.setDateBorrowed(null);
            book.setDueDate(null);
            book.setAvailable(true);
            book.nbOfCopies++;
            borrower.removeBorrowedBook(book);
            nbOfBooks++;
        } else{
            borrower.setFine(0.1 * book.getPrice());
            System.out.println("You have exceeded the deadline. Please pay a fine of " + borrower.getFine());
        }
    }
    public ArrayList<Book> search(ArrayList<Book> bookCollection, String query) {
        ArrayList<Book> results = new ArrayList<>();
        for (Book book : bookCollection){
            if (book.getAuthor().contains(query) || book.getTitle().contains(query) ||
                    book.getGenre().getName().contains(query) || book.getISBN().contains(query) ||
                    book.getPubDate().contains(query) || book.getPublisher().contains(query)){
                System.out.println(book.toString());
            } else {
                throw new IllegalArgumentException("Sorry, can't find what you're looking for.");
            }
        }
        return results;
    }

    public void printBooks(){
        for (Book book : books){
            System.out.println(book.toString());
        }
    }

    public enum SortType{
        Title,
        Author,
        PubDate,
        Genre,
        Publisher,
        NbOfPages
    }
    public void sort(ArrayList<Book> books, SortType sortType){
        List<Book> results;
        switch (sortType){
            case Title -> sortByTitle(books);
            case Author -> sortByAuthor(books);
            case PubDate -> sortByPublicationDate(books);
            case Genre -> sortByGenre(books);
            case Publisher -> sortByPublisher(books);
            case NbOfPages -> sortByNumberOfPages(books);
            default -> throw new IllegalStateException("Unexpected value: " + sortType);
        }
    }
    public static void sortByPublisher(List<Book> books) {
        List<Book> sortedBooks = new ArrayList<>();
        List<String> publishers = new ArrayList<>();
        for (Book book : books) {
            String publisher = book.getPublisher();
            if (!publishers.contains(publisher)) {
                publishers.add(publisher);
            }
        }
        for (String publisher : publishers) {
            for (Book book : books) {
                if (book.getPublisher().equals(publisher)) {
                    sortedBooks.add(book);
                }
            }
        }
        books.clear();
        books.addAll(sortedBooks);
    }
    private static void sortByGenre(List<Book> books) {
        List<List<Book>> sortedLists = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            sortedLists.add(new ArrayList<>());
        }
        for (Book book : books) {
            char firstLetter = book.getGenre().getName().toLowerCase().charAt(0);
            sortedLists.get(firstLetter - 'a').add(book);
        }
        books.clear();
        for (List<Book> list : sortedLists) {
            books.addAll(list);
        }
    }
    private static void sortByTitle(List<Book> books) {
        List<String> titles = new ArrayList<>();
        for (Book book : books) {
            titles.add(book.getTitle());
        }
        titles.sort(String::compareToIgnoreCase);
        List<Book> sortedBooks = new ArrayList<>();
        for (String title : titles) {
            for (Book book : books) {
                if (book.getTitle().equalsIgnoreCase(title)) {
                    sortedBooks.add(book);
                    break;
                }
            }
        }
        books.clear();
        books.addAll(sortedBooks);
    }
    private static void sortByAuthor(List<Book> books) {
        List<String> authors = new ArrayList<>();
        for (Book book : books) {
            authors.add(book.getAuthor());
        }
        authors.sort(String::compareToIgnoreCase);
        List<Book> sortedBooks = new ArrayList<>();
        for (String author : authors) {
            for (Book book : books) {
                if (book.getAuthor().equalsIgnoreCase(author)) {
                    sortedBooks.add(book);
                    break;
                }
            }
        }
        books.clear();
        books.addAll(sortedBooks);
    }
    private static void sortByPublicationDate(List<Book> books) {
        List<Date> publicationDates = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Book book : books) {
            try {
                Date date = dateFormat.parse(String.valueOf(book.getPubDate()));
                publicationDates.add(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        Collections.sort(publicationDates);
        List<Book> sortedBooks = new ArrayList<>();
        for (Date date : publicationDates) {
            for (Book book : books) {
                if (book.getPubDate().equals(dateFormat.format(date))) {
                    sortedBooks.add(book);
                    break;
                }
            }
        }
        books.clear();
        books.addAll(sortedBooks);
    }
    private static void sortByNumberOfPages(List<Book> books) {
        for (int i = 0; i < books.size() - 1; i++) {
            for (int j = 0; j < books.size() - i - 1; j++) {
                if (books.get(j).getNbOfPages() > books.get(j + 1).getNbOfPages()) {
                    Book temp = books.get(j);
                    books.set(j, books.get(j + 1));
                    books.set(j + 1, temp);
                }
            }
        }
    }

}
