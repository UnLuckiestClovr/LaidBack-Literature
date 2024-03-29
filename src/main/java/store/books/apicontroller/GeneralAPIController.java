package store.books.apicontroller;

import org.springframework.web.bind.annotation.*;
import store.books.Mong_DAL.business.BookServicePortal;
import store.books.Mong_DAL.business.BookstoreServicePortal;
import store.books.Mong_DAL.business.UserServicePortal;
import store.books.Mong_DAL.model.UpdateRequest;

import java.util.ArrayList;

@RestController
@RequestMapping("/lb-literature")
public class GeneralAPIController {

    @RequestMapping(path="/get-all/{collection}", method= RequestMethod.GET)
    public <T> ArrayList<T> getAllEntries(@PathVariable String collection) {
        return switch (collection) { // Checks whether we want to get Books, Bookstores, or Users

            case "books" -> (ArrayList<T>) BookServicePortal.getAllBooks();
            case "bookstores" -> (ArrayList<T>) BookstoreServicePortal.getAllBookstores();
            case "users" -> (ArrayList<T>) UserServicePortal.getAllUsers();

            default -> new ArrayList<>(); // Throws In Case of Invalid Input

        };
    }

    @RequestMapping(path="/get/books/{variable}/{search}", method= RequestMethod.GET)
    public <T> ArrayList<T> getBookByVariable(@PathVariable String variable, @PathVariable String search) {
        return switch (variable) { // Checks whether we want to search for a Book, Bookstore, or User and by what variable we wish to search for them

            case "author" -> (ArrayList<T>) BookServicePortal.findBookByAuthor(search);
            case "title" -> (ArrayList<T>) BookServicePortal.findBookByTitle(search);
            case "category" -> (ArrayList<T>) BookServicePortal.findBookByCategory(search);

            default -> new ArrayList<>(); // Throws In Case of Invalid Input
        };
    }

    @RequestMapping(path="/get/bookstores/{variable}/{search}", method= RequestMethod.GET)
    public <T> ArrayList<T> getBookstoreByVariable(@PathVariable String variable, @PathVariable String search) {
        return switch (variable) { // Checks whether we want to search for a Book, Bookstore, or User and by what variable we wish to search for them

            case "state" -> (ArrayList<T>) BookstoreServicePortal.findStoreByState(search);
            case "city" -> (ArrayList<T>) BookstoreServicePortal.findStoreByCity(search);
            case "zipcode" -> (ArrayList<T>) BookstoreServicePortal.findStoreByZipcode(search);
            case "address" -> (ArrayList<T>) BookstoreServicePortal.findStorebyAddress(search);

            default -> new ArrayList<>(); // Throws In Case of Invalid Input
        };
    }

    @RequestMapping(path="/get/users/{variable}/{search}", method= RequestMethod.GET)
    public <T> ArrayList<T> getUserByVariable(@PathVariable String variable, @PathVariable String search) {
        if (search.equals("username")) {
            return (ArrayList<T>) UserServicePortal.findUserByUsername(search);
        } else {
            return new ArrayList<>();
        }
    }

    @RequestMapping(path="/add/{collection}", method= RequestMethod.POST)
    public String addEntry(@RequestBody String obj, @PathVariable String collection) {
        System.out.println("String : " + obj + "\nCollection : " + collection);

        try {
            switch (collection) { // Checks whether we want to add a Book, Bookstore, or User object to our Database

                case "book":
                    BookServicePortal.createBookEntry(obj);
                    return "Addition of Book Successful";
                case "bookstore":
                    BookstoreServicePortal.createBookstoreEntry(obj);
                    return "Addition of Bookstore Successful";
                case "user":
                    UserServicePortal.createUserEntry(obj);
                    return "Addition of User Successful";
                default:
                    throw new IllegalArgumentException("Invalid Collection"); // Throws In Case of Invalid Input
            }
        } catch (Exception e) {
            System.out.println(e);
            return "Error: Addition of Entry Failed";
        }

    }

    @RequestMapping(path="/update/{collection}", method=RequestMethod.PATCH)
    public void updateEntry(@RequestBody UpdateRequest obj, @PathVariable String collection) {
        System.out.println("Update : " + obj + "\nCollection : " + collection);
        switch (collection) { // Checks if we want to update a Book, Bookstore, or User Entry.

            case "book" -> BookServicePortal.updateBookEntry(obj);
            case "bookstore" -> BookstoreServicePortal.updateStoreEntry(obj);
            case "user" -> UserServicePortal.updateUserEntry(obj);

            default -> throw new IllegalArgumentException("Invalid Collection Name"); // Throws In Case of Invalid Input

        }
    }

    @RequestMapping(path= "/delete/{collection}/{varSearch}",method = RequestMethod.DELETE)
    public void deleteEntry(@PathVariable String varSearch, @PathVariable String collection){
        System.out.println("String : " + varSearch + "\nCollection : " + collection);
        switch (collection) { //Checks Whether we want to Delete a Book, Bookstore, or User.

            case "book" -> BookServicePortal.deleteBookEntry(varSearch);
            case "bookstore" -> BookstoreServicePortal.deleteStoreEntry(varSearch);
            case "user" -> UserServicePortal.deleteUserEntry(varSearch);

            default -> throw new IllegalArgumentException("Invalid Collection"); // Throws In Case of Invalid Input

        }
    }

    //Security / Login
    @RequestMapping(path="/auth/{username}/{password}/login", method=RequestMethod.GET)
    public String login(@PathVariable String username, @PathVariable String password) {
        System.out.println("Calling Login...");

        String adName = "admin-admin_9112997";

        boolean adBool = (username + "-" + password).equals(adName);

        boolean logBool = false;
        if (adBool) { // If adBool is true it sets logBool to true as well.
            logBool = true;
        } else {
            logBool = UserServicePortal.findUserLogin(username, password);
        }

        return "{ \"logBool\" : " + logBool + ", " +
                "\"adBool\" : " + adBool + " }";
    }

}
