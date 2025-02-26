package br.com.mockito.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ListTest {

	@Test
	public void testMockList_Quando_SizeEChamado_Retornar10() {
		// Given / Arrange
		List<?> list = mock(List.class);
		when(list.size()).thenReturn(10);
		
		// When / Act
		// Then / Assert
		assertEquals(10, list.size());
	}
	
	
	@Test
	public void testMockList_Quando_RetornarException() {
		// Given / Arrange
		var list = mock(List.class);
		
		// matchers
		when(list.get(anyInt())).thenThrow(new RuntimeException("Foo Bar"));
		
		// When / Act
		// Then / Assert
		assertThrows(RuntimeException.class, 
				() -> {
					list.get(anyInt());
				}, () -> "tem que retornar um RuntimeException");
	}
}
