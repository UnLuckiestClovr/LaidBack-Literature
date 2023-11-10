package store.books.Mong_DAL.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookstoreItem {
    @JsonProperty("_id")
    private int _id = 0;

    private String State;
    private String City;
    private String Zipcode;
    private String Address;
    private String AdminPassword;

    public BookstoreItem() { }

    public int get_Id() {
        return _id;
    }

    public void set_Id(int _id) {
        this._id = _id;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getZipcode() {
        return Zipcode;
    }

    public void setZipcode(String zipcode) {
        Zipcode = zipcode;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAdminPassword() {
        return AdminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        AdminPassword = adminPassword;
    }
}
