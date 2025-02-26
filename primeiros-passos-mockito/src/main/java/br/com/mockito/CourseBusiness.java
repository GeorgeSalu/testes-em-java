package br.com.mockito;

import java.util.ArrayList;
import java.util.List;

import br.com.mockito.service.CourseService;

public class CourseBusiness {

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
