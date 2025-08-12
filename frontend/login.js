async function validate(event) {
    event.preventDefault(); // Prevent form submission
    
    // Get input values (using your existing IDs)
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    // 1. First check hard-coded admin credentials
    if (username === "Sanduni" && password === "123") {
        window.location.href = "SystemUser.html"; // Redirect to admin page
        return true;
    }

    // 2. Check regular users against API
    try {
        const response = await fetch('http://localhost:8083/api/v1/getusers');
        
        if (!response.ok) {
            throw new Error('Failed to fetch users');
        }

        const users = await response.json();

        // Find matching user (exact match)
        const matchedUser = users.find(user => 
            user.username === username && 
            user.password === password
        );

        if (matchedUser) {
            window.location.href = "customer-details.html"; // Redirect to user page
            return true;
        } else {
            alert("Invalid username or password");
            return false;
        }
    } catch (error) {
        console.error("Login error:", error);
        alert("Error connecting to server. Please try again later.");
        return false;
    }
}

// Attach event listener to your form
document.querySelector('form').addEventListener('submit', validate);