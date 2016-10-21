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
    public void setCourseDao(CourseDAO courseDao) {
        this.courseDao = courseDao;
    }

    public boolean courseExists(Courses courses) {
        return courseDao.courseExists(courses);
    }


    public void insertCourse(Courses course) {
        courseDao.insertCourse(course);
    }

    public void deleteCourse(String id) {
        courseDao.deleteCourse(id);
    }

    public void updateCourse(Courses course, String id) {
        courseDao.updateCourse(course, id);
    }

    public List<Courses> getCourses() {
        return courseDao.getCourses();
    }

    public Courses getCourseById(String id) {
        return courseDao.getCourseById(id);
    }

    public List<Courses> getCourseByName(String name) {
        return courseDao.getCoursebyName(name);
    }

    public List<Courses> getCourseByPrice(Integer price) {
        return courseDao.getCourseByPrice(price);
    }


}
