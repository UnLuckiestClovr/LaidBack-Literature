package store.books.apicontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get-html-state")
public class HTMLController {

    @GetMapping("/home")
    public String getHomePage() {
        return "<article>\n" +
                "        <h1>Welcome to Laid-Back Literature Book Portal</h1>\n" +
                "\n" +
                "        <p>\n" +
                "            Welcome to Laid-Back Literature, your online sanctuary for the soul-stirring world of books!\n" +
                "            Immerse yourself in the enchanting realms of imagination as you explore our curated collection\n" +
                "            of literary wonders. Whether you're a seasoned bibliophile or just embarking on your reading journey,\n" +
                "            Laid-Back Literature is your haven for literary discovery.\n" +
                "        </p>\n" +
                "    </article>";
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
