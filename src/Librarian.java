public class Librarian{
    private String name;
    private String email;
    private String phoneNo;
    private String password;
    private int  idEnd = 1000;
    private final String idStart = "LIB";
    private String ID;

    public Librarian(String name, String email, String phoneNo, String password) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.password = password;
        this.ID = generateID();
    }
    public String generateID(){
        idEnd++;
        return String.format(idStart + "%04d", idEnd);
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID(){
        return ID;
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", password='" + password + '\'' +
                ", idEnd=" + idEnd +
                ", idStart='" + idStart + '\'' +
                ", ID='" + ID + '\'' +
                '}';
    }
}
