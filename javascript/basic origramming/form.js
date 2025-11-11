
// validate username and password of the form
function validateForm() {
    var username = document.forms["myForm"]["username"].value;
    var password = document.forms["myForm"]["password"].value;
    if (username == "" && password == "") {
        alert("Username and password must be filled out");
        return false;
    }
    else if (username == "" && password != "") {
        alert("usernmae must");
        return false;
    }
    else if (username != "" && password.length<8) {
        alert("password must be at least 8 characters long");
        return false;
    }
    else{
        alert("Logged in successfully");
        return true;
    }
}   
