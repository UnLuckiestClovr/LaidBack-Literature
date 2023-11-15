package store.books.Mong_DAL.business;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.springframework.stereotype.Service;
import store.books.Mong_DAL.model.BookItem;
import store.books.Mong_DAL.model.BookstoreItem;
import store.books.Mong_DAL.model.UpdateRequest;

import java.util.ArrayList;

@Service
public class BookstoreServicePortal {
    private static final MongoClient client = MongoClients.create("mongodb+srv://BookUserGENERIC:8ANyF1tBdepoieKX@book.lamoqyr.mongodb.net/?retryWrites=true&w=majority");
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static ArrayList<BookstoreItem> bookstores = new ArrayList<>();

    public static ArrayList<BookstoreItem> initBookstoreArrayFromDTB() {
        try {
            MongoDatabase db = client.getDatabase("bookstore");
            MongoCollection<Document> coll = db.getCollection("stores");

            bookstores = new ArrayList<>();

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
            coll.insertOne(Document.parse(jsonString));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            bookstores = initBookstoreArrayFromDTB();
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


    //region UPDATE
    public static void updateStoreEntry(UpdateRequest update_obj) {
        MongoCollection<Document> coll = client.getDatabase("bookstore").getCollection("stores");

        try {
            for (BookstoreItem store : bookstores) {
                if (store.getZipcode().equals(update_obj.getDocName())) {
                    coll.deleteOne(Filters.eq("zipcode", store.getZipcode()));
                    coll.insertOne(Document.parse(objectMapper.writeValueAsString(store)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            initBookstoreArrayFromDTB();
        }
    }
    //endregion

    //region DELETE
    public static void deleteStoreEntry(String zipcode) {
        try {
            MongoCollection<Document> coll = client.getDatabase("bookstore").getCollection("stores");
            coll.deleteOne(Filters.eq("zipcode", zipcode));
        } catch (MongoException e) {
            e.printStackTrace();
        } finally {
            initBookstoreArrayFromDTB();
        }
    }
    //endregion
}
