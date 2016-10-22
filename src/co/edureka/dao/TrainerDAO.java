package co.edureka.dao;


import co.edureka.controller.Trainer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Component("trainerDAO")
public class TrainerDAO {
    private static final Logger logger = Logger.getLogger(CourseDAO.class);
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    public List<Trainer> getTrainers() {
        List<Trainer> ret = new LinkedList<Trainer>();
        String sql = "select * from trainers";
        logger.info(sql);
        try {
            ret =  jdbc.query(sql, new TrainerMapper());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        return ret;
    }

    public List<Trainer> getTrainersByName(String name) {
        List<Trainer> ret = new LinkedList<Trainer>();
        String sql = "select * from trainers where name = :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        logger.info(sql);
        try {
            ret = jdbc.query(sql, params, new TrainerMapper());
        } catch (DataAccessException e) {
            logger.info(e.getMessage());
        }
        return ret;
    }

    public List<Trainer> getTrainersByEx(Integer ex) {
        List<Trainer> ret = new LinkedList<Trainer>();
        String sql = "select * from trainers where experience = :ex";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ex", ex);
        logger.info(sql);
        try {
            ret = jdbc.query(sql, params, new TrainerMapper());
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        return ret;
    }

    private class TrainerMapper implements RowMapper<Trainer> {
        public Trainer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Trainer trainer = new Trainer();
            trainer.setAddress(rs.getString("address"));
            trainer.setName(rs.getString("name"));
            trainer.setEmail(rs.getString("email"));
            trainer.setExperience(rs.getInt("experience"));
            trainer.setPhone(rs.getLong("phone"));
            return trainer;
        }
    }
}
