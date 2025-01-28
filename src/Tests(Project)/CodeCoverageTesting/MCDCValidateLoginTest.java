import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MCDCValidateLoginTest {

    @Test
    void TC1_allTrue() {
        assertTrue(AuthService.validateLogin("admin", "adminPass", true));
    }

    @Test
    void TC2_onlyUsernameFalse() {
        assertFalse(AuthService.validateLogin(null, "adminPass", true));
    }

    @Test
    void TC3_onlyPasswordFalse() {
        assertFalse(AuthService.validateLogin("admin", "wrongPass", true));
    }

    @Test
    void TC4_onlyIsAdminFalse() {
        assertFalse(AuthService.validateLogin("admin", "adminPass", false));
    }

    @Test
    void TC5_usernameEmptyAndPasswordValid() {
        assertFalse(AuthService.validateLogin("", "adminPass", true));
    }

    @Test
    void TC6_usernameValidAndPasswordShort() {
        assertFalse(AuthService.validateLogin("admin", "short", true));
    }

    @Test
    void TC7_validNonAdminLogin() {
        assertTrue(AuthService.validateLogin("user", "userPass", false));
    }

    @Test
    void TC8_invalidNonAdminLogin() {
        assertFalse(AuthService.validateLogin("user", "wrongPass", false));
    }
}
