package store.books.Mong_DAL.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import store.books.Mong_DAL.BookServicePortal;
import store.books.Mong_DAL.model.BookItem;

import java.util.List;

@RestController
@RequestMapping("/lb-literature")
public class BookController {

    @Autowired
    BookServicePortal bookPortal;

    @RequestMapping(path = "/get-all-books/add-book", method = RequestMethod.POST)
    public void addBook (@RequestBody String book){
        bookPortal.createBookEntry(book);

    }

}
