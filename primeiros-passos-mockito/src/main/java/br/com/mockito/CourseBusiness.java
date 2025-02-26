package br.com.mockito;

import java.util.ArrayList;
import java.util.List;

import br.com.mockito.service.CourseService;

// CourseBusiness = SUT - System (Method) Under Test
public class CourseBusiness {

	// CourseService é uma dependencia
	private CourseService service;

	public CourseBusiness(CourseService service) {
		this.service = service;
	}
	
	public List<String> retriveCoursesRelatedToSpring(String student) {
		
		var filteredCourses = new ArrayList<String>();
		var allCourses = service.retrieveCourses(student);
		
		for (String course : allCourses) {
			if(course.contains("Spring")) {
				filteredCourses.add(course);
			}
		}
		
		
		return filteredCourses;
	}
	
}
