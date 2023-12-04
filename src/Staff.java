import java.util.ArrayList;

public class Staff extends User{
    private int idEnd = 1000;
    private final String idStart = "ST";
    private String ID;
    private ArrayList<Book> borrowedBooks;
    public double fine;

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public Staff(String name, String email, String phoneNo, String password) {
        super(name, email, phoneNo, password);
        this.ID = generateID();
    }
    public String generateID(){
        idEnd++;
        return String.format(idStart + "%04d", idEnd);
    }

    public void setID(String ID){
        this.ID = ID;
    }

    public String getID(){
        return ID;
    }
}
