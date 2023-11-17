package store.books.Mong_DAL.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("_id")
    private Object id;
    String fullName;
    String username;
    String encPS;
    String accountStatus;

    public User() {
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {return fullName;}

    public void setFullName(String fullName) {this.fullName = fullName;}

    public String getEncPS() {
        return encPS;
    }

    public void setEncPS(String encPS) {
        this.encPS = encPS;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }
}
