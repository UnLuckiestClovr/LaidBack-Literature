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
import store.books.Mong_DAL.model.UpdateRequest;
import store.books.Mong_DAL.model.User;

import java.util.ArrayList;


public class ReportsServicePortal {
    private static final MongoClient client = MongoClients.create("mongodb+srv://BookUserGENERIC:8ANyF1tBdepoieKX@book.lamoqyr.mongodb.net/?retryWrites=true&w=majority");
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static ArrayList<UpdateRequest> updateRequests = new ArrayList<>();

    public static ArrayList<UpdateRequest> initUserArrayFromDTB() {
        try {
            MongoDatabase db = client.getDatabase("monthlyreports");
            MongoCollection<Document> coll = db.getCollection("consumable");

            updateRequests = new ArrayList<>();

            var documents = coll.find();
            for (var doc : documents) {
                System.out.println(doc.toJson());
                UpdateRequest upReq = objectMapper.readValue(doc.toJson(), UpdateRequest.class);
                updateRequests.add(upReq);
            }

            return updateRequests;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    //g
    public static void createReport(String jsonString) {
        MongoCollection<Document> coll = client.getDatabase("monthlyreports").getCollection("consumable");

        System.out.println("JSON: " + jsonString);
        try {
            UpdateRequest request = objectMapper.readValue(jsonString, UpdateRequest.class);

            request.setKeyValue(String.valueOf((updateRequests.size()+1)));

            coll.insertOne(Document.parse(objectMapper.writeValueAsString(request)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            updateRequests = initUserArrayFromDTB();
        }
    }
    public static void updateReport(UpdateRequest update_obj) {
        MongoCollection<Document> coll = client.getDatabase("monthlyreports").getCollection("consumable ");

        try {
            for (UpdateRequest request : updateRequests) {
                if (request.getDocName().equals(update_obj.getDocName())) {
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
    public static void deleteReport(String docName) {
        try {
            MongoCollection<Document> coll = client.getDatabase("monthlyreports").getCollection("consumable");
            coll.deleteOne(Filters.eq("docName", docName));
        } catch (MongoException e) {
            e.printStackTrace();
        } finally {
            initUserArrayFromDTB();
        }
    }
    //endregion

}
