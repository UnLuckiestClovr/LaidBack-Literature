package store.books;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import store.books.Mong_DAL.BookServicePortal;
import store.books.Mong_DAL.BookstoreServicePortal;
import store.books.Mong_DAL.UserServicePortal;

@SpringBootApplication(
        exclude = {
                MongoAutoConfiguration.class,
                MongoDataAutoConfiguration.class
        }
)
//@EnableMongoRepositories
public class BookstorePortalLaidBackLiteratureApplication{

    public static void main(String[] args) {
        SpringApplication.run(BookstorePortalLaidBackLiteratureApplication.class, args);
        BookServicePortal.initBookArrayFromDTB();
        BookstoreServicePortal.initBookstoreArrayFromDTB();
        UserServicePortal.initUserArrayFromDTB();
    }

}
