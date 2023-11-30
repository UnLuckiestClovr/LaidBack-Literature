// Login Script

function login(username, password) {
    var httpPath = "http://localhost:42070/auth/login/" + username + "/" + password
    var xmlHttp = new XMLHttpRequest();

    xmlHttp.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            sessionStorage.setItem("loggedIn", (JSON.parse(this.responseText).logBool));
            sessionStorage.setItem("authHeaderValue", ("Basic " + btoa(username + ":" + password)))

            showAuthPriv(JSON.parse(this.responseText).adBool);
        } else {

        }
    }

    xmlHttp.open("GET", httpPath, true)
    xmlHttp.send();
}

function anonLoginandLogout() {
    sessionStorage.setItem("authHeaderValue", "")
}

function showAuthPriv(bool) {
    let priv = document.getElementById("adminCRUD")
    if (bool === true) {
        priv.style.display = "block";
    } else {
        priv.style.display = "none";
    }
}

// ---------------------------------------------------------------------------------------------------------------------

// PageChange Script

function getHTMLPage(pageName) {
    var httpPath = "http://localhost:42070/get-html-state/" + pageName.toLowerCase()
    var xmlHttp = new XMLHttpRequest();

    console.log("Attempting State Change : " , pageName);

    xmlHttp.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            document.getElementById("blockContent").innerHTML = this.responseText;
        } else {
            console.log("State Change Failed");
        }
    }
    xmlHttp.open("GET", httpPath, true)

    if(pageName === "admin") {
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
    }

    xmlHttp.send();
}

// ---------------------------------------------------------------------------------------------------------------------

// Browse Books Page
try {

    function renderBookList(books) {
        const entry_list = document.getElementById("uList");
        entry_list.innerHTML = "";

        for (var entry of books) {
            var entryHtml = '<li>' +
                '<p>Book Title >> ' + entry.name + '</p>' +
                '<p>Book Genre >> ' + entry.category + '</p>' +
                '<p>Book Author >> ' + entry.author + '</p>' +
                '<p>Book Description >> ' + entry.description + '</p>' +
                '<p>Book Price >> $' + entry.price + '</p>' +
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

        xmlHttp.open("GET", "http://localhost:42070/lb-literature/get-all/books", true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

    function getSearchBooks() {
        const searchInput = document.getElementById("searchInput").value.toString();
        const catInput = document.getElementById("searchCatsBooks").value.toString();
        var xmlHttp = new XMLHttpRequest();

        xmlHttp.onreadystatechange = function () {
            if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
                var books = JSON.parse(this.responseText)
                console.log(books)
                renderBookList(books)
            }
        }

        xmlHttp.open("GET", ("http://localhost:42070/lb-literature/get/books/" + catInput + "/" + searchInput), true);
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
        xmlHttp.send();
    }

} catch (error) {}
