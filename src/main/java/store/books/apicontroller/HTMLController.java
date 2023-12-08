package store.books.apicontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/get-html-state")
public class HTMLController {

    @GetMapping("/home")
    public String getHomePage() {
        return """
                <article>
                        <h1>Welcome to Laid-Back Literature Book Portal</h1>

                        <p>
                            Welcome to Laid-Back Literature, your online sanctuary for the soul-stirring world of books!
                            Immerse yourself in the enchanting realms of imagination as you explore our curated collection
                            of literary wonders. Whether you're a seasoned bibliophile or just embarking on your reading journey,
                            Laid-Back Literature is your haven for literary discovery.
                        </p>
                       \s
                        <br /><hr /><br />

                        <div class="image-container"><img src="https://i.pinimg.com/564x/e8/9f/2f/e89f2f11a54324ba84a77e5274b45285.jpg" alt="Bookstore Art"></div>
                </article>
                """;
    }

    @GetMapping("/browsebooks")
    public String getBrowseBooksPage() {
        return """
                <h2>Find the Books that are right for YOU!</h2>

                <br/><br/>

                <p>Search By:</p>
                <select id="searchCatsBooks">
                    <option value="author">Author</option>
                    <option value="title">Title</option>
                    <option value="category">Genre</option>
                </select>

                <br/><br/>

                <input type="text" id="BookSearchInput"/>
                <br/><br/>
                <button onclick="getSearchBooks()">Search for Book</button>
                <br/><br/>
                <button onclick="getAllBooks()">View All Books</button>

                <br/<br/>
                <br/<br/>

                <ul id="uList"></ul>""";
    }

