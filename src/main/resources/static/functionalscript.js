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

    xmlHttp.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            loggedIn = JSON.parse(this.responseText).logBool;
            adLog = JSON.parse(this.responseText).adBool;
        }
    }
    xmlHttp.open("GET", httpPath, true)

    if(pageName === "admin") {
        xmlHttp.setRequestHeader("Authorization", sessionStorage.getItem("authHeaderValue"));
    }

    xmlHttp.send();
}

// ---------------------------------------------------------------------------------------------------------------------

