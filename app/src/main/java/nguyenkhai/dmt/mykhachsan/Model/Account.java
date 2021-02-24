package nguyenkhai.dmt.mykhachsan.Model;

import java.io.Serializable;

public class Account implements Serializable {

    private String userName;
    private String email;
    private String password;

    public Account() {
    }

    public Account(String userName, String email,String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
