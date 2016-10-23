package co.edureka.dao;

import co.edureka.controller.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserDAO {
    private static final Logger logger = Logger.getLogger(UserDAO.class);
    private NamedParameterJdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource jdbc) {
        this.jdbc = new NamedParameterJdbcTemplate(jdbc);
    }

    public boolean userNameExists(String userName) {
        int cnt = -1;
        final String SQL_COUNT = "select count(*) from users where username = :name";
        SqlParameterSource param = new MapSqlParameterSource("name", userName);
        logger.info(SQL_COUNT);
        try {
            cnt = jdbc.queryForInt(SQL_COUNT, param);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        return cnt != 0;
    }

    public boolean emailExists(String email) {
        int cnt = -1;
        final String SQL_COUNT = "select count(*) from users where email = :e";
        SqlParameterSource param = new MapSqlParameterSource("e", email);
        logger.info(SQL_COUNT);
        try {
            cnt = jdbc.queryForInt(SQL_COUNT, param);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
        }
        return cnt != 0;
    }

    public void insertUser(User user) throws DataAccessException {
        final String sql = "INSERT INTO users(\n" +
                "            username, password, email, enabled)\n" +
                "    VALUES (:name, :pw, :email, :enabled);\n";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", user.getUsername());
        params.addValue("pw", user.getPassword());
        params.addValue("email", user.getEmail());
        params.addValue("enabled", true);
        logger.info(sql);
        try {
            jdbc.update(sql, params);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }

    public void insertAuth(String name) throws DataAccessException {
        final String sql = "INSERT INTO authorities(\n" +
                "            username, authority)\n" +
                "    VALUES (:name, :role);\n";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("role", "ROLE_USER");
        logger.info(sql);
        try {
            jdbc.update(sql, params);
        } catch (DataAccessException e) {
            logger.error(e.getMessage());
            throw e;
        }
    }
}
