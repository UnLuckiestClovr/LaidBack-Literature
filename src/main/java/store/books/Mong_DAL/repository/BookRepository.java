package store.books.Mong_DAL.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import store.books.Mong_DAL.model.BookItem;

import java.util.List;

public interface BookRepository  extends MongoRepository<BookItem, String> {

    @Query("{name: '?0'}")
    BookItem findItemByName(String name);

    @Query(value="{category: '?0'}", fields="{'name': 1, 'quantity': 1}")
    List<BookItem> findAll(String category);

    public long count();

}