    @GetMapping("/loginorregister")
    public String getLoginPage() {
        return """
                <article>
                    <h2>Login</h2>

                    <p><input type="text" id="logUnameInput"> << Username</p>
                    <p><input type="text" id="logPassInput"> << Password</p>

                    <br/><br/>

                    <button onclick="login()">Login</button>

                    <br /><hr /><br />

                    <p id="loginOutput"></p>
                </article>""";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return """
                <table>
                    <tr>
                        <td><a href="#" onclick="showSubPage('create')">Create Database Entries</a></td>
                        <td><a href="#" onclick="showSubPage('read')">Browse Database Entries</a></td>
                        <td><a href="#" onclick="showSubPage('update')">Update Database Entries</a></td>
                        <td><a href="#" onclick="showSubPage('delete')">Delete Database Entries</a></td>
                    </tr>
                </table>



                <article class="subpage" id="create">
                    <h2>Create Database Entries</h2>

                    <select id="CreateSelect" onchange="showCreatePage()">
                        <option>Select...</option>
                        <option value="booksC">Books</option>
                        <option value="bookstoresC">Bookstores</option>
                        <option value="reportsC">Monthly Reports</option>
                    </select>

                    <br /><hr /><br />

                    <section class="crudPage" id="booksC">
                        <label>Title</label>
                        <input type="text" id="bookTitleC" placeholder="Book Title"/>
                        <br>

                        <label>Author</label>
                        <input type="text" id="bookAuthorC" placeholder="Author"/>
                        <br>

                        <label>Description</label>
                        <input type="text" id="bookDescriptionC" placeholder="Description"/>
                        <br>

                        <label>Category</label>
                        <input type="text" id="bookCategoryC" placeholder="Category"/>
                        <br>

                        <label>Price in USD Dollars ($)</label>
                        <input type="text" id="bookPriceC" placeholder="Price (00.00)"/>
                        <br>

                        <button onclick="submitCreateBook()">Submit</button>
                    </section>

                    <section class="crudPage" id="bookstoresC">
                        <!-- state city zip address -->
                        <label>State</label>
                        <input type="text" id="storeStateC" placeholder="State"/>
                        <br>

                        <label>City</label>
                        <input type="text" id="storeCityC" placeholder="City"/>
                        <br>

                        <label>Zipcode</label>
                        <input type="text" id="storeZipC" placeholder="Zipcode"/>
                        <br>

                        <label>Street Address</label>
                        <input type="text" id="storeAddressC" placeholder="Street Address"/>
                        <br>

                        <button onclick="submitCreateStore()">Submit</button>
                    </section>

                    <section class="crudPage" id="reportsC">
                        <h3>Consumable</h3>
                        <br />

                        <label>Book Title</label>
                        <input type="text" id="consBookInput" placeholder="Book Title">

                        <br />

                        <button onclick="submitCreateReport('consumable')">Submit Consumable Report</button>

                        <br /><hr/><br />

                        <h3>Customer Visits</h3>
                        <br />

                        <label>Customer ID</label>
                        <input type="text" id="custIDInput" placeholder="Customer ID">

                        <br />

                        <button onclick="submitCreateReport('customer_visits')">Submit Customer Visit</button>

                        <br /><hr/><br />

                        <h3>Sales</h3>
                        <br />

                        <label>Bookstore ID</label>
                        <input type="text" id="storeIDInput" placeholder="Bookstore ID">

                        <br />

                        <button onclick="submitCreateReport('Sales')">Submit Sales Report</button>




                    </section>
                </article>



                <article class="subpage" id="read">
                    <h2>Browse Database Entries</h2>

                    <select id="ReadSelect" onchange="showReadPage()">
                        <option>Select...</option>
                        <option value="booksR">Books</option>
                        <option value="bookstoresR">Bookstores</option>
                        <option value="reportsR">Monthly Reports</option>
                        <option value="usersR">Users</option>
                    </select>

                    <br /><hr /><br />

                    <section class="crudPage" id="booksR">

                        <p>Search By:</p>
                        <select id="searchCatsBooks">
                            <option value="author">Author</option>
                            <option value="title">Title</option>
                            <option value="category">Genre</option>
                        </select>

                        <input type="text" id="bookSearchInput"/>
                        <br/>
                        <button onclick="getSearchBooks()">Search for Book</button>
                        <br/>
                        <button onclick="getAllBooks()">View All Books</button>
                    </section>

                    <section class="crudPage" id="bookstoresR">
                        <!-- state city zip address -->

                        <p>Search By:</p>
                        <select id="searchCatsStores">
                            <option value="state">State</option>
                            <option value="city">City</option>
                            <option value="zipcode">Zip</option>
                            <option value="address">Address</option>
                        </select>

                        <input type="text" id="storesSearchInput"/>
                        <br/>
                        <button onclick="getSearchedBookstores()">Search for Store</button>
                        <br/>
                        <button onclick="getAllBookstores()">View All Stores</button>

                    </section>

                    <section class="crudPage" id="reportsR">

                        <!-- year month -->

                        <p>Type of Report:</p>
                        <select id="reportTypeSelect">
                            <option value="consumable">Consumable</option>
                            <option value="sales">Sales</option>
                            <option value="customer_visits">Customer Visits</option>
                        </select>

                        <p><input type="text" id="repMonthSearch"> << Month</p>
                        <p><input type="text" id="repYearSearch"> << Year</p>

                        <br/>
                        <button onclick="getSearchedReports()">Search for Report</button>
                        <br/>
                        <button onclick="getAllReports()">View All Reports</button>

                    </section>

                    <section class="crudPage" id="usersR">

                        <select id="searchCatsUsers">
                            <option value="userName">UserName</option>
                            <option value="fullName">FullName</option>
                        </select>

                        <input type="text" id="usersSearchInput"/>
                        <br/>
                        <button onclick="getSearchedUsers()">Search for User</button>
                        <br/>
                        <button onclick="getAllUsers()">View All Users</button>

                    </section>

                    <ul id="uList"></ul>

                </article>



                <article class="subpage" id="update">
                    <h2>Update Database Entries</h2>

                    <select id="UpdateSelect" onchange="showUpdatePage()">
                        <option>Select...</option>
                        <option value="booksU">Books</option>
                        <option value="bookstoresU">Bookstores</option>
                    </select>

                    <br /><hr /><br />
                   \s
                    <section class="crudPage" id="booksU">
                        <label>Search with Title:</label>
                        <input type="text" id="bookToUpdateTitle" placeholder="Book To Update"/>
                        <br>

                        <label>Variable to Update:</label>
                        <select id="updateBookVariableSelect">
                            <option value="name">Title</option>
                            <option value="author">Author</option>
                            <option value="description">Description</option>
                            <option value="category">Genre</option>
                            <option value="price">Price</option>
                        </select>

                        <label>New Value:</label>
                        <input type="text" id="newBookUpdateValue" placeholder="New Value"/>
                        <br>

                        <button onclick="submitUpdateBook()">Submit</button>
                    </section>

                    <section class="crudPage" id="bookstoresU">

                        <label>Search with ID:</label>
                        <input type="text" id="storeToUpdateID" placeholder="Book To Update"/>
                        <br>

                        <label>Variable to Update:</label>
                        <select id="updateStoreSelect">
                            <option value="state">State</option>
                            <option value="city">City</option>
                            <option value="zipcode">Zipcode</option>
                            <option value="address">Street Address</option>
                        </select>

                        <label>New Value:</label>
                        <input type="text" id="newStoreUpdateValue" placeholder="New Value"/>
                        <br>

                        <button onclick="submitUpdateStore()">Submit</button>
                    </section>
                </article>



                <article class="subpage" id="delete">
                    <h2>Delete Database Entries</h2>

                    <select id="DeleteSelect" onchange="showDeletePage()">
                        <option>Select...</option>
                        <option value="booksD">Books</option>
                        <option value="bookstoresD">Bookstores</option>
                        <option value="reportsD">Monthly Reports</option>
                        <option value="usersD">Users</option>
                    </select>

                    <br /><hr /><br />

                    <section class="crudPage" id="booksD">

                        <label>Book Title:</label>
                        <input type="text" id="bookDeleteInput" placeholder="Book Title"/>

                        <br/>
                        <button onclick="deleteBook()">Delete Book</button>
                    </section>

                    <section class="crudPage" id="bookstoresD">
                        <label>Bookstore Zipcode:</label>
                        <input type="text" id="storeDeleteInput" placeholder="Bookstore Zipcode"/>

                        <br/>
                        <button onclick="deleteBookstore()">Delete Store</button>

                    </section>

                    <section class="crudPage" id="reportsD">

                        <label>Type of Report:</label>

                        <select id="reportTypeSelectD">
                            <option value="consumable">Consumable</option>
                            <option value="sales">Sales</option>
                            <option value="customer_visits">Customer Visits</option>
                        </select>

                        <br />

                        <label>Report Year and Month:</label>
                        <input type="text" id="repYearInputD" placeholder="Report Year - (2023+)"/>
                        <input type="text" id="repMonthInputD" placeholder="Report Month - (January, February, etc...)"/>

                        <br/>
                        <button onclick="deleteReport()">Delete Report</button>

                    </section>

                    <section class="crudPage" id="usersD">

                        <label>User Username:</label>
                        <input type="text" id="userDeleteInput" placeholder="Username"/>

                        <br/>
                        <button onclick="deleteUser()">Delete Store</button>

                    </section>

                    <p id="outputText"></p>
                </article>
                """;
    }

}
