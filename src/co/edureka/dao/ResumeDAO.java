package co.edureka.dao;

import co.edureka.controller.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("resumeDAO")
public class ResumeDAO {
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(jdbc);
    }

    public void insertResume(Resume resume) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(resume);
        jdbcTemplate.update("insert into resumes (trainer,resume) values (:trainerName, :fileName) ", params);
    }

    public List<Resume> getResumes() {
        return jdbcTemplate.query("select * from resumes",
                new RowMapper<Resume>() {
                    public Resume mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Resume resume = new Resume();
                        resume.setTrainerName(rs.getString("trainer"));
                        resume.setFileName(rs.getString("resume"));
                        return resume;
                    }
                });
    }
}
