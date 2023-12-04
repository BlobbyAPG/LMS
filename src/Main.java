import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Main {
    public static Scanner prime = new Scanner(System.in);
    public ArrayList<User> users = new ArrayList<>();

    public ArrayList<Librarian> librarians = new ArrayList<>();
    GenreCatalog gCat = new GenreCatalog();

    public static void main(String[] args) {
        File file = new File("C:\\Users\\mido2\\IdeaProjects\\Library Management System - CSE015 Project\\bookData.txt");
        loadData(file);
        File file2 = new File("C:\\Users\\mido2\\IdeaProjects\\Library Management System - CSE015 Project\\genreData.txt");
        loadData(file2);
        File file3 = new File("C:\\Users\\mido2\\IdeaProjects\\Library Management System - CSE015 Project\\usersData.txt");
        loadData(file3);

        ArrayList<Book> bookColl = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
        String date = "August 23rd, 2004";
        Date dateEst = null;
        try {
            dateEst = formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Library library = new Library("FlashLib", "123 Sesame St.", dateEst, bookColl);
        System.out.println("" +
                "(1) Register" +
                "(2) Login");
        int choice = prime.nextInt();
        switch (choice){
            case (1) -> {
                System.out.println("" +
                        "(1) Student" +
                        "(2) Staff Member" +
                        "(3) Librarian");
                int c = prime.nextInt();
                switch(c){
                    case (1)-> {
                        System.out.println("Please enter your Name, E-mail, Phone Number, and Password");
                        try{
                            String name = prime.next();
                            String email = prime.next();
                            String phoneNumber = prime.next();
                            String password = prime.next();
                            Student student = new Student(name, email, phoneNumber, password);
                            users.add(student);
                            transactionMenu1();
                        } catch(InputMismatchException ime){
                            ime.printStackTrace();
                        }
                    }
                    case(2) -> {
                        System.out.println("Please enter your Name, E-mail, Phone Number, and Password");
                        try{
                            String name = prime.next();
                            String email = prime.next();
                            String phoneNumber = prime.next();
                            String password = prime.next();
                            Staff staff = new Staff(name, email, phoneNumber, password);
                            users.add(staff);
                            transactionMenu1();
                        } catch(InputMismatchException ime){
                            ime.printStackTrace();
                        }
                    }
                    case(3) -> {
                        System.out.println("Please enter your Name, E-mail, Phone Number, and Password");
                        try{
                            String name = prime.next();
                            String email = prime.next();
                            String phoneNumber = prime.next();
                            String password = prime.next();
                            Librarian librarian = new Librarian(name, email, phoneNumber, password);
                            librarians.add(librarian);
                            transactionMenu1();
                        } catch(InputMismatchException ime){
                            ime.printStackTrace();
                        }
                    }
                }
            }
            case(2) ->{
                System.out.println("Are you a:" +
                        "(1) Student" +
                        "(2) Staff Member" +
                        "(3) Librarian");
                int c = prime.nextInt();
                switch(c){
                    case (1), (2)-> {
                        checkCredentials1(users);
                        transactionMenu1(user, library, bookColl, date);
                    }
                    case(3) ->{
                        checkCredentials2(librarians);
                        transactionMenu2();
                    }
                }
            }
        }
        saveData(file);
    }

    private void checkCredentials1(ArrayList<User> users) {
        for (User user : users){
            System.out.println("Please enter your email and password.");
            String email = prime.next();
            String password = prime.next();
            if (user.getPassword().contains(password) && user.getEmail().contains(email)){

                break;
            } else if ((user.getPassword().contains(password) && !user.getEmail().contains(email)) ||
                    (!user.getPassword().contains(password) && user.getEmail().contains(email))) {
                System.out.println("Wrong email or password please try again.");
                checkCredentials1(users);
            } else {
                throw new IllegalArgumentException("Sorry, can't find what you're looking for.");
            }
        }
    }
    private void checkCredentials2(ArrayList<Librarian> librarians) {
        for (Librarian librarian : librarians){
            System.out.println("Please enter your email and password.");
            String email = prime.next();
            String password = prime.next();
            if (librarian.getPassword().contains(password) && librarian.getEmail().contains(email)){

                break;
            } else if ((librarian.getPassword().contains(password) && !librarian.getEmail().contains(email)) ||
                    (!librarian.getPassword().contains(password) && librarian.getEmail().contains(email))) {
                System.out.println("Wrong email or password please try again.");
                checkCredentials2(librarians);
            } else {
                throw new IllegalArgumentException("Sorry, can't find what you're looking for.");
            }
        }
    }

    private void transactionMenu2(Librarian librarian, Library library, ArrayList<Book> bookColl, Date date) {
        System.out.println("What would you like to do?" +
                "(1) Sort books" +
                "(2) Search for a book" +
                "(3) View genres/genre catalog" +
                "(4) View Account");
        int c = prime.nextInt();
        switch(c){
            case(1) ->{
                System.out.println("How would you like to sort the books?" +
                        "(1) By Title\n" +
                        "(2) By Author\n" +
                        "(3) By PubDate\n" +
                        "(4) By Genre\n" +
                        "(5) By Publisher\n" +
                        "(6) By NbOfPages");
                int b = prime.nextInt();
                switch (b){
                    case (1) -> library.sort(bookColl, Library.SortType.Title);
                    case (2) -> library.sort(bookColl, Library.SortType.Author);
                    case (3) -> library.sort(bookColl, Library.SortType.PubDate);
                    case (4) -> library.sort(bookColl, Library.SortType.Genre);
                    case (5) -> library.sort(bookColl, Library.SortType.Publisher);
                    case (6) -> library.sort(bookColl, Library.SortType.NbOfPages);

                }
            }
            case(2) ->{
                System.out.println("What book would you like to search for?");
                String query = prime.next();
                library.search(bookColl, query);
            }
            case (3) ->{
                System.out.println("What would you like to do?" +
                        "(1) View Genre Catalog" +
                        "(2) Add to Genre Catalog" +
                        "(3) Remove from Genre Catalog" +
                        "(4) Edit a Genre");
                int a = prime.nextInt();
                switch (a){
                    case(1) ->{
                        System.out.println("Genre Catalog: " + gCat.toString());
                    }
                    case(2) ->{
                        System.out.println("What would you like to add?");
                        String name = prime.next();
                        System.out.println("Please enter a description");
                        String desc = prime.next();
                        Genre genre = new Genre(name, desc);
                        gCat.addGenre(genre);
                    }
                    case(3) ->{
                        System.out.println("What would you like to remove?");
                        String name = prime.next();
                        gCat.removeGenre(gCat, name);
                    }
                    case(4) ->{
                        System.out.println("What would you like to change: " +
                                "(1) Edit genre name" +
                                "(2) Edit genre description");
                        int z = prime.nextInt();
                        switch (z){
                            case(1) ->{
                                System.out.println("Please enter your change.");
                                String change = prime.next();
                                gCat.updateGenre();
                            }
                        }
                    }
                }
            }
        }
    }

    private void transactionMenu1(User user, Library library, ArrayList<Book> bookColl, Date date) {
        System.out.println("What would you like to do?" +
                "(1) Borrow a book" +
                "(2) Return a book" +
                "(3) Search for a book" +
                "(4) View Account");
        int c = prime.nextInt();
        switch(c){
            case(1) ->{
                System.out.println("What book would you like to borrow?");
                String book = prime.next();
                String dateToday = String.valueOf(date);
                library.borrowBook(bookColl, user, dateToday, book);
                Date newDate = new Date(date.getTime()+ 14 * 24 * 60 * 60 * 1000);
                System.out.println("Return the book by: " + newDate);
            }
            case(2) ->{
                System.out.println("What book would you like to return?");
                String book = prime.next();
                library.returnBook(bookColl, user, book);
            }
            case (3) ->{
                System.out.println("What book would you like to search for?");
                String query = prime.next();
                library.search(bookColl, query);
            }
            case(4) ->{
                System.out.println("Books borrowed : " + user.getBorrowedBooks());
                System.out.println("Books returned: " + user.getReturnedBooks());
                System.out.println("Fines: " + user.getFine());
            }
        }
    }

    public static void loadData(File file) {
        ArrayList<Book> books = new ArrayList<>();
        try {
            Scanner Reader = new Scanner(file);
            while (Reader.hasNextLine())
            {
                String bookData = Reader.nextLine();
                String book[] = bookData.split(" ");
                Book testlibbook = new Book(book[0], book[1], book[2], book[3] + " ", book[4], book[5],
                        Integer.parseInt(book[6]), Integer.parseInt(book[7]), book[8]);
                books.add(testlibbook);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void saveData(File file, ArrayList<Book> libBooks, ArrayList<Librarian> librarians) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            try {
                FileWriter booksDataWriter = new FileWriter(file);
                booksDataWriter.write(libBooks.get(0).getTitle() + " " + libBooks.get(0).getAuthor()+ " " +
                        libBooks.get(0).getISBN() + " " +libBooks.get(0).getPubDate() + " " + libBooks.get(0).getGenre() + " " +
                        libBooks.get(0).getNbOfCopies() + " " + libBooks.get(0).isAvailable() + " ");
                for(int i = 1; i < librarians.size(); i++)
                {
                    booksDataWriter.write("\n" + libBooks.get(i).getTitle() + " " + libBooks.get(i).getAuthor()+ " " +
                            libBooks.get(i).getISBN() + " " +libBooks.get(i).getPubDate() + " " + libBooks.get(i).getGenre() + " " +
                            libBooks.get(i).getNbOfCopies() + " " + libBooks.get(i).isAvailable() + " ");
                }
                booksDataWriter.close();
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }


}