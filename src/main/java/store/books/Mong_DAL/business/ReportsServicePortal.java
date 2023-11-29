package store.books.Mong_DAL.business;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.web.bind.annotation.PathVariable;
import store.books.Mong_DAL.model.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;


public class ReportsServicePortal {
    private static final MongoClient client = MongoClients.create("mongodb+srv://BookUserGENERIC:8ANyF1tBdepoieKX@book.lamoqyr.mongodb.net/?retryWrites=true&w=majority");
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final MongoDatabase db = client.getDatabase("monthlyreports");

    public static ArrayList<String> initReportsArrayFromDTB(String collSTR) {
        try {

            MongoCollection<Document> coll = db.getCollection(collSTR);

            ArrayList<String> jsonDocs = new ArrayList<>();

            var documents = coll.find();
            for (var doc : documents) {
                jsonDocs.add(doc.toJson());
            }

            return jsonDocs;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void createReport(String jsonString, String type) {
        try {
            MongoCollection<Document> coll = db.getCollection(type);

            LocalDate currentDate = LocalDate.now();
            Month month = currentDate.getMonth();
            int year = currentDate.getYear();



            switch (type) {
                case "consumable":
                    ConsumableReport cons = objectMapper.readValue(jsonString, ConsumableReport.class);
                    Document doc = coll.find(Filters.and(Filters.eq("year", year), Filters.eq("month", month), Filters.eq("bookTitle", cons.getBookTitle()))).first();
                    if (doc != null) {
                        cons.setNumSold((cons.getNumSold() + 1));
                    } else {
                        cons.setNumSold(0);
                        cons.setMonth(month.toString());
                        cons.setYear(year);
                        coll.insertOne(Document.parse(objectMapper.writeValueAsString(cons)));
                    }
                    break;
                case "customer_visits":
                    CustVisitsReport custV = objectMapper.readValue(jsonString, CustVisitsReport.class);
                    Document doc = coll.find(Filters.and(Filters.eq("year", year), Filters.eq("month", month), Filters.eq("bookTitle", custV.getCustomerID()))).first();

                    custV.setNumVisits(0);
                    custV.setMonth(month.toString());
                    custV.setYear(year);
                    coll.insertOne(Document.parse(objectMapper.writeValueAsString(custV)));
                    break;
                case "sales":
                    SalesReport sale = objectMapper.readValue(jsonString, SalesReport.class);
                    sale.setBookSales(0);
                    sale.setMonth(month.toString());
                    sale.setYear(year);
                    coll.insertOne(Document.parse(objectMapper.writeValueAsString(sale)));
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateReport(UpdateRequest update_obj, String type, int year, String month) {
        MongoCollection<Document> coll = client.getDatabase("monthlyreports").getCollection(type);

        try {

            UpdateResult updateResult = coll.updateOne(Filters.and(Filters.eq("year", year), Filters.eq("month", month)), new Document("$set", new Document(update_obj.getKeyValue(), update_obj.getNewValue())));

            if (updateResult.getModifiedCount() > 0) {
                System.out.println("Book updated successfully");
            } else {
                System.out.println("Book not found or update failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteReport(int year, String month, String type) {
        try {
            MongoCollection<Document> coll = client.getDatabase("monthlyreports").getCollection(type);
            coll.deleteOne(Filters.and(Filters.eq("year", year), Filters.eq("month", month)));
        } catch (MongoException e) {
            e.printStackTrace();
        }
    }

    public static <E> ArrayList<E> search(String type, int year, String month) throws IOException {
        MongoCollection<Document> coll = db.getCollection(type);

        ArrayList<E> docs = new ArrayList<>();

        switch (type) {
            case "consumable":
                var consReports = coll.find(Filters.and(Filters.eq("year", year), Filters.eq("month", month)));
                for (var doc : consReports) {
                    docs.add((E) objectMapper.readValue(doc.toJson(), ConsumableReport.class));
                }
                break;
            case "customer_visits":
                var custReports = coll.find(Filters.and(Filters.eq("year", year), Filters.eq("month", month)));
                for (var doc : custReports) {
                    docs.add((E) objectMapper.readValue(doc.toJson(), ConsumableReport.class));
                }
                break;
            case "sales":
                var salesReports = coll.find(Filters.and(Filters.eq("year", year), Filters.eq("month", month)));
                for (var doc : salesReports) {
                    docs.add((E) objectMapper.readValue(doc.toJson(), ConsumableReport.class));
                }
                break;
        }

        return docs;
    }

    public static <E> ArrayList<E> search(String type, String month) throws IOException {
        MongoCollection<Document> coll = db.getCollection(type);

        ArrayList<E> docs = new ArrayList<>();

        switch (type) {
            case "consumable":
                var consReports = coll.find(Filters.eq("month", month));
                for (var doc : consReports) {
                    docs.add((E) objectMapper.readValue(doc.toJson(), ConsumableReport.class));
                }
                break;
            case "customer_visits":
                var custReports = coll.find(Filters.eq("month", month));
                for (var doc : custReports) {
                    docs.add((E) objectMapper.readValue(doc.toJson(), ConsumableReport.class));
                }
                break;
            case "sales":
                var salesReports = coll.find(Filters.eq("month", month));
                for (var doc : salesReports) {
                    docs.add((E) objectMapper.readValue(doc.toJson(), ConsumableReport.class));
                }
                break;
        }

        return docs;
    }

    public static <E> ArrayList<E> search(String type, int year) throws IOException {
        MongoCollection<Document> coll = db.getCollection(type);

        ArrayList<E> docs = new ArrayList<>();

        switch (type) {
            case "consumable":
                var consReports = coll.find(Filters.eq("year", year));
                for (var doc : consReports) {
                    docs.add((E) objectMapper.readValue(doc.toJson(), ConsumableReport.class));
                }
                break;
            case "customer_visits":
                var custReports = coll.find(Filters.eq("year", year));
                for (var doc : custReports) {
                    docs.add((E) objectMapper.readValue(doc.toJson(), ConsumableReport.class));
                }
                break;
            case "sales":
                var salesReports = coll.find(Filters.eq("year", year));
                for (var doc : salesReports) {
                    docs.add((E) objectMapper.readValue(doc.toJson(), ConsumableReport.class));
                }
                break;
        }

        return docs;
    }
}
