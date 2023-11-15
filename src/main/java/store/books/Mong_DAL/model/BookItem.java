package store.books.Mong_DAL.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookItem {
    @JsonProperty("_id")
    private Object _id;

    private String name;
    private String author;
    private String description;
    private String category;
    private String price;

    public BookItem() {}

    // region Get/Set


    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    //endregion

    public void printString() {
        System.out.println("Name: " + name
                + "\nAuthor: " + author
                + "\nDescription: " + description
                + "\nCategory: " + category
                + "\nPrice: " + price);
    }
}
