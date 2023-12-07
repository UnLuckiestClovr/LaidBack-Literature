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

    public static <E> ArrayList<E> initReportsArrayFromDTB(String collSTR) {
        try {

            MongoCollection<Document> coll = db.getCollection(collSTR);

            ArrayList<E> docs = new ArrayList<>();

            var documents = coll.find();
            for (var doc : documents) {
                switch (collSTR) {
                    case "consumable" -> docs.add((E) objectMapper.readValue(doc.toJson(), ConsumableReport.class));
                    case "customer_visits" -> docs.add((E) objectMapper.readValue(doc.toJson(), CustVisitsReport.class));
                    case "sales" -> docs.add((E) objectMapper.readValue(doc.toJson(), SalesReport.class));
                }

            }

            return docs;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void createReport(String string, String type) {
        try {
            MongoCollection<Document> coll = db.getCollection(type);

            LocalDate currentDate = LocalDate.now();
            Month month = currentDate.getMonth();
            int year = currentDate.getYear();

            Document doc;

            switch (type) {
                case "consumable":
                    doc = coll.find(Filters.and(
                            Filters.eq("year", year),
                            Filters.eq("month", month),
                            Filters.eq("bookTitle", string))).first();

                    if (doc != null) {
                        coll.updateOne(Filters.and(
                                Filters.eq("year", year),
                                Filters.eq("month", month),
                                Filters.eq("bookTitle", string)), new Document("numSold", (doc.getInteger("numSold") + 1)));
                    } else {
                        ConsumableReport cons = new ConsumableReport();
                        cons.setBookTitle(string);
                        cons.setNumSold(0);
                        cons.setMonth(month.toString());
                        cons.setYear(year);
                        coll.insertOne(Document.parse(objectMapper.writeValueAsString(cons)));
                    }
                    break;

                case "customer_visits":

                    doc = coll.find(Filters.and(
                            Filters.eq("year", year),
                            Filters.eq("month", month),
                            Filters.eq("customerID", Integer.parseInt(string)))).first();

                    if (doc != null) {
                        coll.updateOne(Filters.and(
                                Filters.eq("year", year),
                                Filters.eq("month", month),
                                Filters.eq("customerID", Integer.parseInt(string))), new Document("numVisits", (doc.getInteger("numVisits") + 1)));
                    } else {
                        CustVisitsReport custV = new CustVisitsReport();
                        custV.setCustomerID(Integer.parseInt(string));
                        custV.setNumVisits(0);
                        custV.setMonth(month.toString());
                        custV.setYear(year);
                        coll.insertOne(Document.parse(objectMapper.writeValueAsString(custV)));
                    }
                    break;

                case "sales":

                    doc = coll.find(Filters.and(
                            Filters.eq("year", year),
                            Filters.eq("month", month),
                            Filters.eq("bookstoreID", Integer.parseInt(string)))).first();

                    if (doc != null) {
                        coll.updateOne(Filters.and(
                                Filters.eq("year", year),
                                Filters.eq("month", month),
                                Filters.eq("bookstoreID", Integer.parseInt(string))), new Document("bookSales", (doc.getInteger("bookSales") + 1)));
                    } else {
                        SalesReport sale = new SalesReport();
                        sale.setBookstoreID(Integer.parseInt(string));
                        sale.setBookSales(0);
                        sale.setMonth(month.toString());
                        sale.setYear(year);
                        coll.insertOne(Document.parse(objectMapper.writeValueAsString(sale)));
                    }
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
