package store.books.Mong_DAL.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import store.books.Mong_DAL.model.BookItem;

import java.util.List;

@RestController
public class BookController {

    /*@Autowired
    private BookRepository bookRepo;

    @GetMapping("/getAllBooks")
    public List<BookItem> getAllBooks()
    {
        return bookRepo.findAll();
    }*/
}
