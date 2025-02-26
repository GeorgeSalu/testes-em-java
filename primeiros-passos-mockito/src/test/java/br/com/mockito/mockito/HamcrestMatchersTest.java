package br.com.mockito.mockito;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.Test;

public class HamcrestMatchersTest {

	@Test
	public void testaHamcrestMatchers() {
		// Given / Arrange
		List<Integer> scores = Arrays.asList(99, 100, 101, 105);
		
		// When / Act
		// Then / Assert
		assertThat(scores, hasSize(4));
		assertThat(scores, hasItems(100, 101));
		assertThat(scores, everyItem(greaterThan(98)));
		assertThat(scores, everyItem(lessThan(106)));
		
		// check strings
		assertThat("",	is(emptyString()));
		assertThat(null, is(emptyOrNullString()));
		
		// arrays
		Integer[] myArray = {1, 2, 3};
		
		assertThat(myArray, arrayWithSize(3));
		assertThat(myArray, arrayContaining(1, 2, 3));
	}
	
}
