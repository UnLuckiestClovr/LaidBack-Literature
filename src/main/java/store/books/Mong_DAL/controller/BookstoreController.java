package store.books.Mong_DAL.controller;

import org.springframework.web.bind.annotation.*;
import store.books.Mong_DAL.BookstoreServicePortal;
import store.books.Mong_DAL.model.BookstoreItem;
import store.books.Mong_DAL.model.UpdateRequest;

import java.util.ArrayList;

@RestController
@RequestMapping("/lb-literature")
public class BookstoreController {

    @RequestMapping(path="/get-bookstore-all", method= RequestMethod.GET)
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

    @RequestMapping(path="/update-bookstore", method= RequestMethod.PATCH)
    public void updateBookstore(@RequestBody UpdateRequest obj) {
        BookstoreServicePortal.updateStoreEntry(obj);
    }

    @RequestMapping(path="/delete-bookstore", method= RequestMethod.DELETE)
    public void updateBookstore(@RequestBody String zipcode) {
        BookstoreServicePortal.deleteStoreEntry(zipcode);
    }

}
