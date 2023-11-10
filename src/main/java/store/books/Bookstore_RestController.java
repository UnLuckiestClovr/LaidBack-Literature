package store.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.books.Mong_DAL.BookstoreServicePortal;
import store.books.Mong_DAL.model.BookstoreItem;

import java.util.ArrayList;

@RestController
@RequestMapping("/lb-literature")
public class Bookstore_RestController {

    @RequestMapping(path="/get-all-bookstores", method= RequestMethod.GET)
    public ArrayList<BookstoreItem> getBookstores() {
        return BookstoreServicePortal.getAllBookstores();
    }

    @RequestMapping(path="/get-bookstores-state/{search}", method= RequestMethod.GET)
    public ArrayList<BookstoreItem> getBookstoresByState(@PathVariable String search) {
        return BookstoreServicePortal.findStoreByState(search);
    }
    @RequestMapping(path="/get-bookstores-city/{search}", method= RequestMethod.GET)
    public ArrayList<BookstoreItem> getBookstoresByCity(@PathVariable String search) {
        return BookstoreServicePortal.findStoreByCity(search);
    }
    @RequestMapping(path="/get-bookstores-zipcode/{search}", method= RequestMethod.GET)
    public ArrayList<BookstoreItem> getBookstoresByZipcode(@PathVariable String search) {
        return BookstoreServicePortal.findStoreByZipcode(search);
    }


    @RequestMapping(path="/add-bookstore", method= RequestMethod.POST)
    public void addBookstore(@RequestBody String bookstore) {
        BookstoreServicePortal.createBookstoreEntry(bookstore);
    }

}
