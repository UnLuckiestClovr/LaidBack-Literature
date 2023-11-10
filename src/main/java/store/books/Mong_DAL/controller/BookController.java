package store.books.Mong_DAL.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.books.Mong_DAL.BookServicePortal;
import store.books.Mong_DAL.model.BookItem;
import store.books.Mong_DAL.model.BookstoreItem;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/lb-literature")
public class BookController {

    @RequestMapping(path="/get-all-books", method= RequestMethod.GET)
    public ArrayList<BookItem> getBooks() {
        return BookServicePortal.getAllBooks();
    }

    @RequestMapping(path="/get-book-author/{search}", method= RequestMethod.GET)
    public ArrayList<BookItem> getBookByAuthor(@PathVariable String search) {
        return BookServicePortal.findBookByAuthor(search);
    }
    @RequestMapping(path="/get-book-titles/{search}", method= RequestMethod.GET)
    public ArrayList<BookItem> getBookByTitle(@PathVariable String search) {
        return BookServicePortal.findBookByTitle(search);
    }
    @RequestMapping(path="/get-book-category/{search}", method= RequestMethod.GET)
    public ArrayList<BookItem> getBookCategory(@PathVariable String search) {
        return BookServicePortal.findBookCategory(search);
    }


    @RequestMapping(path="/add-book", method= RequestMethod.POST)
    public void addBook(@RequestBody String bookstore) {
        BookServicePortal.createBookEntry(bookstore);
    }

}
