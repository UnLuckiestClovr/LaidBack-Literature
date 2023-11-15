package store.books.Mong_DAL.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("_id")
    private int id;

    String username;
    String encPS;
    String accountStatus;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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
