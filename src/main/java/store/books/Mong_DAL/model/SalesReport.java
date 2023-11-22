package store.books.Mong_DAL.model;

public class SalesReport {
    private Object _id;
    private int year;
    private String month;
    private int bookstoreID;
    private int bookSales;

    public Object get_id() {
        return _id;
    }

    public void set_id(Object _id) {
        this._id = _id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getBookstoreID() {
        return bookstoreID;
    }

    public void setBookstoreID(int bookstoreID) {
        this.bookstoreID = bookstoreID;
    }

    public int getBookSales() {
        return bookSales;
    }

    public void setBookSales(int bookSales) {
        this.bookSales = bookSales;
    }
}
