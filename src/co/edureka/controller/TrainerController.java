package co.edureka.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import co.edureka.service.TrainerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainerController {
    private static final Logger logger = Logger.getLogger(TrainerController.class);
    public static ArrayList<String> list;
    public static String toUpdate = null;
    private final String[] fields = {"name", "experience", "list all"};
    private TrainerService trainerService;

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @RequestMapping("/addTrainer")
    public String newTrainer() {
        return "addTrainer";
    }

    @RequestMapping("/subTrainer")
    public ModelAndView addTrainer(@RequestParam Map<String, String> param) {
        logger.info("Ya its working");
        String name = param.get("name");
        String exp = param.get("exp");

        String email = param.get("email");
        String phone = param.get("phone");
        String address = param.get("address");
        String message = null;

        Connection con = DBConnection.getConnection();
        int i = 0;

        try {
            Statement st = con.createStatement();
            String sql = "insert into trainers(name,email,phone,experience,address) values ('" + name + "','" + email + "','" + phone + "','" + exp + "','" + address + "' )";
            logger.info(sql);
            i = st.executeUpdate(sql);

            if (i != 0) {
                logger.info("Inserted Successful");
                message = name + " have been added as trainer successfully.";
            } else {
                logger.info("Insertion failed");
                message = "Error occured while inserting Trainer record";
            }
        } catch (Exception e) {
            logger.info("Error while checking");
            e.printStackTrace();
        }


        return new ModelAndView("addTrainer", "message", message);


    }

    @RequestMapping("/deleteTrainer")
    public ModelAndView deleteTrainer() {

        Connection con = DBConnection.getConnection();

        ArrayList<String> trainers = new ArrayList<String>();
        ResultSet set = null;
        try {
            Statement st = con.createStatement();
            String sql = "select * from trainers";
            logger.info(sql);
            set = st.executeQuery(sql);

            while (set.next()) {
                String trainer = set.getString(1);
                trainers.add(trainer);
            }
        } catch (Exception e) {
            logger.info("Error while checking");
            e.printStackTrace();
        }
        logger.info(trainers);
        return new ModelAndView("deleteTrainer", "model", trainers);
    }

    @RequestMapping("trainerDeletion")
    public ModelAndView courseDeletion(@RequestParam Map<String, String> param) {
        String message = null;
        String delete = param.get("trainer");
        logger.info("Trainer to be deleted " + delete);
        Connection con = DBConnection.getConnection();
        int i = 0;

        try {
            Statement st = con.createStatement();
            String sql = "delete from trainers where name='" + delete + "'   ";
            logger.info(sql);
            i = st.executeUpdate(sql);

            if (i != 0) {
                message = delete + "  have been deleted from database successfully ";
            } else {
                message = "Error Occurred while deleting " + delete;
            }
        } catch (Exception e) {
            logger.info("Error while checking");
            e.printStackTrace();
        }


        return new ModelAndView("trainerDeletion", "model", message);
    }


    @RequestMapping("/updateTrainer")
    public ModelAndView updateTrainer() {

        Connection con = DBConnection.getConnection();

        ArrayList<String> trainers = new ArrayList<String>();
        ResultSet set = null;
        String email = "Email ID ";
        String exp = "Experience";
        String phone = "Phone Number";
        Map<String, Object> model = new HashMap<String, Object>();
        try {
            Statement st = con.createStatement();
            String sql = "select * from trainers";
            logger.info(sql);
            set = st.executeQuery(sql);

            while (set.next()) {
                String trainer = set.getString(1);
                trainers.add(trainer);
            }
            list = trainers;
        } catch (Exception e) {
            logger.info("Error while checking");
            e.printStackTrace();
        }
        logger.info(trainers);
        model.put("trainers", trainers);
        model.put("email", email);
        model.put("exp", exp);
        model.put("phone", phone);
        model.put("name", "Trainer name");
        return new ModelAndView("updateTrainer", "model", model);
    }

    @RequestMapping("/modifyTrainer")
    public ModelAndView modifyCourse(@RequestParam Map<String, String> param) {

        Connection con = DBConnection.getConnection();
        String trainer = param.get("trainer");
        toUpdate = trainer;
        ArrayList<String> courses = new ArrayList<String>();
        ResultSet set = null;
        int exp = 0;
        String email = "abc@abc.com";
        String name = "Trainer Name";
        long phone = 0;
        try {
            Statement st = con.createStatement();
            String sql = "select * from trainers where name='" + trainer + "' ";
            logger.info(sql);
            set = st.executeQuery(sql);

            while (set.next()) {
                name = set.getString(1);
                exp = set.getInt(2);
                email = set.getString(3);
                phone = Long.parseLong(set.getString(4));

            }
        } catch (Exception e) {
            logger.info("Error while checking");
            e.printStackTrace();
        }
        logger.info(courses);
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("exp", exp);
        model.put("email", email);
        model.put("phone", phone);
        model.put("trainers", list);
        model.put("name", name);

        return new ModelAndView("updateTrainer", "model", model);
    }

    @RequestMapping("/trainerUpdated")
    public ModelAndView courseUpdated(@RequestParam Map<String, String> param) {
        String name = param.get("tname");
        String exp = param.get("texp");
        String email = param.get("temail");
        String phone = param.get("tphone");
        int exper = Integer.parseInt(exp);
        long contact = Long.parseLong(phone);
        Connection con = DBConnection.getConnection();
        int i = 0;
        String message = null;
        String trainer = param.get("tname");
        ArrayList<String> courses = new ArrayList<String>();

        try {
            Statement st = con.createStatement();
            String sql = "update trainers set name='" + name + "' ,experience='" + exper + "',email='" + email + "',phone='" + contact + "' where name='" + TrainerController.toUpdate + "' ";
            logger.info(sql);
            i = st.executeUpdate(sql);

            if (i != 0) {
                message = "Trainer details have been updated successfully";
            }
        } catch (Exception e) {
            logger.info("Error while checking");
            e.printStackTrace();
        }
        logger.info(courses);


        return new ModelAndView("trainerUpdated", "model", message);
    }

    @RequestMapping("searchTrainer")
    public String searchTrainer(Model model) {
        model.addAttribute("filedsArray", fields);
        return "searchTrainers";
    }

    @RequestMapping("searchTrainersResult")
    public String searchCoursesResult(Model model,
                                      @RequestParam("searchBy") String searchBy,
                                      @RequestParam("searchValue") String searchValue) {
        model.addAttribute("filedsArray", fields);
        if (searchBy.equals("--- Search By ---")) {
            model.addAttribute("msg", "Must select a valid field to search by");
            return "searchTrainers";
        }

        if (searchValue.isEmpty() && !searchBy.equals("list all")) {
            model.addAttribute("msg", "Must input a value to search");
            return "searchTrainers";
        }

        List<Trainer> results = new LinkedList<Trainer>();
        if (searchBy.equals("name")) {
            logger.info(CheckUserAuth.checkUserAndAuth() + "search trainer by name: " + searchValue);
            results = trainerService.getTrainersByName(searchValue);
        } else if (searchBy.equals("experience")) {
            logger.info(CheckUserAuth.checkUserAndAuth() + "search trainer by experience: " + searchValue);
            try {
                Integer integerVal = Integer.parseInt(searchValue);
                results = trainerService.getTrainersByEx(integerVal);
            } catch (NumberFormatException e) {
                String msg = "Input value for field:\""
                        + searchBy
                        + "\" must be Integer";
                model.addAttribute("msg", msg);
                return "searchCourses";
            }
        } else if (searchBy.equals("list all")) {
            logger.info(CheckUserAuth.checkUserAndAuth() + "search course by all");
            results = trainerService.getTrainers();
        } else {
            logger.error("search trainer by invalid filed");
        }
        logger.info("Search trainers results: " + results);
        model.addAttribute("data", results);
        return "searchTrainers";
    }
}
