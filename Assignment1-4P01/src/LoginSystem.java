public class LoginSystem {

    private static final String VALID_USERNAME = "validUser";
    private static final String VALID_PASSWORD = "validPassword";
    private static final int MAX_ATTEMPTS = 3;

    // Track failed login attempts and lockout status
    private int failedAttempts = 0;
    private boolean isLocked = false; // Track if the user is locked

    public String login(String username, String password) {
        // If user is already locked, deny access immediately
        if (isLocked) {
            return "Error: User locked out due to too many failed attempts.";
        }

        // Check for empty input
        if (username == null || username.isEmpty()) {
            return "Error: Username cannot be empty.";
        }
        if (password == null || password.isEmpty()) {
            return "Error: Password cannot be empty.";
        }

        // Validate credentials
        if (username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD)) {
            // Reset failed attempts on successful login
            resetFailedAttempts();
            return "Login successful, redirecting to dashboard.";
        } else {
            // Increment failed attempts on invalid login
            incrementFailedAttempts();
            if (failedAttempts >= MAX_ATTEMPTS) {
                isLocked = true;  // Lock the user out after 3 failed attempts
                return "Error: User locked out due to too many failed attempts.";
            }
            return "Error: Invalid username or password.";
        }
    }

    // Increment the failed login attempts
    private void incrementFailedAttempts() {
        failedAttempts++;
    }

    // Reset the failed login attempts after a successful login
    private void resetFailedAttempts() {
        failedAttempts = 0;
    }
}
