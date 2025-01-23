import static org.junit.Assert.*;
import org.junit.Test;

public class UnitTesting{
    @Test
public void testCreateScene_WithAdmin() {
    Scene result = AdminScene.createScene("admin", stage, true, loggedOut);
    assertNotNull(result);
    assertEquals("MANAGER: admin", ((Label) result.lookup("#roleLabel")).getText());
}

@Test
public void testCreateScene_WithCashier() {
    Scene result = AdminScene.createScene("cashier", stage, true, loggedOut);
    assertNotNull(result);
    assertEquals("CASHIER", ((Label) result.lookup("#roleLabel")).getText());
}
@Test
public void testCreateCustomerPane_ValidData() {
    CustomerPane pane = new CustomerPane();
    assertNotNull(pane.createCustomerPane("John Doe"));
}

@Test
public void testCreateCustomerPane_InvalidData() {
    CustomerPane pane = new CustomerPane();
    assertThrows(IllegalArgumentException.class, () -> {
        pane.createCustomerPane(null);
    });
}
@Test
public void testCreateLogOutButton_Functionality() {
    Button logOutButton = createLogOutButton();
    assertNotNull(logOutButton);
    assertEquals("Log Out", logOutButton.getText());
}

@Test
public void testCreateLogOutButton_Action() {
    Button logOutButton = createLogOutButton();
    logOutButton.fire();
    assertEquals(true, isLoggedOut);
}
}