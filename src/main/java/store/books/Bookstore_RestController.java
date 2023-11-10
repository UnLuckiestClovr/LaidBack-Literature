package store.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import store.books.Mong_DAL.BookstoreServicePortal;
import store.books.Mong_DAL.model.BookstoreItem;

import java.util.ArrayList;

@RestController
@RequestMapping("/lb-literature")
public class Bookstore_RestController {

    @Autowired
    BookstoreServicePortal bookstorePortal;

    @RequestMapping(path="/get-all-bookstores", method= RequestMethod.GET)
    public ArrayList<BookstoreItem> getBookstores() {
        return bookstorePortal.initBookstoreArrayFromDTB();
    }


    @RequestMapping(path="/add-bookstore", method= RequestMethod.POST)
    public void addBookstore(@RequestBody String bookstore) {
        bookstorePortal.createBookstoreEntry(bookstore);
    }
}
