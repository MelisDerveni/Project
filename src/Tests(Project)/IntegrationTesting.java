import static org.junit.Assert.*;
import org.junit.Test;

public class Integrationtesting{
    @Test
public void testUserPaneToAdminSceneIntegration() {
    UserPane userPane = new UserPane();
    userPane.addUser("John Doe", "Admin");
    AdminScene adminScene = new AdminScene();
    assertEquals("John Doe", adminScene.getUserDetails("Admin"));
}
@Test
public void testTransactionPaneInventoryUpdate() {
    TransactionPane transactionPane = new TransactionPane();
    InventoryPane inventoryPane = new InventoryPane();
    transactionPane.purchaseItem("Apple", 5);
    assertEquals(95, inventoryPane.getStock("Apple"));
}
@Test
public void testLoginModuleRoleRedirect() {
    LoginModule loginModule = new LoginModule();
    assertEquals("AdminScene", loginModule.login("adminUser", "password"));
    assertEquals("CashierScene", loginModule.login("cashierUser", "password"));
}
}