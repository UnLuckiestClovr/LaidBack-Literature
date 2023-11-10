package store.books.Mong_DAL.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import store.books.Mong_DAL.BookServicePortal;
import store.books.Mong_DAL.model.BookItem;

import java.util.List;

@RestController
@RequestMapping("/lb-literature")
public class BookController {

    @Autowired
    static BookServicePortal bookstorePortal;

    @RequestMapping(path="/get-all-books")
    public void getAllBooks() {
        bookstorePortal.getAllBooks();
    }
}
