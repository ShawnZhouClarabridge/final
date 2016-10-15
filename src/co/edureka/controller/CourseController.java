package co.edureka.controller;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.edureka.service.CourseService;

import javax.validation.Valid;

@Controller
public class CourseController {

    private static final Logger logger = Logger.getLogger(CourseController.class);
    private CourseService courseService;
    private String course_update = null;
//private static ArrayList<String> course_list=null;

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "addCourse")
    public ModelAndView add() {
        return new ModelAndView("addCourse2", "courses", new Courses()); //view name, model name and model object
    }

    @RequestMapping(value = "subCourse", method = RequestMethod.POST, params = {"add"})
    public String addCourse(Model model, @Valid Courses course, BindingResult result) {
        if (result.hasErrors()) {
            return "addCourse2";
        }
        if (courseService.courseExists(course)) {
            model.addAttribute("msg", String.format("Course with ID %s already exist", course.getId()));
            return "addCourse2";
        }
        courseService.insertCourse(course);
        String message = "Course Inserted Successfully";
        model.addAttribute("message",message);
        return "courseAdded";
    }

    @RequestMapping(value = "subCourse", method = RequestMethod.POST, params = {"cancel"})
    public String cancelAdd() {
        return "home";
    }


    @RequestMapping("/updateCourse")
    public ModelAndView getCourses() {

        ArrayList<Courses> courses = (ArrayList<Courses>) courseService.getCourses();
        return new ModelAndView("updateCourse", "courses", courses);

    }

    @RequestMapping("/modifyCourse")
    public ModelAndView modifyCourse(@RequestParam Map<String, String> param) {
        course_update = param.get("course");
        Courses course = courseService.getCourse(param.get("course"));
        return new ModelAndView("updateCourse", "course", course);

    }


    @RequestMapping("/courseUpdated")
    public ModelAndView courseUpdated(Courses course) {

        courseService.updateCourse(course, course_update);
        String message = course.getName() + " course has been updated successfully";
        return new ModelAndView("courseUpdated", "model", message);
    }


    @RequestMapping("/deleteCourse")
    public ModelAndView deleteCourse() {

        ArrayList<Courses> courses = (ArrayList<Courses>) courseService.getCourses();

        return new ModelAndView("deleteCourse", "model", courses);
    }


    @RequestMapping("courseDeletion")
    public ModelAndView courseDeletion(@RequestParam Map<String, String> param) {
        System.out.println("Trying to delete " + param.get("course"));
        courseService.deleteCourse(param.get("course"));
        String message = param.get("course") + " Course have been deleted from database successfully ";
        return new ModelAndView("courseDeletion", "model", message);
    }


}
