package co.edureka.dao;

import co.edureka.controller.Resume;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Component("resumeDAO")
public class ResumeDAO {
    private static final Logger logger = Logger.getLogger(ResumeDAO.class);
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
        List<Resume> ret = new LinkedList<Resume>();
        final String sql = "select * from resumes";
        logger.info(sql);
        try {
            ret = jdbcTemplate.query(sql, new ResumeMapper());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        return ret;
    }

    public void deleteResume(String trainer, String resume) {
        final String sql = "delete from resumes where trainer = :t and resume = :r ";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("t", trainer);
        params.addValue("r",resume);
        logger.info(sql);
        try {
            jdbcTemplate.update(sql, params);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
    }

    public List<Resume> getResumesByTrainer(String trainer) {
        final String sql = "select * from resumes where trainer = :t";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("t", trainer);
        List<Resume> ret = new LinkedList<Resume>();
        logger.info(sql);
        try {
            ret = jdbcTemplate.query(sql,params,new ResumeMapper());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        return ret;
    }

    private class ResumeMapper implements RowMapper<Resume> {
        public Resume mapRow(ResultSet rs, int rowNUm) throws SQLException {
            Resume resume = new Resume();
            resume.setTrainerName(rs.getString("trainer"));
            resume.setFileName(rs.getString("resume"));
            return resume;
        }
    }
}
