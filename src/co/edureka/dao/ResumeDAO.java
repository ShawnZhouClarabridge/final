package co.edureka.dao;

import co.edureka.controller.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component("resumeDAO")
public class ResumeDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
    }

    public void inserResume(Resume resume) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(resume);
        jdbcTemplate.update("insert into resumes (trainer,resume) values (:trainerName, :fileName) ", params);
    }
}
