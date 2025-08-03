function validate() {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    if (username === "Sanduni" && password === "123") {
        window.location.replace("index.html"); // Redirect to the new index.html
        return false; // Prevent form from submitting the default way
    } else {
        alert("Login failed. Please check your credentials.");
        return false; // Prevent reload on failure
    }
}
