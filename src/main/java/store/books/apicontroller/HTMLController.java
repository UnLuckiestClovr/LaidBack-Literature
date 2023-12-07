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
                "<input type=\"text\" id=\"BookSearchInput\"/>\n" +
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
        return "<table>\n" +
                "    <tr>\n" +
                "        <td><a href=\"#\" onclick=\"showSubPage('create')\">Create Database Entries</a></td>\n" +
                "        <td><a href=\"#\" onclick=\"showSubPage('read')\">Browse Database Entries</a></td>\n" +
                "        <td><a href=\"#\" onclick=\"showSubPage('update')\">Update Database Entries</a></td>\n" +
                "        <td><a href=\"#\" onclick=\"showSubPage('delete')\">Delete Database Entries</a></td>\n" +
                "    </tr>\n" +
                "</table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<article class=\"subpage\" id=\"create\">\n" +
                "    <h2>Create Database Entries</h2>\n" +
                "\n" +
                "    <select id=\"CreateSelect\" onchange=\"showCreatePage()\">\n" +
                "        <option>Select...</option>\n" +
                "        <option value=\"booksC\">Books</option>\n" +
                "        <option value=\"bookstoresC\">Bookstores</option>\n" +
                "        <option value=\"reportsC\">Monthly Reports</option>\n" +
                "    </select>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"booksC\">\n" +
                "        <label>Title</label>\n" +
                "        <input type=\"text\" id=\"bookTitleC\" placeholder=\"Book Title\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <label>Author</label>\n" +
                "        <input type=\"text\" id=\"bookAuthorC\" placeholder=\"Author\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <label>Description</label>\n" +
                "        <input type=\"text\" id=\"bookDescriptionC\" placeholder=\"Description\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <label>Category</label>\n" +
                "        <input type=\"text\" id=\"bookCategoryC\" placeholder=\"Category\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <label>Price in USD Dollars ($)</label>\n" +
                "        <input type=\"text\" id=\"bookPriceC\" placeholder=\"Price (00.00)\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <button onclick=\"submitCreateBook()\">Submit</button>\n" +
                "    </section>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"bookstoresC\">\n" +
                "        <!-- state city zip address -->\n" +
                "        <label>State</label>\n" +
                "        <input type=\"text\" id=\"storeStateC\" placeholder=\"State\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <label>City</label>\n" +
                "        <input type=\"text\" id=\"storeCityC\" placeholder=\"City\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <label>Zipcode</label>\n" +
                "        <input type=\"text\" id=\"storeZipC\" placeholder=\"Zipcode\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <label>Street Address</label>\n" +
                "        <input type=\"text\" id=\"storeAddressC\" placeholder=\"Street Address\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <button onclick=\"submitCreateStore()\">Submit</button>\n" +
                "    </section>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"reportsC\">\n" +
                "        <h3>Consumable</h3>\n" +
                "        <br />\n" +
                "\n" +
                "        <label>Book Title</label>\n" +
                "        <input type=\"text\" id=\"consBookInput\" placeholder=\"Book Title\">\n" +
                "\n" +
                "        <br />\n" +
                "\n" +
                "        <button onclick=\"submitCreateReport('consumable')\">Submit Consumable Report</button>\n" +
                "\n" +
                "        <br /><hr/><br />\n" +
                "\n" +
                "        <h3>Customer Visits</h3>\n" +
                "        <br />\n" +
                "\n" +
                "        <label>Customer ID</label>\n" +
                "        <input type=\"text\" id=\"custIDInput\" placeholder=\"Customer ID\">\n" +
                "\n" +
                "        <br />\n" +
                "\n" +
                "        <button onclick=\"submitCreateReport('customer_visits')\">Submit Customer Visit</button>\n" +
                "\n" +
                "        <br /><hr/><br />\n" +
                "\n" +
                "        <h3>Sales</h3>\n" +
                "        <br />\n" +
                "\n" +
                "        <label>Bookstore ID</label>\n" +
                "        <input type=\"text\" id=\"storeIDInput\" placeholder=\"Bookstore ID\">\n" +
                "\n" +
                "        <br />\n" +
                "\n" +
                "        <button onclick=\"submitCreateReport('Sales')\">Submit Sales Report</button>\n" +
                "\n" +
                "\n" +
                "\n" +
                "\n" +
                "    </section>\n" +
                "</article>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<article class=\"subpage\" id=\"read\">\n" +
                "    <h2>Browse Database Entries</h2>\n" +
                "\n" +
                "    <select id=\"ReadSelect\" onchange=\"showReadPage()\">\n" +
                "        <option>Select...</option>\n" +
                "        <option value=\"booksR\">Books</option>\n" +
                "        <option value=\"bookstoresR\">Bookstores</option>\n" +
                "        <option value=\"reportsR\">Monthly Reports</option>\n" +
                "        <option value=\"usersR\">Users</option>\n" +
                "    </select>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"booksR\">\n" +
                "\n" +
                "        <p>Search By:</p>\n" +
                "        <select id=\"searchCatsBooks\">\n" +
                "            <option value=\"author\">Author</option>\n" +
                "            <option value=\"title\">Title</option>\n" +
                "            <option value=\"category\">Genre</option>\n" +
                "        </select>\n" +
                "\n" +
                "        <input type=\"text\" id=\"bookSearchInput\"/>\n" +
                "        <br/>\n" +
                "        <button onclick=\"getSearchBooks()\">Search for Book</button>\n" +
                "        <br/>\n" +
                "        <button onclick=\"getAllBooks()\">View All Books</button>\n" +
                "    </section>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"bookstoresR\">\n" +
                "        <!-- state city zip address -->\n" +
                "\n" +
                "        <p>Search By:</p>\n" +
                "        <select id=\"searchCatsStores\">\n" +
                "            <option value=\"state\">State</option>\n" +
                "            <option value=\"city\">City</option>\n" +
                "            <option value=\"zipcode\">Zip</option>\n" +
                "            <option value=\"address\">Address</option>\n" +
                "        </select>\n" +
                "\n" +
                "        <input type=\"text\" id=\"storesSearchInput\"/>\n" +
                "        <br/>\n" +
                "        <button onclick=\"getSearchedBookstores()\">Search for Store</button>\n" +
                "        <br/>\n" +
                "        <button onclick=\"getAllBookstores()\">View All Stores</button>\n" +
                "\n" +
                "    </section>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"reportsR\">\n" +
                "\n" +
                "        <!-- year month -->\n" +
                "\n" +
                "        <p>Type of Report:</p>\n" +
                "        <select id=\"reportTypeSelect\">\n" +
                "            <option value=\"consumable\">Consumable</option>\n" +
                "            <option value=\"sales\">Sales</option>\n" +
                "            <option value=\"customer_visits\">Customer Visits</option>\n" +
                "        </select>\n" +
                "\n" +
                "        <p><input type=\"text\" id=\"repMonthSearch\"> << Month</p>\n" +
                "        <p><input type=\"text\" id=\"repYearSearch\"> << Year</p>\n" +
                "        \n" +
                "        <br/>\n" +
                "        <button onclick=\"getSearchedReports()\">Search for Report</button>\n" +
                "        <br/>\n" +
                "        <button onclick=\"getAllReports()\">View All Reports</button>\n" +
                "\n" +
                "    </section>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"usersR\">\n" +
                "\n" +
                "        <select id=\"searchCatsUsers\">\n" +
                "            <option value=\"userName\">UserName</option>\n" +
                "            <option value=\"fullName\">FullName</option>\n" +
                "        </select>\n" +
                "\n" +
                "        <input type=\"text\" id=\"usersSearchInput\"/>\n" +
                "        <br/>\n" +
                "        <button onclick=\"getSearchedUsers()\">Search for User</button>\n" +
                "        <br/>\n" +
                "        <button onclick=\"getAllUsers()\">View All Users</button>\n" +
                "\n" +
                "    </section>\n" +
                "\n" +
                "    <ul id=\"uList\"></ul>\n" +
                "\n" +
                "</article>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<article class=\"subpage\" id=\"update\">\n" +
                "    <h2>Update Database Entries</h2>\n" +
                "\n" +
                "    <select id=\"UpdateSelect\" onchange=\"showUpdatePage()\">\n" +
                "        <option>Select...</option>\n" +
                "        <option value=\"booksU\">Books</option>\n" +
                "        <option value=\"bookstoresU\">Bookstores</option>\n" +
                "    </select>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"booksU\">\n" +
                "        <label>Search with Title:</label>\n" +
                "        <input type=\"text\" id=\"bookToUpdateTitle\" placeholder=\"Book To Update\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <label>Variable to Update:</label>\n" +
                "        <select id=\"updateBookVariableSelect\">\n" +
                "            <option value=\"name\">Title</option>\n" +
                "            <option value=\"author\">Author</option>\n" +
                "            <option value=\"description\">Description</option>\n" +
                "            <option value=\"category\">Genre</option>\n" +
                "            <option value=\"price\">Price</option>\n" +
                "        </select>\n" +
                "\n" +
                "        <label>New Value:</label>\n" +
                "        <input type=\"text\" id=\"newBookUpdateValue\" placeholder=\"New Value\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <button onclick=\"submitUpdateBook()\">Submit</button>\n" +
                "    </section>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"bookstoresU\">\n" +
                "\n" +
                "        <label>Search with ID:</label>\n" +
                "        <input type=\"text\" id=\"storeToUpdateID\" placeholder=\"Book To Update\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <label>Variable to Update:</label>\n" +
                "        <select id=\"updateStoreSelect\">\n" +
                "            <option value=\"state\">State</option>\n" +
                "            <option value=\"city\">City</option>\n" +
                "            <option value=\"zipcode\">Zipcode</option>\n" +
                "            <option value=\"address\">Street Address</option>\n" +
                "        </select>\n" +
                "\n" +
                "        <label>New Value:</label>\n" +
                "        <input type=\"text\" id=\"newStoreUpdateValue\" placeholder=\"New Value\"/>\n" +
                "        <br>\n" +
                "\n" +
                "        <button onclick=\"submitUpdateStore()\">Submit</button>\n" +
                "    </section>\n" +
                "</article>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<article class=\"subpage\" id=\"delete\">\n" +
                "    <h2>Delete Database Entries</h2>\n" +
                "\n" +
                "    <select id=\"DeleteSelect\" onchange=\"showDeletePage()\">\n" +
                "        <option>Select...</option>\n" +
                "        <option value=\"booksD\">Books</option>\n" +
                "        <option value=\"bookstoresD\">Bookstores</option>\n" +
                "        <option value=\"reportsD\">Monthly Reports</option>\n" +
                "        <option value=\"usersD\">Users</option>\n" +
                "    </select>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"booksD\">\n" +
                "\n" +
                "        <label>Book Title:</label>\n" +
                "        <input type=\"text\" id=\"bookDeleteInput\" placeholder=\"Book Title\"/>\n" +
                "\n" +
                "        <br/>\n" +
                "        <button onclick=\"deleteBook()\">Delete Book</button>\n" +
                "    </section>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"bookstoresD\">\n" +
                "        <label>Bookstore Zipcode:</label>\n" +
                "        <input type=\"text\" id=\"storeDeleteInput\" placeholder=\"Bookstore Zipcode\"/>\n" +
                "\n" +
                "        <br/>\n" +
                "        <button onclick=\"deleteBookstore()\">Delete Store</button>\n" +
                "\n" +
                "    </section>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"reportsD\">\n" +
                "\n" +
                "        <label>Type of Report:</label>\n" +
                "\n" +
                "        <select id=\"reportTypeSelectD\">\n" +
                "            <option value=\"consumable\">Consumable</option>\n" +
                "            <option value=\"sales\">Sales</option>\n" +
                "            <option value=\"customer_visits\">Customer Visits</option>\n" +
                "        </select>\n" +
                "\n" +
                "        <br />\n" +
                "\n" +
                "        <label>Report Year and Month:</label>\n" +
                "        <input type=\"text\" id=\"repYearInputD\" placeholder=\"Report Year - (2023+)\"/>\n" +
                "        <input type=\"text\" id=\"repMonthInputD\" placeholder=\"Report Month - (January, February, etc...)\"/>\n" +
                "\n" +
                "        <br/>\n" +
                "        <button onclick=\"deleteReport()\">Delete Report</button>\n" +
                "\n" +
                "    </section>\n" +
                "\n" +
                "    <section class=\"crudPage\" id=\"usersD\">\n" +
                "\n" +
                "        <label>User Username:</label>\n" +
                "        <input type=\"text\" id=\"userDeleteInput\" placeholder=\"Username\"/>\n" +
                "\n" +
                "        <br/>\n" +
                "        <button onclick=\"deleteUser()\">Delete Store</button>\n" +
                "\n" +
                "    </section>\n" +
                "\n" +
                "    <p id=\"outputText\"></p>\n" +
                "</article> \n";
    }

}
