package store.books.Mong_DAL;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Service;
import store.books.Mong_DAL.model.BookstoreItem;

import java.util.ArrayList;

@Service
public class BookstoreServicePortal {
    private static final MongoClient client = MongoClients.create("mongodb+srv://BookUserGENERIC:8ANyF1tBdepoieKX@book.lamoqyr.mongodb.net/?retryWrites=true&w=majority");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public ArrayList<BookstoreItem> initBookstoreArrayFromDTB() {
        try {
            MongoDatabase db = client.getDatabase("bookstore");
            MongoCollection<Document> coll = db.getCollection("stores");

            ArrayList<BookstoreItem> bookstores = new ArrayList<>();

            var documents = coll.find();
            for (var doc : documents) {
                System.out.println(doc.toJson());
                BookstoreItem bookstore = objectMapper.readValue(doc.toJson(), BookstoreItem.class);
                bookstores.add(bookstore);
            }
            return bookstores;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    // region CREATE
    public void createBookstoreEntry(String jsonString) {
        MongoDatabase db = client.getDatabase("bookstore");
        MongoCollection<Document> coll = db.getCollection("stores");

        try {
            ArrayList<BookstoreItem> currBookstores = initBookstoreArrayFromDTB();
            int id = 1;

            for (BookstoreItem bookstore : currBookstores) {
                if(bookstore.get_Id() != id) {
                    break;
                }
                else {id++;}
            }

            BookstoreItem bookstore = objectMapper.readValue(jsonString, BookstoreItem.class);

            Document doc = new Document("_id", id)
                    .append("state", bookstore.getState())
                    .append("city", bookstore.getCity())
                    .append("zipcode", bookstore.getZipcode())
                    .append("address", bookstore.getAddress())
                    .append("admin_password", bookstore.getAdminPassword());

            coll.insertOne(doc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //endregion

    // region READ

    //endregion

}
