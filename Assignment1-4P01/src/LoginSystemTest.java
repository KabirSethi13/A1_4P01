import org.junit.Test;
import static org.junit.Assert.*;

public class LoginSystemTest {

    @Test
    public void testValidLogin() {
        LoginSystem loginSystem = new LoginSystem();
        String result = loginSystem.login("validUser", "validPassword");
        assertEquals("Login successful, redirecting to dashboard.", result);
    }

    @Test
    public void testInvalidLogin() {
        LoginSystem loginSystem = new LoginSystem();
        String result = loginSystem.login("invalidUser", "wrongPassword");
        assertEquals("Error: Invalid username or password.", result);
    }

    @Test
    public void testFailedAttempts() {
        LoginSystem loginSystem = new LoginSystem();

        // 1st failed attempt
        loginSystem.login("user", "wrongPassword");

        // 2nd failed attempt
        loginSystem.login("user", "wrongPassword");

        // 3rd failed attempt should trigger lockout
        String result = loginSystem.login("user", "wrongPassword");
        assertEquals("Error: User locked out due to too many failed attempts.", result);
    }

    @Test
    public void testEmpty() {
        LoginSystem loginSystem = new LoginSystem();

        // Test for empty username
        String result = loginSystem.login("", "somePassword");
        assertEquals("Error: Username cannot be empty.", result);

        // Test for empty password
        result = loginSystem.login("someUser", "");
        assertEquals("Error: Password cannot be empty.", result);
    }

}