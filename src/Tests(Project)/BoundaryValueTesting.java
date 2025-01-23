import static org.junit.Assert.*;
import org.junit.Test;

public class BoundaryValueTesting{
    //Melis
@Test
public void testCreateUserPane_BoundaryValues() {
    assertEquals("Valid", createUserPane(0));
    assertEquals("Valid", createUserPane(100));
    assertEquals("Invalid", createUserPane(-1));
}
    //Erlind
@Test
public void testGetProductPrice_BoundaryValues() {
    assertEquals(1, getProductPrice(1));
    assertEquals(1000, getProductPrice(1000));
    assertEquals(-1, getProductPrice(0));
}

@Test
public void testValidateLogin_BoundaryValues() {
    assertTrue(validateLogin("user3"));
    assertTrue(validateLogin("user20"));
    assertFalse(validateLogin("user2"));
}
}