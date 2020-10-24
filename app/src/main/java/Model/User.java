package Model;

public class User {

    private long ID;
    private String FirstName;
    private String LastName;
    private String PassData;
    private String Phone;
    private String Email;
    private String DateBirth;
    private String PoliceNum;
    private String Login;
    private String Password;

    public User(long ID, String firstName, String lastName, String passData, String phone, String email, String dateBirth, String policeNum, String login, String password) {
        this.ID = ID;
        FirstName = firstName;
        LastName = lastName;
        PassData = passData;
        Phone = phone;
        Email = email;
        DateBirth = dateBirth;
        PoliceNum = policeNum;
        Login = login;
        Password = password;
    }

    public long getID() {
        return ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPassData() {
        return PassData;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getDateBirth() {
        return DateBirth;
    }

    public String getPoliceNum() {
        return PoliceNum;
    }

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return Password;
    }
}
