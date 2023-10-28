import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreetingImplTest {
    private Greeting greeting;

    @Before
    public void setup() {
        greeting = new GreetingImpl();
    }

    @Test
    public void greetShouldReturnValidOutput() {
        String result = greeting.greet("Ahmet");
        assertNotNull(result);
        assertEquals("Hello Ahmet", result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void greetShouldThrowException_For_EmptyName() {
        greeting.greet("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void greetShouldThrowException_For_NameNull() {
        greeting.greet(null);
    }

    @After
    public void tearDown() {
        greeting = null;
    }
}