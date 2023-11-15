package store.books.Mong_DAL.controller;
import org.springframework.web.bind.annotation.*;
import store.books.Mong_DAL.business.BookServicePortal;
import store.books.Mong_DAL.model.BookItem;
import store.books.Mong_DAL.model.UpdateRequest;

import java.util.ArrayList;

import static store.books.Mong_DAL.business.BookServicePortal.deleteBookEntry;

@RestController
@RequestMapping("/lb-literature")
public class BookController {

    @RequestMapping(path="/get-book-all", method= RequestMethod.GET)
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
        return BookServicePortal.findBookByCategory(search);
    }

    @RequestMapping(path="/add-book", method= RequestMethod.POST)
    public void addBook(@RequestBody String bookstore) {
        System.out.println(bookstore);
        BookServicePortal.createBookEntry(bookstore);
    }

    @RequestMapping(path="/update-book", method=RequestMethod.PATCH)
    public void updateBook(@RequestBody UpdateRequest obj) {
        BookServicePortal.updateBookEntry(obj);
    }

    @RequestMapping(path= "/delete-book",method = RequestMethod.DELETE)
    public void deleteBook(@RequestBody String name){deleteBookEntry(name);}

}
