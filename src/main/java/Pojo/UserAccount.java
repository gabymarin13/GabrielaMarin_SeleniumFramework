package Pojo;

public class UserAccount {
    private String email = "";
    private String password = "";
    private boolean userExists;

    public UserAccount(String email, String password, boolean userExists){
        this.setEmail(email);
        this.setPassword(password);
        this.setUserExists(userExists);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean userExistsMethod() {
        return this.userExists;
    }

    public void setUserExists(boolean _userExists) {
        this.userExists = _userExists;
    }
}
