import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.control.TableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

class CompleteTransactionTest {

    @Test
    void TC_CT_01_validTransaction() {
        String customerName = "John Doe";
        double totalPrice = 500.0;
        TableView<Object[]> tableView = createMockTableView(2);

        assertDoesNotThrow(() -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void TC_CT_02_emptyCustomerName() {
        String customerName = "";
        double totalPrice = 200.0;
        TableView<Object[]> tableView = createMockTableView(1);

        assertThrows(SQLException.class, () -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void TC_CT_03_negativeTotalPrice() {
        String customerName = "Alice";
        double totalPrice = -50.0;
        TableView<Object[]> tableView = createMockTableView(1);

        assertThrows(SQLException.class, () -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void TC_CT_04_zeroTotalPrice() {
        String customerName = "Bob";
        double totalPrice = 0.0;
        TableView<Object[]> tableView = createMockTableView(1);

        assertThrows(SQLException.class, () -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void TC_CT_05_largeTotalPrice() {
        String customerName = "Eve";
        double totalPrice = 1_000_000.0;
        TableView<Object[]> tableView = createMockTableView(1);

        assertDoesNotThrow(() -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void TC_CT_06_emptyTableView() {
        String customerName = "John";
        double totalPrice = 100.0;
        TableView<Object[]> tableView = new TableView<>();

        assertThrows(SQLException.class, () -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void TC_CT_07_specialCharactersInCustomerName() {
        String customerName = "!@#$%^&*";
        double totalPrice = 100.0;
        TableView<Object[]> tableView = createMockTableView(1);

        assertThrows(SQLException.class, () -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void TC_CT_08_maximumItemsInTableView() {
        String customerName = "MaxUser";
        double totalPrice = 500.0;
        TableView<Object[]> tableView = createMockTableView(1000);

        assertDoesNotThrow(() -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    @Test
    void TC_CT_09_nonNumericQuantityInTableView() {
        String customerName = "User";
        double totalPrice = 100.0;
        TableView<Object[]> tableView = new TableView<>();
        ObservableList<Object[]> data = FXCollections.observableArrayList();
        data.add(new Object[]{"Product1", "invalidQuantity"});
        tableView.setItems(data);

        assertThrows(NumberFormatException.class, () -> {
            YourClass.completeTransaction(customerName, totalPrice, tableView);
        });
    }

    private TableView<Object[]> createMockTableView(int numRows) {
        TableView<Object[]> tableView = new TableView<>();
        ObservableList<Object[]> data = FXCollections.observableArrayList();
        for (int i = 0; i < numRows; i++) {
            data.add(new Object[]{"Product" + i, 1});
        }
        tableView.setItems(data);
        return tableView;
    }
}
