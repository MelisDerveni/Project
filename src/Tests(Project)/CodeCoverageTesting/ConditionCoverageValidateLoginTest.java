import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConditionCoverageValidateLoginTest {

    @Test
    void TC1_allConditionsTrue() {
        assertTrue(AuthService.validateLogin("admin", "adminPass", true));
    }

    @Test
    void TC2_usernameNull() {
        assertFalse(AuthService.validateLogin(null, "userPass", false));
    }

    @Test
    void TC3_emptyUsername() {
        assertFalse(AuthService.validateLogin("", "userPass", false));
    }

    @Test
    void TC4_passwordNull() {
        assertFalse(AuthService.validateLogin("user", null, false));
    }

    @Test
    void TC5_passwordTooShort() {
        assertFalse(AuthService.validateLogin("user", "short", false));
    }

    @Test
    void TC6_invalidAdminCredentials() {
        assertFalse(AuthService.validateLogin("admin", "wrongPass", true));
    }

    @Test
    void TC7_validUserLogin() {
        assertTrue(AuthService.validateLogin("user", "userPass", false));
    }

    @Test
    void TC8_invalidUserLogin() {
        assertFalse(AuthService.validateLogin("user", "wrongPass", false));
    }
}
