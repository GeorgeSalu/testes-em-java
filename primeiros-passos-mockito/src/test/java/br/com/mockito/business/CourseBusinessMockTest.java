package br.com.mockito.business;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.mockito.service.CourseService;

class CourseBusinessMockTest {

	CourseService mockService;
	CourseBusiness business;
	
	@BeforeEach
	void setup() {
		// Given / Arrange
		mockService = mock(CourseService.class);
		business = new CourseBusiness(mockService);
	}
	
	@Test
	public void testCoursesRelatedToSpring_When_UsingAMock() {
		

		
		// When / Act
		var filteredCourses = business.retriveCoursesRelatedToSpring("Leandro");
		
		// Then / Assert
		assertEquals(4, filteredCourses.size());
	}


}
