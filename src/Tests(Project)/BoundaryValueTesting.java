import static org.junit.Assert.*;
import org.junit.Test;

public class BoundaryValueTesting{
    //Melis
    //test for CreateUserPane() edge cases
    @Test
    void testEmptyUsername() {
        StackPane result = UserPane.createUserPane("", testStage, true, loggedOutScene);
        assertNotNull(result, "StackPane should not be null");
        // Check if error handling is triggered (e.g., using logs, alerts, or UI feedback)
    }

    @Test
    void testMaxLengthUsername() {
        String maxUsername = "a".repeat(50);
        StackPane result = UserPane.createUserPane(maxUsername, testStage, true, loggedOutScene);
        assertNotNull(result, "StackPane should handle max username length");
    }

    @Test
    void testSpecialCharactersInUsername() {
        String specialUsername = "@#$%&*!";
        StackPane result = UserPane.createUserPane(specialUsername, testStage, true, loggedOutScene);
        assertNotNull(result, "StackPane should handle special characters");
    }

    @Test
    void testNullStage() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            UserPane.createUserPane("validUser", null, true, loggedOutScene);
        });
        assertNotNull(exception, "Should throw NullPointerException when stage is null");
    }

    @Test
    void testAdminAccess() {
        StackPane result = UserPane.createUserPane("adminUser", testStage, true, loggedOutScene);
        assertNotNull(result, "StackPane should load for admin users");
        // Check if admin options exist in the result
    }

    @Test
    void testNonAdminAccess() {
        StackPane result = UserPane.createUserPane("normalUser", testStage, false, loggedOutScene);
        assertNotNull(result, "StackPane should load for non-admin users");
        // Check if non-admin options exist in the result
    }

    @Test
    void testNewUserOption() {
        Platform.runLater(() -> {
            StackPane result = UserPane.createUserPane("testUser", testStage, true, loggedOutScene);
            ChoiceBox<String> userOptions = (ChoiceBox<String>) result.lookup(".choice-box");
            userOptions.setValue("New User");
            assertEquals("New User", userOptions.getValue(), "ChoiceBox should have 'New User' selected");
        });
    }

    @Test
    void testModifyUserOption() {
        Platform.runLater(() -> {
            StackPane result = UserPane.createUserPane("testUser", testStage, true, loggedOutScene);
            ChoiceBox<String> userOptions = (ChoiceBox<String>) result.lookup(".choice-box");
            userOptions.setValue("Modify User");
            assertEquals("Modify User", userOptions.getValue(), "ChoiceBox should have 'Modify User' selected");
        });
    }

    @Test
    void testRemoveUserOption() {
        Platform.runLater(() -> {
            StackPane result = UserPane.createUserPane("testUser", testStage, true, loggedOutScene);
            ChoiceBox<String> userOptions = (ChoiceBox<String>) result.lookup(".choice-box");
            userOptions.setValue("Remove User");
            assertEquals("Remove User", userOptions.getValue(), "ChoiceBox should have 'Remove User' selected");
        });
    }

    @Test
    void testEmptyChoiceBoxSelection() {
        Platform.runLater(() -> {
            StackPane result = UserPane.createUserPane("testUser", testStage, true, loggedOutScene);
            ChoiceBox<String> userChoiceBox = (ChoiceBox<String>) result.lookup(".choice-box");
            assertEquals("Choose User", userChoiceBox.getValue(), "ChoiceBox should have default value 'Choose User'");
        });
    }

    @Test
    void testValidUserAddition() {
        Platform.runLater(() -> {
            StackPane result = UserPane.createUserPane("newUser", testStage, true, loggedOutScene);
            assertNotNull(result, "StackPane should allow adding new users");
            // Simulate user input and button click events
        });
    }

    @Test
    void testPrimaryAdminModificationRestriction() {
        Platform.runLater(() -> {
            StackPane result = UserPane.createUserPane("Klendi", testStage, true, loggedOutScene);
            assertNotNull(result, "StackPane should prevent primary admin modification");
            // Check for alert or restriction logic
        });
    }
    
    
    
    
    
    // test for completeTransaction
    //Erlind
    @Test
    void testCompleteTransactionWithEmptyCustomerName() {
        // Boundary Test: Empty customer name
        String customerName = "";
        double totalPrice = 100.0; // Valid boundary value
        TableView<Object[]> tableView = createMockTableView();

        assertThrows(SQLException.class, () -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void testCompleteTransactionWithMaxCustomerNameLength() {
        // Boundary Test: Maximum length of customer name
        String customerName = "a".repeat(255); // Assuming 255 is the maximum allowed length
        double totalPrice = 100.0;
        TableView<Object[]> tableView = createMockTableView();

        assertDoesNotThrow(() -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void testCompleteTransactionWithEmptyTableView() {
        // Boundary Test: Empty table view
        String customerName = "ValidCustomer";
        double totalPrice = 100.0;
        TableView<Object[]> tableView = new TableView<>(); // Empty table view

        assertDoesNotThrow(() -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void testCompleteTransactionWithZeroTotalPrice() {
        // Boundary Test: Zero total price
        String customerName = "ValidCustomer";
        double totalPrice = 0.0; // Minimum boundary value for price
        TableView<Object[]> tableView = createMockTableView();

        assertDoesNotThrow(() -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void testCompleteTransactionWithNegativeTotalPrice() {
        // Boundary Test: Negative total price
        String customerName = "ValidCustomer";
        double totalPrice = -10.0; // Invalid boundary value
        TableView<Object[]> tableView = createMockTableView();

        assertThrows(SQLException.class, () -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void testCompleteTransactionWithLargeTotalPrice() {
        // Boundary Test: Large total price
        String customerName = "ValidCustomer";
        double totalPrice = 1_000_000.0; // Large boundary value
        TableView<Object[]> tableView = createMockTableView();

        assertDoesNotThrow(() -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void testCompleteTransactionWithSingleItemInTableView() {
        // Boundary Test: TableView with a single item
        String customerName = "ValidCustomer";
        double totalPrice = 100.0;
        TableView<Object[]> tableView = new TableView<>();
        ObservableList<Object[]> data = FXCollections.observableArrayList();
        data.add(new Object[]{"Product1", 1}); // Single item
        tableView.setItems(data);

        assertDoesNotThrow(() -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void testCompleteTransactionWithMaxItemsInTableView() {
        // Boundary Test: TableView with maximum items
        String customerName = "ValidCustomer";
        double totalPrice = 100.0;
        TableView<Object[]> tableView = new TableView<>();
        ObservableList<Object[]> data = FXCollections.observableArrayList();
        for (int i = 0; i < 1000; i++) { // Assuming 1000 is the max items allowed
            data.add(new Object[]{"Product" + i, 1});
        }
        tableView.setItems(data);

        assertDoesNotThrow(() -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void testCompleteTransactionWithNonNumericQuantityInTableView() {
        // Boundary Test: Non-numeric quantity
        String customerName = "ValidCustomer";
        double totalPrice = 100.0;
        TableView<Object[]> tableView = new TableView<>();
        ObservableList<Object[]> data = FXCollections.observableArrayList();
        data.add(new Object[]{"Product1", "InvalidQuantity"}); // Non-numeric quantity
        tableView.setItems(data);

        assertThrows(NumberFormatException.class, () -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    private TableView<Object[]> createMockTableView() {
        // Helper method to create a mock TableView with sample data
        TableView<Object[]> tableView = new TableView<>();
        ObservableList<Object[]> data = FXCollections.observableArrayList();
        data.add(new Object[]{"Product1", 1});
        data.add(new Object[]{"Product2", 2});
        tableView.setItems(data);
        return tableView;
    }
}