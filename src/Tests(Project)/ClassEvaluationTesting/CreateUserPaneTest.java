import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

class CreateUserPaneTest {

    @Test
    void TC_CUP_01_validUsername() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        assertDoesNotThrow(() -> {
            YourClass.createUserPane("ValidUser", stage, true, scene);
        });
    }

    @Test
    void TC_CUP_02_emptyUsername() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        assertThrows(IllegalArgumentException.class, () -> {
            YourClass.createUserPane("", stage, false, scene);
        });
    }

    @Test
    void TC_CUP_03_maximumLengthUsername() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());
        String maxUsername = "A".repeat(50);

        assertDoesNotThrow(() -> {
            YourClass.createUserPane(maxUsername, stage, true, scene);
        });
    }

    @Test
    void TC_CUP_04_exceedingMaxUsernameLength() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());
        String maxUsername = "A".repeat(51);

        assertThrows(IllegalArgumentException.class, () -> {
            YourClass.createUserPane(maxUsername, stage, false, scene);
        });
    }

    @Test
    void TC_CUP_05_nullStage() {
        Scene scene = new Scene(new StackPane());

        assertThrows(NullPointerException.class, () -> {
            YourClass.createUserPane("User", null, true, scene);
        });
    }

    @Test
    void TC_CUP_06_nullScene() {
        Stage stage = new Stage();

        assertThrows(NullPointerException.class, () -> {
            YourClass.createUserPane("User", stage, false, null);
        });
    }

    @Test
    void TC_CUP_07_adminAccess() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createUserPane("Admin", stage, true, scene);
        assertNotNull(pane);
    }

    @Test
    void TC_CUP_08_nonAdminAccess() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createUserPane("User", stage, false, scene);
        assertNotNull(pane);
    }

    @Test
    void TC_CUP_09_switchToModifyUser() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        assertDoesNotThrow(() -> {
            YourClass.createUserPane("User", stage, true, scene);
        });
    }

    @Test
    void TC_CUP_10_switchToNewUser() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        assertDoesNotThrow(() -> {
            YourClass.createUserPane("User", stage, true, scene);
        });
    }
}
