package store.books.Mong_DAL;

import store.books.BookstorePortalLaidBackLiteratureApplication;
import store.books.Mong_DAL.model.BookItem;
import store.books.Mong_DAL.repository.BookRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MongCRUD {

    static BookRepository bookRepo;

    public static void initRepo() {
        bookRepo = BookstorePortalLaidBackLiteratureApplication.bookRepo;
    }

    public static void printAllBooks() {
        List<BookItem> books = bookRepo.findAll();
        for (BookItem book : books) {
            book.printString();
        }
    }

    public static ArrayList<BookItem> getAllBooks() {

    }

}
