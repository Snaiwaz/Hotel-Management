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

    //getters和setter
    public String getUserId() {
        return userId;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

}
