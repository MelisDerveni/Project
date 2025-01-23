import static org.junit.Assert.*;
import org.junit.Test;

public class ClassEvaluationTesting{
    //Erlind
@Test
public void testCreateTransaction_Valid() {
    Order validOrder = new Order(100);
    assertTrue(createTransaction(validOrder));
}

@Test
public void testCreateTransaction_Invalid() {
    Order invalidOrder = new Order(-10);
    assertFalse(createTransaction(invalidOrder));
}

    //Melis
@Test
public void testApplyDiscount_EligibleCustomer() {
    Customer customer = new Customer("Gold");
    assertEquals(20, applyDiscount(customer));
}

@Test
public void testApplyDiscount_IneligibleCustomer() {
    Customer customer = new Customer("Regular");
    assertEquals(0, applyDiscount(customer));
}
}