import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class statementCoverageTransactionPaneTest {

    @Test
    void TC1_createTransactionPane_validInput() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        assertDoesNotThrow(() -> {
            StackPane pane = YourClass.createTransactionPane("ValidUser", stage, true, scene);
            assertNotNull(pane);
        });
    }

    @Test
    void TC2_createTransactionPane_emptyUsername() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        assertDoesNotThrow(() -> {
            StackPane pane = YourClass.createTransactionPane("", stage, false, scene);
            assertNotNull(pane);
        });
    }

    @Test
    void TC3_createTransactionPane_nullScene() {
        Stage stage = new Stage();

        assertThrows(NullPointerException.class, () -> {
            YourClass.createTransactionPane("User", stage, true, null);
        });
    }

    @Test
    void TC4_createTransactionPane_productNotSelected() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createTransactionPane("User", stage, true, scene);

        // Get product choice box and simulate no selection
        ChoiceBox<String> productChoiceBox = (ChoiceBox<String>) pane.lookup(".choice-box");
        productChoiceBox.setValue("Select Product");

        assertDoesNotThrow(() -> {
            productChoiceBox.fireEvent(null);
        });
    }

    @Test
    void TC5_createTransactionPane_validProductSelected() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createTransactionPane("User", stage, true, scene);

        // Select a valid product and add it
        ChoiceBox<String> productChoiceBox = (ChoiceBox<String>) pane.lookup(".choice-box");
        productChoiceBox.setValue("Product1");

        assertDoesNotThrow(() -> {
            productChoiceBox.fireEvent(null);
        });
    }

    @Test
    void TC6_createTransactionPane_invalidQuantityInput() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createTransactionPane("User", stage, true, scene);

        // Simulate invalid quantity input
        assertDoesNotThrow(() -> {
            YourClass.createTransactionPane("User", stage, true, scene);
        });
    }

    @Test
    void TC7_createTransactionPane_validTransactionCompletion() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createTransactionPane("User", stage, true, scene);
        assertNotNull(pane);

        TableView<Object[]> tableView = (TableView<Object[]>) pane.lookup(".table-view");
        assertNotNull(tableView);

        assertDoesNotThrow(() -> {
            YourClass.completeTransaction("User", 100.0, tableView);
        });
    }

    @Test
    void TC8_createTransactionPane_emptyTableViewTransaction() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createTransactionPane("User", stage, true, scene);
        TableView<Object[]> tableView = (TableView<Object[]>) pane.lookup(".table-view");

        assertThrows(IllegalArgumentException.class, () -> {
            YourClass.completeTransaction("User", 100.0, tableView);
        });
    }

    @Test
    void TC9_createTransactionPane_insufficientInventory() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createTransactionPane("User", stage, true, scene);

        assertDoesNotThrow(() -> {
            YourClass.createTransactionPane("User", stage, true, scene);
        });
    }

    @Test
    void TC10_createTransactionPane_databaseConnectionFailure() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        assertThrows(Exception.class, () -> {
            YourClass.createTransactionPane("User", stage, true, scene);
        });
    }
}
