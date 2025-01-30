import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataIntegrityTest {
    public static void main(String[] args) {
        int initialStock = getProductStock(1);  // Get initial stock for product ID 1
        processSale(1, 3);  // Simulate sale of 3 units
        int finalStock = getProductStock(1);  // Get stock after sale

        if (finalStock == initialStock - 3) {
            System.out.println("Data Integrity Test Passed: Inventory updated correctly.");
        } else {
            System.out.println("WARNING: Data Integrity Test Failed! Inventory mismatch detected.");
        }
    }

    private static int getProductStock(int productId) {
        String query = "SELECT stock FROM products WHERE product_id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerydb", "root", "password");
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("stock");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Return -1 if query fails
    }

    private static void processSale(int productId, int quantitySold) {
        String updateStockQuery = "UPDATE products SET stock = stock - ? WHERE product_id = ?";
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/grocerydb", "root", "password");
             PreparedStatement stmt = conn.prepareStatement(updateStockQuery)) {
            stmt.setInt(1, quantitySold);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
