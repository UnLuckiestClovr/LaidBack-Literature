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