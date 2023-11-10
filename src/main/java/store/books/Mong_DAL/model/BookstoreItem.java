package store.books.Mong_DAL.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookstoreItem {
    @JsonProperty("_id")
    private int _id = 0;

    private String name;
    private String Address;

    public BookstoreItem() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
