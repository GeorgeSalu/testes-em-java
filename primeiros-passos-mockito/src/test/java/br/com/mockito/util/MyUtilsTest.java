package br.com.mockito.util;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

class MyUtilsTest {

	@Test
	void testeMocarMetodosEstaticosComParametros() {
		
		try(MockedStatic<MyUtils> mockedStatic = mockStatic(MyUtils.class) ){
			mockedStatic.when(() -> MyUtils.getWelcomeMessage(eq("George"), anyBoolean())).thenReturn("ola george");
			
			String result = MyUtils.getWelcomeMessage("George", false);
			
			assertEquals("ola george", result);
		}
		
	}

}
