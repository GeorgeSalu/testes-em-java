package br.com.mockito.business;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import br.com.mockito.service.CourseService;

class CourseBusinessMockBDDTest {

	CourseService mockService;
	CourseBusiness business;
	List<String> courses;
	
	@BeforeEach
	void setup() {
		// Given / Arrange
		mockService = mock(CourseService.class);
		business = new CourseBusiness(mockService);
		courses = Arrays.asList(
                "REST API's RESTFul do 0 à Azure com ASP.NET Core 5 e Docker",
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "Spotify Engineering Culture Desmistificado",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker",
                "Docker do Zero à Maestria - Contêinerização Desmistificada",
                "Docker para Amazon AWS Implante Apps Java e .NET com Travis CI",
                "Microsserviços do 0 com Spring Cloud, Spring Boot e Docker",
                "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Kotlin e Docker",
                "Kotlin para DEV's Java: Aprenda a Linguagem Padrão do Android",
                "Microsserviços do 0 com Spring Cloud, Kotlin e Docker"
        );
	}
	
	@Test
	public void testCoursesRelatedToSpring_When_UsingAMock() {
		// Given / Arrange
		given(mockService.retrieveCourses("Leandro")).willReturn(courses);
		
		// When / Act
		var filteredCourses = business.retriveCoursesRelatedToSpring("Leandro");
		
		// Then / Assert
		assertThat(filteredCourses.size(), is(4));
	}
	
	@Test
	public void testaOperacaoDeDeleteDeCurso() {
		// Given / Arrange
		given(mockService.retrieveCourses("Leandro")).willReturn(courses);
		
		// When / Act
		business.deleteCoursesNotRelatedToSpring("Leandro");
		
		
		// Then / Assert
		verify(mockService).deleteCourse("Agile Desmistificado com Scrum, XP, Kanban e Trello");
		verify(mockService).deleteCourse("Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#");
		verify(mockService, never()).deleteCourse("REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker");
	}
	
	@Test
	public void testaOperacaoDeDeleteDeCursoV2() {
		// Given / Arrange
		given(mockService.retrieveCourses("Leandro")).willReturn(courses);
		
		// When / Act
		business.deleteCoursesNotRelatedToSpring("Leandro");
		
		
		// Then / Assert
		String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";
		String architecturecourse = "Arquitetura de Microsserviços do 0 com ASP.NET, .NET 6 e C#";

		then(mockService)
			.should()
			.deleteCourse(agileCourse);
		then(mockService)
			.should()
			.deleteCourse(architecturecourse);
	}
	
	@Test
	public void testaOperacaoDeDeleteDeCursoCapturingArguments() {
		// Given / Arrange
		
		courses = Arrays.asList(
                "Agile Desmistificado com Scrum, XP, Kanban e Trello",
                "REST API's RESTFul do 0 à AWS com Spring Boot 3 Java e Docker"
        );
		
		given(mockService.retrieveCourses("Leandro")).willReturn(courses);
		
		ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
		
		// When / Act
		business.deleteCoursesNotRelatedToSpring("Leandro");
		
		
		// Then / Assert
		String agileCourse = "Agile Desmistificado com Scrum, XP, Kanban e Trello";

		then(mockService)
			.should()
			.deleteCourse(argumentCaptor.capture());
		
		assertThat(argumentCaptor.getValue(), is(agileCourse));
	}


}
