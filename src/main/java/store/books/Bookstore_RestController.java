package store.books;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import store.books.Mong_DAL.MongCRUD;
import store.books.Mong_DAL.model.BookItem;

import java.util.ArrayList;

@RestController
@RequestMapping("/LB_Literature")
public class Bookstore_RestController {

    @RequestMapping(path="/print-all", method= RequestMethod.GET)
    public ArrayList<BookItem> getBooks() {
        MongCRUD.printAllBooks();

    }

}
