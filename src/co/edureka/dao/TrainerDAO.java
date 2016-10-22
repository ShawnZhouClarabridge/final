package co.edureka.dao;


import co.edureka.controller.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component("trainerDAO")
public class TrainerDAO {
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    public List<Trainer> getTrainers() {
        String sql = "select * from trainers";
        return jdbc.query(sql,new TrainerMapper());
    }

    public List<Trainer> getTrainersByName(String name) {
        List<Trainer> ret = null;
        String sql = "select * from trainers where name = :name";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        try {
            ret = jdbc.query(sql, params, new TrainerMapper());
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return ret;
    }

    public List<Trainer> getTrainersByEx(Integer ex) {
        String sql = "select * from trainers where experience = :ex";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("ex", ex);
        return jdbc.query(sql,params,new TrainerMapper());
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
