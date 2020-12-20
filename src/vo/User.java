package vo;

public class User {
    private String userName;
    private String chrName;
    private String password;
    private String role;

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String userName, String chrName, String password, String role) {
        super();
        this.userName = userName;
        this.chrName = chrName;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getChrName() {
        return chrName;
    }
    public void setChrName(String chrName) {
        this.chrName = chrName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [userName=" + userName + ", chrName=" + chrName
                + ", password=" + password + ", role=" + role + "]";
    }
}
