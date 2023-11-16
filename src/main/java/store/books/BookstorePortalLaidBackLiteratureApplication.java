package store.books;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import store.books.Mong_DAL.business.BookServicePortal;
import store.books.Mong_DAL.business.BookstoreServicePortal;
import store.books.Mong_DAL.business.UserServicePortal;

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

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {

            @Override public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:3000").allowedMethods("*");
            }
        };
    }

}
