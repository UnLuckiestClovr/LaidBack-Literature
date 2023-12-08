// Login Scripts --------------------------------------------------------------------------------------------------------

function login() {
    let username = document.getElementById("logUnameInput").value.toString();
    let password = document.getElementById("logPassInput").value.toString();

    var httpPath = "/lb-literature/auth/" + username + "/" + password + "/login"
    var xmlHttp = new XMLHttpRequest();

    xmlHttp.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            var loginSuccess = JSON.parse(this.responseText).logBool;

            console.log(loginSuccess, JSON.parse(this.responseText).adBool)
            if(loginSuccess) {
                sessionStorage.setItem("loggedIn", (JSON.parse(this.responseText).logBool));
                sessionStorage.setItem("authHeaderValue", ("Basic " + btoa(username + ":" + password)))

                toggleLoginLogoutBTNs(JSON.parse(this.responseText).logBool);
                getHTMLPage("home")
                showAuthPriv(JSON.parse(this.responseText).adBool);
                document.getElementById("loginOutput").innerHTML = ""

            } else {
                document.getElementById("loginOutput").innerHTML = "Login Unsuccessful : Invalid Credentials"
            }

        } else {
            document.getElementById("loginOutput").innerHTML = "Login Unsuccessful : Server Error"
        }
    }

    xmlHttp.open("GET", httpPath, true);
    xmlHttp.send();
}

function Logout() {
    sessionStorage.setItem("authHeaderValue", "")
    toggleLoginLogoutBTNs(false);
}

function toggleLoginLogoutBTNs(loggingIn) {
    if (loggingIn) {
        document.getElementById("loginBTN").style.display = "none";
        document.getElementById("logoutBTN").style.display = "block";
    } else {
        document.getElementById("loginBTN").style.display = "block";
        document.getElementById("logoutBTN").style.display = "none";
    }
}

function showAuthPriv(bool) {
    let priv = document.getElementById("adminCRUD")
    if (bool === true) {
        priv.style.display = "block";
    } else {
        priv.style.display = "none";
    }
}


// PageChange Scripts ---------------------------------------------------------------------------------------------------

try {
    function getHTMLPage(pageName) {
        var httpPath = "/get-html-state/" + pageName.toLowerCase()
        var xmlHttp = new XMLHttpRequest();

        console.log("Attempting State Change : " , pageName);

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                document.getElementById("blockContent").innerHTML = this.responseText;
            }
        }
        xmlHttp.open("GET", httpPath, true)

        if(pageName === "admin") {
            xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        }

        xmlHttp.send();
    }
} catch (error) {
    console.log("HTML State Changer not Set up Correctly")
}



// Browse Books Scripts ---------------------------------------------------------------------------------------------------

try {

    function renderBookList(books) {
        const entry_list = document.getElementById("uList");
        entry_list.innerHTML = "";

        for (var entry of books) {
            var entryHtml = '<li>' +
                '<h3>' + entry.name + '</h3>' +
                '<p>Genre >> ' + entry.category + '</p>' +
                '<p>Author >> ' + entry.author + '</p>' +
                '<p>Book Description >> ' + entry.description + '</p>' +
                '<p>Price >> $' + entry.price + '</p>' +
                '</li>';
            entry_list.innerHTML += entryHtml;
        }
    }

    function getAllBooks() {
        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                var books = JSON.parse(this.responseText)
                console.log(books)
                renderBookList(books)
            }
        }

        xmlHttp.open("GET", "/lb-literature/get-all/books", true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function getSearchBooks() {
        const searchInput = document.getElementById("bookSearchInput").value.toString();
        const catInput = document.getElementById("searchCatsBooks").value.toString();
        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                var books = JSON.parse(this.responseText)
                console.log(books)
                renderBookList(books)
            }
        }

        xmlHttp.open("GET", ("/lb-literature/get/books/" + catInput + "/" + searchInput), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

} catch (error) {}

// Admin Page Scripts --------------------------------------------------------------------------------------------------

