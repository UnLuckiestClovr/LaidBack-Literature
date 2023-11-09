package store.books;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import store.books.Mong_DAL.BookServicePortal;
import store.books.Mong_DAL.model.BookItem;

import java.util.ArrayList;

@RestController
@RequestMapping("/lb-literature")
public class Bookstore_RestController {

    @Autowired
    BookServicePortal bookPortal;

    @RequestMapping(path="/print-all", method= RequestMethod.GET)
    public ArrayList<BookItem> getBooks() {
        String databaseAddress = "mongodb+srv://BookUserGENERIC:8ANyF1tBdepoieKX@book.lamoqyr.mongodb.net/?retryWrites=true&w=majority";
        try (MongoClient client = MongoClients.create(databaseAddress)) {
            MongoDatabase db = client.getDatabase("bookstore");
            MongoCollection<Document> inventory = db.getCollection("inventory");
            var yes = inventory.find();
            for (var yes2 : yes) {
                System.out.println(yes2.toJson());
            }
        }
        return new ArrayList<>();
        /*ArrayList<BookItem> books = (ArrayList<BookItem>) bookPortal.getAllInventory();
        for (BookItem book : books) {
            book.printString();
        }

        return books;*/
    }

}
