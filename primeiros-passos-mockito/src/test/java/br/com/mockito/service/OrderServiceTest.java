package br.com.mockito.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import br.com.mockito.model.Order;

class OrderServiceTest {

	private final OrderService service = new OrderService();
    private final UUID defaultUuid = UUID.fromString("8d8b30e3-de52-4f1c-a71c-9905a8043dac");
    private final LocalDateTime defaultLocalDateTime = LocalDateTime.of(2023, 7, 4, 12, 0);
	
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
	
    @Test
    void shouldIncludeCurrentTimeWhenCreatingANewOrder() {
        try (MockedStatic<LocalDateTime> mockedLocalDateTime = Mockito.mockStatic(LocalDateTime.class)) {
            mockedLocalDateTime.when(LocalDateTime::now).thenReturn(defaultLocalDateTime);

            Order result = service.createOrder("MacBook Pro", 2L, "42");
            assertEquals(defaultLocalDateTime, result.getCreationDate());
        }
    }

}
