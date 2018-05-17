package jdbc.dto;

import java.io.Serializable;

public class User extends Dto implements Serializable, Cloneable {

    private Integer userId;
    private String type;
    private String login;
    private String password;

    public User() {
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object clone() {
        User o = new User();
        o.setUserId(this.getUserId());
        o.setType(this.getType());
        o.setLogin(this.getLogin());
        o.setPassword(this.getPassword());
        return o;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof User))
            return false;
        User that = (User) o;
        return (
                compare(this.getUserId(), that.getUserId()) &&
                compare(this.getType(), that.getType()) &&
                compare(this.getLogin(), that.getLogin()) &&
                compare(this.getPassword(), that.getPassword())
                );
    }

    public int hashCode() {
        int result = SEED;
        result = hash(result, this.getUserId());
        result = hash(result, this.getType());
        result = hash(result, this.getLogin());
        result = hash(result, this.getPassword());
        return result;
    }
}
