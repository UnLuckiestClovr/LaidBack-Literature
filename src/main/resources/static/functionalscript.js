let loggedIn = false;
let authHeaderValue = null;

function login(username, password) {
    authHeaderValue = "Basic " + btoa(username + ":" + password)


    var httpPath = "http://localhost:42070/auth/login/" + username + "/" + password
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open("GET", httpPath, true)
    xmlHttp.send();
    // ON READY STATE CHANGE ---------------------------------------------------------
    xmlHttp.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            loggedIn = JSON.parse(this.responseText).logBool;

            localStorage.setItem("loggedIn", loggedIn);
        }
    }
}

function anonLogin() {
    authHeaderValue = "Basic " + btoa("anonymous:anon_user")
}


//PageChange

function getHTMLPage(pageName) {
    var httpPath = "http://localhost:42070/get-html-state/" + pageName.toLowerCase()
    var xmlHttp = new XMLHttpRequest();

    // ON READY STATE CHANGE ---------------------------------------------------------
    xmlHttp.onreadystatechange = function () {
        if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
            loggedIn = JSON.parse(this.responseText).logBool;
            adLog = JSON.parse(this.responseText).adBool;
        }
    }
    xmlHttp.open("GET", httpPath, true)

    if(pageName === "admin") {
        xmlHttp.setRequestHeader("Authorization", authHeaderValue);
    }

    xmlHttp.send();
}