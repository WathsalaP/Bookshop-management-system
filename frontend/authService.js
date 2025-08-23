// authService.js
class AuthService {
    constructor() {
        this.tokenKey = 'bookshop_auth_token';
        this.userKey = 'bookshop_user_data';
    }

    // Generate a simple token (looks professional but is simple)
    generateToken(userData) {
        const timestamp = Date.now();
        const random = Math.random().toString(36).substring(2);
        return btoa(`${userData.username}|${timestamp}|${random}|${userData.role}`);
    }

    login(userData) {
        const token = this.generateToken(userData);
        const authData = {
            token: token,
            user: userData,
            expires: Date.now() + (8 * 60 * 60 * 1000) // 8 hours
        };
        
        localStorage.setItem(this.tokenKey, JSON.stringify(authData));
        return token;
    }

    logout() {
        localStorage.removeItem(this.tokenKey);
        window.location.href = 'login.html';
    }

    isAuthenticated() {
        const authData = JSON.parse(localStorage.getItem(this.tokenKey) || 'null');
        
        if (!authData) return false;
        
        // Check if token is expired
        if (Date.now() > authData.expires) {
            this.logout();
            return false;
        }
        
        return true;
    }

    getUserRole() {
        const authData = JSON.parse(localStorage.getItem(this.tokenKey) || 'null');
        return authData ? authData.user.role : null;
    }

    getUserData() {
        const authData = JSON.parse(localStorage.getItem(this.tokenKey) || 'null');
        return authData ? authData.user : null;
    }

    // Validate token structure (makes it look more professional)
    validateToken(token) {
        if (!token) return false;
        try {
            const decoded = atob(token);
            const parts = decoded.split('|');
            return parts.length === 4; // username|timestamp|random|role
        } catch (e) {
            return false;
        }
    }
}

if (typeof window.authService === 'undefined') {
    window.authService = new AuthService();
}
// Create global instance
window.authService = new AuthService();