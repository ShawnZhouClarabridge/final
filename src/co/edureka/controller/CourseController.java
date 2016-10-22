package co.edureka.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    private Courses courseToUpdate = null;
    private ArrayList<Courses> coursesArrayList = new ArrayList<Courses>();
    private final String[] fields = {"id", "name", "price", "list all"};

    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    /************************Add Course************************/
    @RequestMapping(value = "addCourse")
    public ModelAndView add() {
        return new ModelAndView("addCourse2", "courses", new Courses());
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
        model.addAttribute("message", message);
        return "courseAdded";
    }

    @RequestMapping(value = "subCourse", method = RequestMethod.POST, params = {"cancel"})
    public String cancelAdd() {
        return "home";
    }
    /************************Add Course End************************/

    /************************Update Course************************/
    @RequestMapping("/updateCourse")
    public String getCourses(Model model) {
        coursesArrayList = (ArrayList<Courses>) courseService.getCourses();
        model.addAttribute("courses", new Courses());
        model.addAttribute("coursesArray", coursesArrayList);
        return "updateCourse";
    }

    @RequestMapping("/modifyCourse")
    public String modifyCourse(Model model, @RequestParam Map<String, String> param) {
        courseToUpdate = courseService.getCourseById(param.get("course"));
        model.addAttribute("courses", courseToUpdate);
        model.addAttribute("coursesArray", coursesArrayList);
        return "updateCourse";
    }

    @RequestMapping("/courseUpdated")
    public String courseUpdated(Model model, @Valid Courses course, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println("binding error");
            return "updateCourse";
        }
        if (courseService.courseExists(course) && !course.getId().equals(courseToUpdate.getId())) {
            model.addAttribute("msg", String.format("Course with ID %s already exist", course.getId()));
            return "updateCourse";
        }

        courseService.updateCourse(course, courseToUpdate.getId());
        String message = course.getName() + " course has been updated successfully";
        model.addAttribute("model", message);
        return "courseUpdated";
    }

    /************************Update Course End************************/

    /************************Delete Course************************/
    @RequestMapping("/deleteCourse")
    public ModelAndView deleteCourse() {
        ArrayList<Courses> courses = (ArrayList<Courses>) courseService.getCourses();
        return new ModelAndView("deleteCourse", "model", courses);
    }


    @RequestMapping("courseDeletion")
    public ModelAndView courseDeletion(@RequestParam Map<String, String> param) {
        System.out.println("Trying to delete " + param.get("course"));
        courseService.deleteCourse(param.get("course"));
        String message = " Course have been deleted from database successfully ";
        return new ModelAndView("courseDeletion", "model", message);
    }
    /************************Delete Course End************************/

    /************************Search Course************************/
    @RequestMapping("searchCourse")
    public String searchCourses(Model model) {
        model.addAttribute("filedsArray", fields);
        return "searchCourses";
    }


    @RequestMapping("searchCoursesResult")
    public String searchCoursesResult(Model model,
                                      @RequestParam("searchBy") String searchBy,
                                      @RequestParam("searchValue") String searchValue) {
        model.addAttribute("filedsArray", fields);
        if (searchBy.equals("--- Search By ---")) {
            model.addAttribute("msg", "Must select a valid field to search by");
            return "searchCourses";
        }

        if (searchValue.isEmpty() && !searchBy.equals("list all")) {
            model.addAttribute("msg", "Must input a value to search");
            return "searchCourses";
        }

        List<Courses> results = new LinkedList<Courses>();

        if (searchBy.equals("price")) {
            try {
                Integer integerVal = Integer.parseInt(searchValue);
                results = courseService.getCourseByPrice(integerVal);
            } catch (NumberFormatException e) {
                String msg = "Input value for field:\""
                        + searchBy
                        + "\" must be Integer";
                model.addAttribute("msg", msg);
                return "searchCourses";
            }
        } else if (searchBy.equals("id")) {
            Courses course = courseService.getCourseById(searchValue);
            if (course != null)
                results.add(course);
        } else if (searchBy.equals("name")){
            results = courseService.getCourseByName(searchValue);
        } else if (searchBy.equals("list all")) {
            results = courseService.getCourses();
        } else {
            System.out.println("error!");
        }
        model.addAttribute("result", results);
        return "searchCourses";
    }
    /************************Search Course************************/

}
