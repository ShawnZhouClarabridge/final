package co.edureka.dao;


import co.edureka.controller.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
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
        return jdbc.query("select * from trainers",
                new RowMapper<Trainer>() {
                    public Trainer mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Trainer trainer = new Trainer();
                        trainer.setAddress(rs.getString("address"));
                        trainer.setName(rs.getString("name"));
                        trainer.setEmail(rs.getString("email"));
                        trainer.setExperience(rs.getInt("experience"));
                        trainer.setPhone(rs.getLong("phone"));
                        return trainer;
                    }
                });
    }
}
