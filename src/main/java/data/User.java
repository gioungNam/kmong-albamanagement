package data;
/**
 * 계정 class
 */
public class User {
    private String userId;
    private String nickname;
    private String password;

    public User(String userId, String nickname) {
        this.userId = userId;
        this.nickname = nickname;
    }

    public User(String userId, String nickname, String password) {
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
    }

    // getter 및 setter
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }


    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", nickname=" + nickname + ", password=" + password + "]";
    }

    
}
