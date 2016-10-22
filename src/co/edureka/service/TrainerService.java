package co.edureka.service;

import co.edureka.controller.Trainer;
import co.edureka.dao.TrainerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("trainerService")
public class TrainerService {

    private TrainerDAO trainerDAO;

    @Autowired
    public void setTrainerDAO(TrainerDAO trainerDAO) {
        this.trainerDAO = trainerDAO;
    }

    public List<Trainer> getTrainers() {
        return trainerDAO.getTrainers();
    }

    public List<Trainer> getTrainersByName(String name) {
        return trainerDAO.getTrainersByName(name);
    }

    public List<Trainer> getTrainersByEx(Integer ex) {
        return trainerDAO.getTrainersByEx(ex);
    }
}
