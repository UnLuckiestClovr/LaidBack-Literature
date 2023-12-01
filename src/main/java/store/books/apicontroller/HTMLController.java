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
                "        <h2>Welcome to Laid-Back Literature Book Portal</h2>\n" +
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
        return "<h2>Find the Books that are right for YOU!</h2>\n" +
                "\n" +
                "<br/><br/>\n" +
                "\n" +
                "<p>Search By:</p>\n" +
                "<select id=\"searchCatsBooks\">\n" +
                "    <option value=\"author\">Author</option>\n" +
                "    <option value=\"title\">Title</option>\n" +
                "    <option value=\"category\">Genre</option>\n" +
                "</select>\n" +
                "\n" +
                "<br/><br/>\n" +
                "\n" +
                "<input type=\"text\" id=\"searchInput\"/>\n" +
                "<br/><br/>\n" +
                "<button onclick=\"getSearchBooks()\">Search for Book</button>\n" +
                "<br/><br/>\n" +
                "<button onclick=\"getAllBooks()\">View All Books</button>\n" +
                "\n" +
                "<br/<br/>\n" +
                "<br/<br/>\n" +
                "\n" +
                "<ul id=\"uList\"></ul>";
    }

    @GetMapping("/loginorregister")
    public String getLoginPage() {
        return "<article>\n" +
                "    <h2>Login</h2>\n" +
                "    \n" +
                "    <p><input type=\"text\" id=\"logUnameInput\"> << Username</p>\n" +
                "    <p><input type=\"text\" id=\"logPassInput\"> << Password</p>\n" +
                "\n" +
                "    <br/><br/>\n" +
                "    \n" +
                "    <button onclick=\"login()\">Login</button>\n" +
                "</article>";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "";
    }

}
