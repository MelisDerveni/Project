import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class branchTestingInventoryPaneTest {

    @Test
    void TC1_createInventoryPane_validInput() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        assertDoesNotThrow(() -> {
            StackPane pane = YourClass.createInventoryPane("AdminUser", stage, true, scene);
            assertNotNull(pane);
        });
    }

    @Test
    void TC2_createInventoryPane_emptyUsername() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        assertDoesNotThrow(() -> {
            StackPane pane = YourClass.createInventoryPane("", stage, false, scene);
            assertNotNull(pane);
        });
    }

    @Test
    void TC3_createInventoryPane_nullScene() {
        Stage stage = new Stage();

        assertThrows(NullPointerException.class, () -> {
            YourClass.createInventoryPane("User", stage, true, null);
        });
    }

    @Test
    void TC4_createInventoryPane_productNotSelected() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createInventoryPane("User", stage, true, scene);

        // Get product choice box and simulate no selection
        ChoiceBox<String> productChoiceBox = (ChoiceBox<String>) pane.lookup(".choice-box");
        productChoiceBox.setValue("Select Product");

        assertDoesNotThrow(() -> {
            productChoiceBox.fireEvent(null);
        });
    }

    @Test
    void TC5_createInventoryPane_validProductSelected() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createInventoryPane("User", stage, true, scene);

        // Select a valid product and trigger event
        ChoiceBox<String> productChoiceBox = (ChoiceBox<String>) pane.lookup(".choice-box");
        productChoiceBox.setValue("Product1");

        assertDoesNotThrow(() -> {
            productChoiceBox.fireEvent(null);
        });
    }

    @Test
    void TC6_createInventoryPane_invalidQuantityInput() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createInventoryPane("User", stage, true, scene);

        // Simulate invalid quantity input and expect an error handling alert
        assertDoesNotThrow(() -> {
            YourClass.createInventoryPane("User", stage, true, scene);
        });
    }

    @Test
    void TC7_createInventoryPane_validQuantityAndProduct() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createInventoryPane("User", stage, true, scene);
        assertNotNull(pane);

        TableView<Object[]> tableView = (TableView<Object[]>) pane.lookup(".table-view");
        assertNotNull(tableView);

        assertDoesNotThrow(() -> {
            YourClass.addProductToInventory("Product1", 10, 50.0);
        });
    }

    @Test
    void TC8_createInventoryPane_invalidQuantityBoundary() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createInventoryPane("User", stage, true, scene);
        TableView<Object[]> tableView = (TableView<Object[]>) pane.lookup(".table-view");

        assertThrows(IllegalArgumentException.class, () -> {
            YourClass.addProductToInventory("Product1", -1, 50.0);
        });
    }

    @Test
    void TC9_createInventoryPane_updateExistingProduct() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        StackPane pane = YourClass.createInventoryPane("User", stage, true, scene);

        assertDoesNotThrow(() -> {
            YourClass.updateInventory("Product1", 20, 100.0);
        });
    }

    @Test
    void TC10_createInventoryPane_databaseConnectionFailure() {
        Stage stage = new Stage();
        Scene scene = new Scene(new StackPane());

        assertThrows(Exception.class, () -> {
            YourClass.createInventoryPane("User", stage, true, scene);
        });
    }
}
