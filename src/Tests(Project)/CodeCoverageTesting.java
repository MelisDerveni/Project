import static org.junit.Assert.*;
import org.junit.Test;
public class CodeCoverageTesting{
    //Melis
@Test
public void testCreateTransactionPane_StatementCoverage() {
    TransactionPane pane = new TransactionPane();
    pane.addItem("Apple", 2);
    assertEquals(2, pane.getItemCount("Apple"));
}

@Test
public void testCreateInventoryPane_BranchCoverage() {
    InventoryPane pane = new InventoryPane();
    pane.updateStock("Banana", 10);
    assertTrue(pane.isInStock("Banana"));
    pane.updateStock("Banana", -10);
    assertFalse(pane.isInStock("Banana"));
}
    //Erlind
@Test
public void testValidateLogin_ConditionCoverage() {
    assertTrue(validateLogin("admin", "password123"));
    assertFalse(validateLogin("admin", "wrongpass"));
    assertFalse(validateLogin("", "password123"));
}
}