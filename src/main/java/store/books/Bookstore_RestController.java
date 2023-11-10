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

    @RequestMapping(path="/get-all-bookstores", method= RequestMethod.GET)
    public ArrayList<BookItem> getBookstores() {
        //replace with bookstore portal once created
        return bookPortal.initBookArrayFromDTB();
    }

}
