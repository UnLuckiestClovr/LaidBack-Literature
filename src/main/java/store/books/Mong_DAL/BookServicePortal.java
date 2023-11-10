package store.books.Mong_DAL;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.books.Mong_DAL.model.BookItem;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServicePortal {
    private static final String databaseAddress = "mongodb+srv://BookUserGENERIC:8ANyF1tBdepoieKX@book.lamoqyr.mongodb.net/?retryWrites=true&w=majority";
    private static final MongoClient client = MongoClients.create(databaseAddress);

    private static final ObjectMapper objectMapper = new ObjectMapper();
    public ArrayList<BookItem> initBookArrayFromDTB() {
        try {
            MongoDatabase db = client.getDatabase("bookstore");
            MongoCollection<Document> inventory = db.getCollection("inventory");

            ArrayList<BookItem> books = new ArrayList<>();

            var documents = inventory.find();
            for (var doc : documents) {
                System.out.println(doc.toJson());
                BookItem book = objectMapper.readValue(doc.toJson(), BookItem.class);
                books.add(book);
                System.out.println(book.toString());
            }

            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /*@Autowired
    public BookServicePortal(BookRepository bRep) {
        this.bookRepository = bRep;
    }

    // Perform operations using the repository methods

    public BookItem saveInventory(BookItem book) {
        return bookRepository.save(book);
    }

    public List<BookItem> getAllInventory() {
        System.out.println("getAllInventory()");
        return bookRepository.findAll();
    }

    public BookItem findInventoryById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Other methods as needed for update or delete

    // For instance:
    public void deleteInventoryById(String id) {
        bookRepository.deleteById(id);
    }*/
}
