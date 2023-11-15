package store.books.Mong_DAL;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.stereotype.Service;
import store.books.Mong_DAL.model.BookItem;
import store.books.Mong_DAL.model.BookstoreItem;

import java.util.ArrayList;

@Service
public class BookstoreServicePortal {
    private static final MongoClient client = MongoClients.create("mongodb+srv://BookUserGENERIC:8ANyF1tBdepoieKX@book.lamoqyr.mongodb.net/?retryWrites=true&w=majority");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static ArrayList<BookstoreItem> bookstores = initBookstoreArrayFromDTB();

    public static ArrayList<BookstoreItem> initBookstoreArrayFromDTB() {
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
    public static void createBookstoreEntry(String jsonString) {
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
    public static ArrayList<BookstoreItem> getAllBookstores() {
        return bookstores;
    }


    public static  ArrayList<BookstoreItem> findStoreByState(String stateSearch) {

        try {
            ArrayList<BookstoreItem> output = new ArrayList<>();

            for (BookstoreItem bookstore : bookstores) {
                if (stateSearch.equalsIgnoreCase(bookstore.getState())) {
                    output.add(bookstore);
                }
            }

            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static  ArrayList<BookstoreItem> findStoreByCity(String citySearch) {

        try {
            ArrayList<BookstoreItem> output = new ArrayList<>();

            for (BookstoreItem bookstore : bookstores) {
                if (citySearch.equalsIgnoreCase(bookstore.getCity())) {
                    output.add(bookstore);
                }
            }

            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static  ArrayList<BookstoreItem> findStoreByZipcode(String zipSearch) {

        try {
            ArrayList<BookstoreItem> output = new ArrayList<>();

            for (BookstoreItem bookstore : bookstores) {
                if (zipSearch.equalsIgnoreCase(bookstore.getZipcode())) {
                    output.add(bookstore);
                }
            }

            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    //endregion

}