try {

    function showSubPage(subpageID) {
        // Hide all pages
        const pages = document.querySelectorAll('.subpage');
        pages.forEach(page => {
            page.style.display = 'none';
        });

        // Show the selected page
        const selectedPage = document.getElementById(subpageID);
        if (selectedPage) {
            selectedPage.style.display = 'block';
        }
    }

    function showCreatePage() {
        let crudPageID = document.getElementById("CreateSelect").value.toString();

        // Hide all pages
        const pages = document.querySelectorAll('.crudPage');
        pages.forEach(page => {
            page.style.display = 'none';
        });

        // Show the selected page
        const selectedPage = document.getElementById(crudPageID);
        if (selectedPage) {
            selectedPage.style.display = 'block';
        }
    }

    function showReadPage() {
        let crudPageID = document.getElementById("ReadSelect").value.toString();

        // Hide all pages
        const pages = document.querySelectorAll('.crudPage');
        pages.forEach(page => {
            page.style.display = 'none';
        });

        // Show the selected page
        const selectedPage = document.getElementById(crudPageID);
        if (selectedPage) {
            selectedPage.style.display = 'block';
        }
    }

    function showUpdatePage() {
        let crudPageID = document.getElementById("UpdateSelect").value.toString();

        // Hide all pages
        const pages = document.querySelectorAll('.crudPage');
        pages.forEach(page => {
            page.style.display = 'none';
        });

        // Show the selected page
        const selectedPage = document.getElementById(crudPageID);
        if (selectedPage) {
            selectedPage.style.display = 'block';
        }
    }

    function showDeletePage() {
        let crudPageID = document.getElementById("DeleteSelect").value.toString();

        // Hide all pages
        const pages = document.querySelectorAll('.crudPage');
        pages.forEach(page => {
            page.style.display = 'none';
        });

        // Show the selected page
        const selectedPage = document.getElementById(crudPageID);
        if (selectedPage) {
            selectedPage.style.display = 'block';
        }
    }

    //Render Scripts AdPage

    function renderBookstores(items) {
        const entry_list = document.getElementById("uList");
        entry_list.innerHTML = "";

        for (var entry of items) {
            var entryHtml = '<li>' +
                '<p>State >>' + entry.state + '</p>' +
                '<p>City >> ' + entry.city + '</p>' +
                '<p>ZipCode >> ' + entry.zipcode + '</p>' +
                '<p>Address >> ' + entry.address + '</p>' +
                '</li>';
            entry_list.innerHTML += entryHtml;
        }
    }

    function renderConsReports(items) {
        const entry_list = document.getElementById("uList");
        entry_list.innerHTML = "";

        for (var entry of items) {
            var entryHtml = '<li>' +
                '<p>Timeframe >> ' + entry.month + '-' + entry.year + '</p>' +
                '<p>Book Title >> ' + entry.bookTitle + '</p>' +
                '<p>Total Number Sold >> ' + entry.numSold + '</p>' +
                '</li>';
            entry_list.innerHTML += entryHtml;
        }
    }

    function renderCustReports(items) {
        const entry_list = document.getElementById("uList");
        entry_list.innerHTML = "";

        for (var entry of items) {
            var entryHtml = '<li>' +
                '<p>Timeframe >> ' + entry.month + '-' + entry.year + '</p>' +
                '<p>CustomerID >> ' + entry.customerID + '</p>' +
                '<p>Total Number of Visits >> ' + entry.numVisits + '</p>' +
                '</li>';
            entry_list.innerHTML += entryHtml;
        }
    }

    function renderSalesReports(items) {
        const entry_list = document.getElementById("uList");
        entry_list.innerHTML = "";

        for (var entry of items) {
            var entryHtml = '<li>' +
                '<p>Timeframe >> ' + entry.month + '-' + entry.year + '</p>' +
                '<p>Bookstore ID >> ' + entry.bookstoreID + '</p>' +
                '<p>Total Books Sold >> ' + entry.bookSales + '</p>' +
                '</li>';
            entry_list.innerHTML += entryHtml;
        }
    }

    function renderUsers(users) {
        const entry_list = document.getElementById("uList");
        entry_list.innerHTML = "";

        for (var entry of users) {
            var entryHtml = '<li>' +
                '<p>Full Name >> ' + entry.userFullName + '</p>' +
                '<p>Username >> ' + entry.username + '</p>' +
                '</li>';
            entry_list.innerHTML += entryHtml;
        }
    }

} catch (error) {}


