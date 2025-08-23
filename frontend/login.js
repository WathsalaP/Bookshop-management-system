async function validate(event) {
    event.preventDefault();
    console.log('Login function started');
    
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();
    console.log('Username:', username, 'Password:', password);

    // Admin login
    if (username === "Sanduni" && password === "123") {
        console.log('Admin login successful');
        window.authService.login({
            username: username,
            role: 'admin'
        });
        window.location.href = "SystemUser.html";
        return;
    } else {
        alert("Invalid admin credentials");
    }

    // Regular user login
    try {
        console.log('Trying regular user login');
        const response = await fetch('http://localhost:8083/api/v1/getusers');
        const users = await response.json();
        console.log('Users from API:', users);
        
        const matchedUser = users.find(user => 
            user.username === username && user.password === password
        );

        if (matchedUser) {
            console.log('User login successful:', matchedUser);
            window.authService.login({
                username: matchedUser.username,
                role: 'user'
            });
            window.location.href = "customer-details.html";
        } else {
            console.log('No matching user found');
            alert("Invalid username or password");
        }
    } catch (error) {
        console.error('Login error:', error);
        alert("Login failed. Please try again.");
    }
}