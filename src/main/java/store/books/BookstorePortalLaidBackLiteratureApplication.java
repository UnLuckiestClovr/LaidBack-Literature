package store.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import store.books.Mong_DAL.repository.BookRepository;

@SpringBootApplication
@EnableMongoRepositories
public class BookstorePortalLaidBackLiteratureApplication{

    @Autowired
    BookRepository bookRepo;

    public static void main(String[] args) {
        SpringApplication.run(BookstorePortalLaidBackLiteratureApplication.class, args);

    }

}