// Admin CRUD

try {

    //Read

    function getAllBookstores() {
        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                var stores = JSON.parse(this.responseText)
                console.log(stores)
                renderBookstores(stores)
            }
        }

        xmlHttp.open("GET", "/lb-literature/get-all/bookstores", true);
        //xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function getSearchedBookstores() {
        const searchInput = document.getElementById("storesSearchInput").value.toString();
        const catInput = document.getElementById("searchCatsStores").value.toString();
        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                var stores = JSON.parse(this.responseText)
                renderBookstores(stores)
            }
        }

        xmlHttp.open("GET", ("/lb-literature/get/bookstores/" + catInput + "/" + searchInput), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function getAllUsers() {
        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                let users = JSON.parse(this.responseText)
                renderUsers(users)
            }
        }

        xmlHttp.open("GET", "/lb-literature/get-all/users", true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function getSearchedUsers() {
        const searchInput = document.getElementById("usersSearchInput").value.toString();
        const catInput = document.getElementById("searchCatsUsers").value.toString();
        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                var users = JSON.parse(this.responseText)
                renderUsers(users)
            }
        }

        xmlHttp.open("GET", ("/lb-literature/get/users/" + catInput + "/" + searchInput), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function getAllReports() {
        let repType = document.getElementById("reportTypeSelect").value.toString();
        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                var stores = JSON.parse(this.responseText)
                console.log(stores);
                switch (repType) {
                    case "consumable":
                        renderConsReports(stores);
                        break;
                    case "customer_visits":
                        renderCustReports(stores);
                        break;
                    case "sales":
                        renderSalesReports(stores);
                        break;
                }
            }
        }

        xmlHttp.open("GET", ("/lb-reports/get-all-reports/" + repType), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function getSearchedReports() {
        const type = document.getElementById("reportTypeSelect").value.toString();

        console.log(type);

        switch (type) {
            case "consumable":
                getSearchedConsReports();
                break;
            case "customer_visits":
                getSearchedCustVisitsReports();
                break;
            case "sales":
                getSearchedSalesReports();
                break;
        }
    }

    function getSearchedCustVisitsReports() {
        let month = document.getElementById("repMonthSearch").value.toString().toUpperCase();
        let yearSTR = document.getElementById("repYearSearch").value.toString();

        if (month === "") {
            month = "0"
        }
        if (yearSTR === "") {
            yearSTR = "0"
        }

        let yearINT = parseInt(yearSTR);

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                var reps = JSON.parse(this.responseText)
                renderCustReports(reps)
            }
        }

        xmlHttp.open("GET", ("/lb-reports/search/customer_visits/" + yearINT + "/" + month), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function getSearchedSalesReports() {
        let month = document.getElementById("repMonthSearch").value.toString().toUpperCase();
        let yearSTR = document.getElementById("repYearSearch").value.toString();

        if (month === "") {
            month = "0"
        }
        if (yearSTR === "") {
            yearSTR = "0"
        }

        let yearINT = parseInt(yearSTR);

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                var reps = JSON.parse(this.responseText)
                renderSalesReports(reps)
            }
        }

        xmlHttp.open("GET", ("/lb-reports/search/sales/" + yearINT + "/" + month), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function getSearchedConsReports() {
        let month = document.getElementById("repMonthSearch").value.toString().toUpperCase();
        let yearSTR = document.getElementById("repYearSearch").value.toString();

        if (month === "") {
            month = "0"
        }
        if (yearSTR === "") {
            yearSTR = "0"
        }

        let yearINT = parseInt(yearSTR);

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                var reps = JSON.parse(this.responseText)
                renderConsReports(reps)
            }
        }

        xmlHttp.open("GET", ("/lb-reports/search/consumable/" + yearINT + "/" + month), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function submitCreateBook() {
        var title = document.getElementById("bookTitleC").value.toString();
        var author = document.getElementById("bookAuthorC").value.toString();
        var description = document.getElementById("bookDescriptionC").value.toString();
        var bookCat = document.getElementById("bookCategoryC").value.toString();
        var price = parseFloat(document.getElementById("bookPriceC").value.toString());

        let json = "{" +
            "\"name\" : \"" + title +"\"" +
            "\"author\" : \"" + author + "\"" +
            "\"description\" : \""+ description + "\"" +
            "\"category\" : \"" + bookCat + "\"" +
            "\"price\" :" + price +
            "}"

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                document.getElementById("outputText").innerHTML = this.responseText.toString();
            }
        }

        xmlHttp.open("POST", ("/lb-literature/add/book"), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send(json);
    }

    function submitCreateStore() {
        var state = document.getElementById("storeStateC").value.toString();
        var city = document.getElementById("storeCityC").value.toString();
        var zipcode = document.getElementById("storeZipC").value.toString();
        var address = document.getElementById("storeAddressC").value.toString();

        let json = "{" +
            "\"state\" : \"" + state +"\"" +
            "\"city\" : \"" + city + "\"" +
            "\"zipcode\" : \""+ zipcode + "\"" +
            "\"address\" : \"" + address + "\"" +
            "}"

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                document.getElementById("outputText").innerHTML = this.responseText.toString();
            }
        }

        xmlHttp.open("POST", ("/lb-literature/add/bookstore"), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send(json);
    }

    function submitCreateReport(repType) {
        var stringvalue = "";

        switch (repType) {
            case "consumable":
                stringvalue = document.getElementById("consBookInput").value.toString();
                break;
            case "customer_visits":
                stringvalue = document.getElementById("custIDInput").value.toString();
                break;
            case "sales":
                stringvalue = document.getElementById("storeIDInput").value.toString();
                break;
        }

        console.log("Input : ", stringvalue)

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                document.getElementById("outputText").innerHTML = this.responseText.toString();
            }
        }

        xmlHttp.open("POST", ("/lb-reports/add/" + repType + "/" + stringvalue), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function submitUpdateBook() {

        var json = {
            "docName" : document.getElementById("bookToUpdateTitle").value.toString(),
            "keyValue" : document.getElementById("updateBookVariableSelect").value.toString(),
            "newValue" : document.getElementById("newBookUpdateValue").value.toString()
        }

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                document.getElementById("outputText").innerHTML = "Update Successful!";
            }
        }

        xmlHttp.open("PATCH", ("/lb-literature/update/book"), true);
        xmlHttp.setRequestHeader('Content-Type', 'application/json'); // Set Content-Type
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send(JSON.stringify(json));
    }

    function submitUpdateStore() {

        var jsonString = {
            "docName" : document.getElementById("storeToUpdateID").value.toString(),
            "keyValue" : document.getElementById("updateStoreSelect").value.toString(),
            "newValue" : document.getElementById("newStoreUpdateValue").value.toString()
            }

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                console.log("Update Successful")
            }
        }

        xmlHttp.open("PATCH", ("/lb-literature/update/bookstore"), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send(JSON.stringify(jsonString));
    }

    function deleteBook() {
        var search = document.getElementById("bookDeleteInput").value.toString();

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                document.getElementById("outputText").innerHTML = "Deletion Successful!";
            }
        }

        xmlHttp.open("DELETE", ("/lb-literature/delete/book/" + search), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function deleteBookstore() {
        var search = document.getElementById("storeDeleteInput").value.toString();

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                document.getElementById("outputText").innerHTML = "Deletion Successful!";
            }
        }

        xmlHttp.open("DELETE", ("/lb-literature/delete/bookstore/" + search), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function deleteReport() {
        var type = document.getElementById("reportTypeSelectD").value.toString();
        var searchYear = document.getElementById("repYearInputD").value.toString();
        var searchMonth = document.getElementById("repMonthInputD").value.toString();

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                document.getElementById("outputText").innerHTML = "Deletion Successful!";
            }
        }

        xmlHttp.open("DELETE", ("/lb-reports/delete/" + type + "/" + searchYear + "/" + searchMonth), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function deleteUser() {
        var search = document.getElementById("userDeleteInput").value.toString();

        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                document.getElementById("outputText").innerHTML = "Deletion Successful!";
            }
        }

        xmlHttp.open("DELETE", ("/lb-literature/delete/user/" + search), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }


} catch (error) {}
