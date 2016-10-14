package co.edureka.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edureka.controller.Courses;
import co.edureka.dao.CourseDAO;

@Service("courseService")
public class CourseService {
	
	private CourseDAO courseDao;
	
	@Autowired 
	public void setCourseDao(CourseDAO courseDao){
		this.courseDao=courseDao;
	}
	

	public void insertCourse(Courses course) {
		
		courseDao.insertCourse(course);
		
	}

	public void deleteCourse(String courseName){
		courseDao.deleteCourse(courseName);
	}
	
	public void updateCourse(Courses course,String courseName){
		courseDao.updateCourse(course,courseName);
	}
	
	public List<Courses> getCourses(){
		return courseDao.getCourses();
	}
	
	public Courses getCourse(String name){
		return courseDao.getCourse(name);
	}
	
}
