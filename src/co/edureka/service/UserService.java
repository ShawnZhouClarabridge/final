package co.edureka.service;

import co.edureka.controller.User;
import co.edureka.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }


    public boolean usedName(String name) {
        return userDAO.userNameExists(name);
    }

    public boolean usedEmail(String email) {
        return userDAO.emailExists(email);
    }

    public void insertUser(User user) throws DataAccessException {
        try {
            userDAO.insertUser(user);
            userDAO.insertAuth(user.getUsername());
        } catch (DataAccessException e) {
            throw e;
        }
    }
}
