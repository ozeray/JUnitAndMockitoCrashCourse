package com.ahmet.junit5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

// Mockito cannot mock
// 	- static methods/variables,
// 	- final classes/methods/variables,
// 	- constructors,
// 	- private methods/variables
// 	- enums,
// because it uses proxy pattern.
//
// PowerMock can mock them, because it uses bytecode instrumentation/manipulation and custom class loader.
// It has two flavours: For Mockito and for EasyMock. Each has consistence style with the respective mocking library,
// which simplifies migration (e.g. from Mockito to PowerMock)
// NOTE: PowerMock is deprecated. Should not be used as it's not safe.
@ExtendWith(MockitoExtension.class)
public class GreetingImplTest {

	@Mock
	private GreetingService service;

	@InjectMocks
	private GreetingImpl greeting;

	@Test
	public void greetShouldRetunAValidOutput() {
		System.out.println("greetShouldRetunAValidOutput");
		when(service.greet(anyString())).thenReturn("Hello Junit");
		String result = greeting.greet("Junit");
		Assertions.assertNotNull(result);
		Assertions.assertEquals("Hello Junit", result);
		verify(service, times(1)).greet(any());
	}

	@Test
	public void greetShouldThrowAnException_For_NameIsNull() {
		System.out.println("greetShouldThrowAnException_For_NameIsNull");
		doThrow(IllegalArgumentException.class).when(service).greet(null);
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			greeting.greet(null);
		});
		verify(service, atLeastOnce()).greet(any());
	}

	@Test
	public void greetShouldThrowAnException_For_NameIsBlank() {
		System.out.println("greetShouldThrowAnException_For_NameIsBlank");
		doThrow(IllegalArgumentException.class).when(service).greet(eq(""));
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			greeting.greet("");
		});
	}

	@Test
	public void greetShouldThrowAnException_AfterSecondCall() {
		doReturn("").doThrow(IllegalArgumentException.class).when(service).greet(eq(""));
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			greeting.greet("");
			greeting.greet("");
		});
	}

}
