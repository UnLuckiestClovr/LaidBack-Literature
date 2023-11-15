package store.books.Mong_DAL.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookstoreItem {
    @JsonProperty("_id")
    private Object _id;

    private String state;
    private String city;
    private String zipcode;
    private String address;

    public BookstoreItem() { }

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
