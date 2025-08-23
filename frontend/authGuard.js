// authGuard.js
document.addEventListener('DOMContentLoaded', function() {
    // Get current page name
    const currentPage = window.location.pathname.split('/').pop();
    
    // Skip authentication check for login page and help page
    if (currentPage === 'login.html' || currentPage === 'help.html') {
        return; // Don't check auth for these pages
    }
    
    // Check if authService exists and user is authenticated
    if (typeof window.authService === 'undefined' || !window.authService.isAuthenticated()) {
        alert('Please login first to access this page!');
        window.location.href = 'login.html';
        return;
    }

    // Optional: Role-based access check
    const userRole = window.authService.getUserRole();
    if (currentPage === 'SystemUser.html' && userRole !== 'admin') {
        alert('Access denied! Admin privileges required.');
        window.location.href = 'customer-details.html';
    }

    // ✅ Navbar role-based visibility
    const systemUserLink = document.querySelector('a[href="SystemUser.html"]');
    if (systemUserLink && userRole !== 'admin') {
        systemUserLink.parentElement.style.display = 'none'; // hide the <li>
    }

    // ✅ Replace "Logout" link to actually log out
    const logoutLink = document.getElementById('logout-link');
    if (logoutLink) {
        logoutLink.addEventListener('click', function(e) {
            e.preventDefault();
            window.authService.logout();
        });
    }
});