package co.edureka.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import co.edureka.controller.Courses;

@Component("courseDAO")
public class CourseDAO {
	
private NamedParameterJdbcTemplate jdbc;

	
	@Autowired
	public void setDataSource(DataSource jdbc){
		this.jdbc=new NamedParameterJdbcTemplate(jdbc);
		
	}	


	public void insertCourse(Courses course) {	
		BeanPropertySqlParameterSource params=new BeanPropertySqlParameterSource(course);
		jdbc.update("insert into courses(id,name,price) values (:id,:name,:price) ", params);		
	}
	
	public void deleteCourse(String courseName) {	
				
		String sql="delete from courses where name=:courseName ";		
		SqlParameterSource param=new MapSqlParameterSource("courseName",courseName);
		System.out.println(sql);
		jdbc.update(sql, param);   	
	}
	
	public void updateCourse(Courses course,String courseName){
		
		MapSqlParameterSource params=new MapSqlParameterSource();
		
		params.addValue("name",course.getName());params.addValue("id",course.getId());
		params.addValue("price",course.getPrice());params.addValue("old",courseName);		
		
		String sql="update courses set name=:name , id=:id ,price=:price where name=:old ";
		System.out.println(sql);
		jdbc.update(sql, params);
	}


	public List<Courses> getCourses() {
		
		
		return jdbc.query("select * from courses", new RowMapper<Courses>() {

			public Courses mapRow(ResultSet rs, int rowNum) throws SQLException {
				Courses course = new Courses();

				course.setId(rs.getString("id"));
				course.setName(rs.getString("name"));
				course.setPrice(rs.getInt("price"));
				return course;
			}
		});
	}
		
		
		public Courses getCourse (String name){
			
			MapSqlParameterSource params = new MapSqlParameterSource();
			params.addValue("name",name);

			return (Courses) jdbc.queryForObject("select * from courses where name=:name", params,
					new RowMapper<Courses>() {

						public Courses mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							Courses course = new Courses();

							course.setId(rs.getString("id"));
							course.setName(rs.getString("name"));
							course.setPrice(rs.getInt("price"));
							
							return course;
						}

					});			
		}
		
}
