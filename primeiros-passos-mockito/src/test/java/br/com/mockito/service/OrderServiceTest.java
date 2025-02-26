package br.com.mockito.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import br.com.mockito.model.Order;

class OrderServiceTest {

	private final OrderService service = new OrderService();
	private final UUID defaultUuid = UUID.fromString("bf75c7c5-5e04-4fcd-94ca-6b30e808e002");
	
	@DisplayName("")
	@Test
	public void testeDeveIncluirRandonOrderId_Quando_NoOrderIdExists() {
		// Given / Arrange
		try(MockedStatic<UUID> mockedUUID = Mockito.mockStatic(UUID.class)) {
			mockedUUID.when(UUID::randomUUID).thenReturn(defaultUuid);
			
			
			Order result = service.createOrder("macbook pro", 2L, null);
			
			assertEquals(defaultUuid.toString(), result.getId());
		}
		// When / Act
		// Then / Assert
	}

}
