package store.books.Mong_DAL.model;


public class BookItem {
    private String id;

    private String name;
    private String author;
    private String description;
    private String category;
    private String price;

    public BookItem(String id, String name, String author, String quantity, String category, String price) {
        super();
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = quantity;
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
        System.out.println("Name: " + id
                + "\nAuthor: " + author
                + "\nDescription: " + description
                + "\nCategory: " + category
                + "\nPrice: " + price);
    }
}
