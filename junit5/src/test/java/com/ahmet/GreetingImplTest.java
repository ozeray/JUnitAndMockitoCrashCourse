package com.ahmet;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GreetingImplTest {
    private Greeting greeting;

    @BeforeEach
    public void setup() {
        greeting = new GreetingImpl();
    }

    @Test
    public void greetShouldReturnValidOutput() {
        String result = greeting.greet("Ahmet");
        assertNotNull(result);
        assertEquals("Hello Ahmet", result);
    }

    @Test
    public void greetShouldThrowException_For_EmptyName() {
        assertThrows(IllegalArgumentException.class, () -> greeting.greet(""));
    }

    @Test
    public void greetShouldThrowException_For_NameNull() {
        assertThrows(IllegalArgumentException.class, () -> greeting.greet(null));
    }

    @AfterEach
    public void tearDown() {
        greeting = null;
    }
}