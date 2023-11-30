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
import store.books.Mong_DAL.model.BookstoreItem;
import store.books.Mong_DAL.model.UpdateRequest;
import store.books.Mong_DAL.model.User;

import java.util.ArrayList;

@Service
public class UserServicePortal {
    private static final MongoClient client = MongoClients.create("mongodb+srv://BookUserGENERIC:8ANyF1tBdepoieKX@book.lamoqyr.mongodb.net/?retryWrites=true&w=majority");
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> initUserArrayFromDTB() {
        try {
            MongoDatabase db = client.getDatabase("bookstore");
            MongoCollection<Document> coll = db.getCollection("users");

            users = new ArrayList<>();

            var documents = coll.find();
            for (var doc : documents) {
                System.out.println(doc.toJson());
                User user = objectMapper.readValue(doc.toJson(), User.class);
                users.add(user);
            }

            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    // region CREATE

    public static void createUserEntry(String jsonString) {
        MongoCollection<Document> coll = client.getDatabase("bookstore").getCollection("users");

        System.out.println("JSON: " + jsonString);
        try {
            User user = objectMapper.readValue(jsonString, User.class);

            user.setId((users.size()+1));

            coll.insertOne(Document.parse(objectMapper.writeValueAsString(user)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            users = initUserArrayFromDTB();
        }
    }
    //endregion

    // region READ
    public static ArrayList<User> getAllUsers() {
        return users;
    }

    public static  ArrayList<User> findUserByUsername(String unSearch) {

        try {
            ArrayList<User> output = new ArrayList<>();

            for (User user : users) {
                if (unSearch.equalsIgnoreCase(user.getUsername())) {
                    output.add(user);
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
    public static void updateUserEntry(UpdateRequest update_obj) {
        MongoCollection<Document> coll = client.getDatabase("bookstore").getCollection("users");

        try {
            for (User user : users) {
                if (user.getUsername().equals(update_obj.getDocName())) {
                    UpdateResult updateResult = coll.updateOne(Filters.eq("username", update_obj.getDocName()), new Document("$set", new Document(update_obj.getKeyValue(), update_obj.getNewValue())));
                    if (updateResult.getModifiedCount() > 0) {
                        System.out.println("User updated successfully");
                    } else {
                        System.out.println("User not found or update failed");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            initUserArrayFromDTB();
        }
    }
    //endregion

    //region DELETE
    public static void deleteUserEntry(String username) {
        try {
            MongoCollection<Document> coll = client.getDatabase("bookstore").getCollection("users");
            coll.deleteOne(Filters.eq("username", username));
        } catch (MongoException e) {
            e.printStackTrace();
        } finally {
            initUserArrayFromDTB();
        }
    }
    //endregion


    public static boolean findUserLogin(String username, String password) {
        try {
            MongoCollection<Document> coll = client.getDatabase("bookstore").getCollection("users");
            Document doc = coll.find(Filters.and(Filters.eq("username", username), Filters.eq("encodedPassword", password))).first();

            if (doc != null) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
