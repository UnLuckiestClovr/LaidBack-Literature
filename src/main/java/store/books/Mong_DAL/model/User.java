package store.books.Mong_DAL.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    @JsonProperty("_id")
    private Object id;

    private String userFullName;
    private String username;
    private String encPS;

    public User() {
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
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
}
