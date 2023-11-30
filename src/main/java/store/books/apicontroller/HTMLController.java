package store.books.apicontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get-html-state")
public class HTMLController {

    @GetMapping("/home")
    public String getHomePage() {
        return "";
    }

    @GetMapping("/browsebooks")
    public String getBrowseBooksPage() {
        return "";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "";
    }

    @GetMapping("/ADMIN")
    public String getAdminPage() {
        return "";
    }

}
