package store.books.Mong_DAL.business;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.stereotype.Service;
import store.books.Mong_DAL.model.BookItem;
import store.books.Mong_DAL.model.UpdateRequest;

import java.util.ArrayList;

@Service
public class BookServicePortal {
    private static final MongoClient client = MongoClients.create("mongodb+srv://BookUserGENERIC:8ANyF1tBdepoieKX@book.lamoqyr.mongodb.net/?retryWrites=true&w=majority");
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static ArrayList<BookItem> books = new ArrayList<>();

    public static ArrayList<BookItem> initBookArrayFromDTB() {
        try {
            MongoDatabase db = client.getDatabase("bookstore");
            MongoCollection<Document> coll = db.getCollection("inventory");

            books = new ArrayList<>();

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


    // region CREATE

    public static void createBookEntry(String jsonString) {
        MongoCollection<Document> coll = client.getDatabase("bookstore").getCollection("inventory");

        System.out.println("JSON: " + jsonString);
        try {
            coll.insertOne(Document.parse(jsonString));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            books = initBookArrayFromDTB();
        }
    }
    //endregion

    // region READ
    public static ArrayList<BookItem> getAllBooks() {
        return books;
    }

    public static  ArrayList<BookItem> findBookByCategory(String catSearch) {

        try {
            ArrayList<BookItem> output = new ArrayList<>();

            for (BookItem book : books) {
                if (catSearch.equalsIgnoreCase(book.getCategory())) {
                    output.add(book);
                }
            }

            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static  ArrayList<BookItem> findBookByTitle(String titleSearch) {

        try {
            ArrayList<BookItem> output = new ArrayList<>();

            for (BookItem book : books) {
                if (book.getName().toLowerCase().contains(titleSearch.toLowerCase())) {
                    output.add(book);
                }
            }

            return output;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static  ArrayList<BookItem> findBookByAuthor(String authorSearch) {

        try {
            ArrayList<BookItem> output = new ArrayList<>();

            for (BookItem book : books) {
                if (authorSearch.equalsIgnoreCase(book.getAuthor())) {
                    output.add(book);
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
    public static void updateBookEntry(UpdateRequest update_obj) {
        MongoCollection<Document> coll = client.getDatabase("bookstore").getCollection("inventory");

        try {
            for (BookItem book : books) {
                if (book.getName().equals(update_obj.getDocName())) {
                    UpdateResult updateResult = coll.updateOne(Filters.eq("planetName", update_obj.getDocName()), new Document("$set", new Document(update_obj.getKeyValue(), update_obj.getNewValue())));
                    if (updateResult.getModifiedCount() > 0) {
                        System.out.println("Planet updated successfully");
                    } else {
                        System.out.println("Planet not found or update failed");
                    }

                    coll.deleteOne(Filters.eq("name", book.getName()));
                    coll.insertOne(Document.parse(objectMapper.writeValueAsString(book)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            initBookArrayFromDTB();
        }
    }
    //endregion

    //region DELETE
    public static void deleteBookEntry(String name) {
        try {
            MongoCollection<Document> coll = client.getDatabase("bookstore").getCollection("inventory");
            coll.deleteOne(Filters.eq("name", name));
        } catch (MongoException e) {
            e.printStackTrace();
        } finally {
            initBookArrayFromDTB();
        }
    }
    //endregion

}
