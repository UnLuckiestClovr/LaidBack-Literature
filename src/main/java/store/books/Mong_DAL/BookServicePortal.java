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
    private static final MongoClient client = MongoClients.create("mongodb+srv://BookUserGENERIC:8ANyF1tBdepoieKX@book.lamoqyr.mongodb.net/?retryWrites=true&w=majority");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ArrayList<BookItem> initBookArrayFromDTB() {
        try {
            MongoDatabase db = client.getDatabase("bookstore");
            MongoCollection<Document> coll = db.getCollection("inventory");

            ArrayList<BookItem> books = new ArrayList<>();

            var documents = coll.find();
            for (var doc : documents) {
                System.out.println(doc.toJson());
                BookItem book = objectMapper.readValue(doc.toJson(), BookItem.class);
                books.add(book);
            }
            return books;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void createBookEntry(String jsonString) {
        MongoDatabase db = client.getDatabase("bookstore");
        MongoCollection<Document> coll = db.getCollection("inventory");

        try {
            Document doc = Document.parse(jsonString);
            coll.insertOne(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
