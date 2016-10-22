package co.edureka.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import co.edureka.controller.Courses;

@Component("courseDAO")
public class CourseDAO {
    private static final Logger logger = Logger.getLogger(CourseDAO.class);
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    public boolean courseExists(Courses courses) {
        int cnt  = -1;
        final String SQL_COUNT = "select count(*) from courses where id = :id";
        SqlParameterSource param = new MapSqlParameterSource("id", courses.getId());
        logger.info(SQL_COUNT);
        try {
            cnt = jdbc.queryForInt(SQL_COUNT, param);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        return cnt != 0;
    }

    public void insertCourse(Courses course) {
        final String sql = "insert into courses(id,name,price,sessions) values (:id,:name,:price,:sessions) ";
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(course);
        logger.info(sql);
        try {
            jdbc.update(sql, params);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
    }

    public void deleteCourse(String id) {
        final String sql = "delete from courses where id = :id ";
        SqlParameterSource params = new MapSqlParameterSource("id", id);
        logger.info(sql);
        try {
            jdbc.update(sql, params);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
    }

    public void updateCourse(Courses course, String id) {
        final String sql = "update courses set name=:name , id=:id ,price=:price where id=:old";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", course.getName());
        params.addValue("id", course.getId());
        params.addValue("price", course.getPrice());
        params.addValue("old", id);
        logger.info(sql);
        try {
            jdbc.update(sql, params);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
    }


    public List<Courses> getCourses() {
        List<Courses> ret = new LinkedList<Courses>();
        final String sql = "select * from courses";
        logger.info(sql);
        try {
            ret = jdbc.query(sql, new CourseMapper());
        } catch (DataAccessException e) {
        }
        return ret;
    }

    public Courses getCourseById(String id) {
        Courses ret = null;
        String sql = "select * from courses where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        logger.info(sql);
        try {
            ret = jdbc.queryForObject(sql, params, new CourseMapper());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        return ret;
    }

    public List<Courses> getCoursebyName(String name) {
        List<Courses> ret = new LinkedList<Courses>();
        String sql = "select * from courses where name = :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        logger.info(sql);
        try {
            ret = jdbc.query(sql, params, new CourseMapper());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        return ret;
    }

    public List<Courses> getCourseByPrice(Integer price) {
        List<Courses> ret = new LinkedList<Courses>();
        String sql = "select * from courses where price = :price";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("price", price);
        logger.info(sql);
        try {
            return jdbc.query(sql, params, new CourseMapper());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        return ret;
    }

    private class CourseMapper implements RowMapper<Courses> {
        public Courses mapRow(ResultSet rs, int rowNUm) throws SQLException {
            Courses course = new Courses();
            course.setId(rs.getString("id"));
            course.setName(rs.getString("name"));
            course.setPrice(rs.getInt("price"));
            return course;
        }
    }

}
