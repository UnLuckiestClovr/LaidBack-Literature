package store.books.Mong_DAL;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import store.books.Mong_DAL.model.BookItem;

import java.util.List;

@Service
public class BookServicePortal {

    /*@Autowired
    public BookServicePortal(BookRepository bRep) {
        this.bookRepository = bRep;
    }

    // Perform operations using the repository methods

    public BookItem saveInventory(BookItem book) {
        return bookRepository.save(book);
    }

    public List<BookItem> getAllInventory() {
        System.out.println("getAllInventory()");
        return bookRepository.findAll();
    }

    public BookItem findInventoryById(String id) {
        return bookRepository.findById(id).orElse(null);
    }

    // Other methods as needed for update or delete

    // For instance:
    public void deleteInventoryById(String id) {
        bookRepository.deleteById(id);
    }*/
}
