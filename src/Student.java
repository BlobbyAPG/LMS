import java.util.ArrayList;

public class Student extends User{
    private int idEnd = 1000;
    private final String idStart = "SD";
    private String ID;
    private ArrayList<Book> borrowedBooks;
    public double fine;

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public Student(String name, String email, String phoneNo, String password) {
        super(name, email, phoneNo, password);
        this.ID = generateID();
    }
    public String generateID(){
        idEnd++;
        return String.format(idStart + "%04d", idEnd);
    }

    public void setIdEnd(int idEnd){
        this.idEnd = idEnd;
    }

    public int getIdEnd(){
        return idEnd;
    }

}
