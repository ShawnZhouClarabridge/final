package co.edureka.controller;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.edureka.service.CourseService;

@Controller
public class CourseController {
	
	private static final Logger logger = Logger.getLogger(CourseController.class);
	
private CourseService courseService;

//private static ArrayList<String> course_list=null;

private String course_update=null;
	
	@Autowired
	public void setCourseService(CourseService courseService){
		this.courseService=courseService;
	}
	
	@RequestMapping("/addCourse")
	public String addCourse(){
		logger.debug("Inside addCourse Method");
		return "addCourse2";
	}
	
	
	@RequestMapping("/subCourse")
	public ModelAndView addCourse(Courses course){
		
		 courseService.insertCourse(course);
		 String message="Course Inserted Successfully";
		 return new ModelAndView("courseAdded", "message", message);
	}
	

	
	@RequestMapping("/updateCourse")
	public ModelAndView getCourses(){
		
		ArrayList <Courses> courses =(ArrayList<Courses>) courseService.getCourses();
		return new ModelAndView("updateCourse","courses",courses);
		
	}	
	
	@RequestMapping("/modifyCourse")
	public ModelAndView modifyCourse(@RequestParam Map<String,String>param){
	course_update=param.get("course");
	Courses course=courseService.getCourse(param.get("course"));	
	return new ModelAndView("updateCourse","course",course);
		
	}
		
	
	@RequestMapping("/courseUpdated")
	public ModelAndView courseUpdated(Courses course){
		
		courseService.updateCourse(course,course_update);
		String message=course.getName()+" course has been updated successfully";	
		return new ModelAndView("courseUpdated","model",message);
	}
	
	
	@RequestMapping("/deleteCourse")
	public ModelAndView deleteCourse(){
		
		ArrayList <Courses> courses =(ArrayList<Courses>) courseService.getCourses();
		
		return new ModelAndView("deleteCourse","model",courses);
	}
	
	
	
	@RequestMapping("courseDeletion")
	public ModelAndView courseDeletion(@RequestParam Map<String,String>param){
		System.out.println("Trying to delete "+param.get("course"));
		courseService.deleteCourse(param.get("course"));
		String message=param.get("course")+" Course have been deleted from database successfully ";
		return new ModelAndView("courseDeletion","model",message);
	}
	
	
}
