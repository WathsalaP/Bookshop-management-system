function validate() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;

    if (username === "Sanduni" && password === "123") {
        window.location.replace("file:///D:/Dekstop/PahanEdu/pages/dashboard.html");
        return false; // Prevent form submission
    } else {
        alert("Login failed");
        return false; // Prevent form submission
    }
}