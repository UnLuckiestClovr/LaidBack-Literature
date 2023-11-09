package store.books.Mong_DAL.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("bookitems")
public class BookItem {
    @Id
    private String id;

    private String name;
    private String author;
    private String quantity;
    private String category;
    private String price;

    public BookItem(String id, String name, String author, String quantity, String category, String price) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
    }

    // region Get/Set
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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
        System.out.println("Name: " + id
                + "\nAuthor: " + author
                + "\nQuantity: " + quantity
                + "\nCategory: " + category
                + "\nPrice: " + price);
    }
}
