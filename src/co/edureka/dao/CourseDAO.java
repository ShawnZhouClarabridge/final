package co.edureka.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

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
    private static final String SQL_COUNT = "select count(*) from courses where id = :id";
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    public boolean courseExists(Courses courses) {
        SqlParameterSource param = new MapSqlParameterSource("id", courses.getId());
        int cnt = jdbc.queryForInt(SQL_COUNT, param);
        return cnt != 0;
    }

    public void insertCourse(Courses course) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(course);
        jdbc.update("insert into courses(id,name,price,sessions) values (:id,:name,:price,:sessions) ", params);
    }

    public void deleteCourse(String id) {
        String sql = "delete from courses where id = :id ";
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        System.out.println(sql);
        jdbc.update(sql, param);
    }

    public void updateCourse(Courses course, String id) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("name", course.getName());
        params.addValue("id", course.getId());
        params.addValue("price", course.getPrice());
        params.addValue("old", id);

        String sql = "update courses set name=:name , id=:id ,price=:price where id=:old";
        System.out.println(sql);
        jdbc.update(sql, params);
    }


    public List<Courses> getCourses() {
        return jdbc.query("select * from courses", new CourseMapper());
    }

    public Courses getCourseById(String id) {
        Courses ret = null;
        String sql = "select * from courses where id = :id";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);
        try {
            ret = jdbc.queryForObject(sql, params, new CourseMapper());
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public List<Courses> getCoursebyName(String name) {
        String sql = "select * from courses where name = :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        return jdbc.query(sql, params, new CourseMapper());
    }

    public List<Courses> getCourseByPrice(Integer price) {
        String sql = "select * from courses where price = :price";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("price", price);
        return jdbc.query(sql, params, new CourseMapper());
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
