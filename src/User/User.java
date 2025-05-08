package User;

public class User {
    private String userId;
    private String password;
    
    //无参构造
    public User() {}
    //有参构造
    public User(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    //getters
    public String getUserId() {
        return userId;
    }
    public String getPassword() {
        return password;
    }

}
