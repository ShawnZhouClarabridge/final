package co.edureka.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CalendarController {
    private static final Logger logger = Logger.getLogger(CalendarController.class);

    @Autowired
    private JavaMailSender mailSender;
    private static String batch_na = null;
    ArrayList<String> noTrainer = new ArrayList<String>();

    @RequestMapping("newCalendar")
    public ModelAndView getCalendar() {
        ArrayList<String> list = new ArrayList<String>();
        Connection con = DBConnection.getConnection();

        ResultSet set = null;
        try {
            Statement st = con.createStatement();
            String sql = "select * from courses";
            logger.info(sql);
            set = st.executeQuery(sql);
            while (set.next()) {
                String c = set.getString("name");
                logger.info(c);
                list.add(c);
            }

        } catch (Exception e) {
            logger.info("Error while checking");
            e.printStackTrace();
        }


        return new ModelAndView("newCalendar", "list", list);
    }

    @RequestMapping(value = "calendarResult", method = RequestMethod.POST)
    public ModelAndView addCalendar(@RequestParam Map<String, String> param) {


        logger.info("Ya its working");
        String course = param.get("course");
        String startdate = param.get("startdate");
        String enddate = param.get("enddate");
        String time = param.get("sessions");
        int duration = Integer.parseInt(time);
        logger.info(startdate);
        logger.info(enddate);

        startdate = convertDate(startdate);
        enddate = convertDate(enddate);

        logger.info(startdate + "   " + enddate);

        String message = null;
        Connection con = DBConnection.getConnection();
        int i = 0;

        try {
            Statement st = con.createStatement();
            String sql = "insert into calendar(course,start_date,end_date,sessions,trainer) values ('" + course + "','" + startdate + "','" + enddate + "','" + duration + "',null )";
            logger.info(sql);
            i = st.executeUpdate(sql);

            if (i != 0) {
                logger.info("calendar Inserted Successful");
                message = "Course have been added into calendar successfully";
            } else {
                logger.info("Insertion failed");
                message = "Error occured while adding course into calendar";
            }
        } catch (Exception e) {
            logger.info("Error while checking");
        }


        logger.info(message);
        return new ModelAndView("calendarResult", "message", message);

    }


    @RequestMapping("assignCourses")
    public ModelAndView assign() {

        ArrayList<Course> course_list = new ArrayList<Course>(10);
        ArrayList<String> trainers = new ArrayList<String>(10);
        Connection con = DBConnection.getConnection();

        ResultSet set1 = null;
        try {
            Statement st = con.createStatement();
            String sql = "select * from calendar where trainer is null";
            logger.info(sql);
            set1 = st.executeQuery(sql);
            while (set1.next()) {
                String col1 = set1.getString(1);
                String col2 = set1.getString(2);
                String col3 = set1.getString(3);
                int col4 = set1.getInt(4);
                logger.info(col1 + col2 + col3 + col4);
                Course c = new Course(col1, col2, col3, col4);
                logger.info(c);
                course_list.add(c);
            }

        } catch (Exception e) {
            logger.info("Error while checking");
            e.printStackTrace();
        }
        logger.info("Number of courses " + course_list.size());

        ResultSet set2 = null;
        try {
            Connection con1 = DBConnection.getConnection();
            Statement st = con1.createStatement();
            String sql = "select * from trainers";
            logger.info(sql);
            set2 = st.executeQuery(sql);
            while (set2.next()) {
                String col1 = set2.getString(1);

                logger.info(col1);

                trainers.add(col1);
            }

        } catch (Exception e) {
            logger.info("Error while checking");
            e.printStackTrace();
        }


        Map<String, Object> model = new HashMap<String, Object>();
        ArrayList<String> coursenames = new ArrayList<String>();
        for (Course cr : course_list) {
            String st = cr.getName();
            logger.info(">>>>> " + st + "  <<<<<");
            coursenames.add(st);
        }
        model.put("course_list", course_list);
        model.put("trainers", trainers);
        model.put("courses", coursenames);

        logger.info(trainers);
        return new ModelAndView("assignment2", "model", model);


    }

    @RequestMapping("batchAssign")
    public ModelAndView callBatch() {
        logger.info("In to batch Assign");
        ArrayList<String> batches = new ArrayList<String>();
        String sql = " select distinct course from calendar where trainer is null    ";
        Connection con = DBConnection.getConnection();
        ResultSet re = null;
        try {
            Statement st = con.createStatement();
            logger.info(sql);
            re = st.executeQuery(sql);
            while (re.next()) {
                batches.add(re.getString(1));
                logger.info(re.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Map<String, Object> model = new HashMap<String, Object>();
        noTrainer = batches;
        model.put("batches", noTrainer);
        return new ModelAndView("selectBatch", "model", model);
    }

    @RequestMapping("batAssign")
    public ModelAndView batchAssign(HttpServletRequest req, HttpServletResponse res) {
        logger.info("Got it inside " + req.getParameter("course"));
        CalendarController.batch_na = req.getParameter("course");

        ArrayList<Course> course_list = new ArrayList<Course>(10);
        ArrayList<String> trainers = new ArrayList<String>(10);
        Connection con = DBConnection.getConnection();

        ResultSet set1 = null;
        try {
            Statement st = con.createStatement();
            String sql = "select * from calendar where course='" + CalendarController.batch_na + "' and trainer is null";
            logger.info(sql);
            set1 = st.executeQuery(sql);
            while (set1.next()) {
                String col1 = set1.getString(1);
                String col2 = set1.getString(2);
                String col3 = set1.getString(3);
                int col4 = set1.getInt(4);
                logger.info(col1 + col2 + col3 + col4);
                Course c = new Course(col1, col2, col3, col4);
                logger.info(c);
                course_list.add(c);
            }

        } catch (Exception e) {
            logger.info("Error while checking");
            e.printStackTrace();
        }
        logger.info("Number of courses " + course_list.size());

        ResultSet set2 = null;
        try {
            Connection con1 = DBConnection.getConnection();
            Statement st = con1.createStatement();
            String sql = "select * from trainers";
            logger.info(sql);
            set2 = st.executeQuery(sql);
            while (set2.next()) {
                String col1 = set2.getString(1);

                logger.info(col1);

                trainers.add(col1);
            }

        } catch (Exception e) {
            logger.info("Error while checking");
            e.printStackTrace();
        }


        Map<String, Object> model = new HashMap<String, Object>();
        ArrayList<String> coursenames = new ArrayList<String>();
        for (Course cr : course_list) {
            String st = cr.getName();
            logger.info(">>>>> " + st + "  <<<<<");
            coursenames.add(st);
        }
        model.put("course_list", course_list);
        model.put("trainers", trainers);
        model.put("courses", coursenames);
        model.put("batches", noTrainer);
        logger.info(trainers);

        return new ModelAndView("selectBatch", "model", model);
    }

    @RequestMapping("courseassignment")
    public ModelAndView courseAssignment(@RequestParam Map<String, String> param) {

        ResultSet rs = null;
        int i = 0;
        String coursedetail = param.get("course");
        String trainer = param.get("trainer");
        String[] data = coursedetail.split(",");
        String course_name = data[0];
        String start_date = data[1];
        String end_date = data[2];
        String sessions = data[3];
        int number = Integer.parseInt(sessions);
        String sql = "update calendar set trainer='" + trainer + "' where course='" + course_name + "' and start_date='" + start_date + "' and end_date='" + end_date + "' and sessions='" + number + "'   ";
        Connection con = DBConnection.getConnection();
        String share = null;
        try {
            Statement st = con.createStatement();

            logger.info(sql);
            i = st.executeUpdate(sql);
            if (i != 0) {
                share = trainer + " have been assigned the course successfully and an email have been sent ";
            }
            String sql2 = "select * from trainers where name='" + trainer + "' ";
            rs = st.executeQuery(sql2);
            String trainer_email = null;
            while (rs.next()) {
                trainer_email = rs.getString(3);
                logger.info("Trainers Email " + trainer_email);
            }
            String recipient = trainer_email;
            String subject = "New Course Assignment";
            String message = "Hi " + trainer + " ,"
                    + "You have been assigned the " + course_name + " course" +
                    " Starting Date - " + start_date +
                    " Ending Date -  " + end_date +
                    " Sessions - " + sessions +
                    " Regards ," + "\n" + "   CourseCalendar";
            HashMap<String, String> emailData = new HashMap<String, String>();
            emailData.put("recipient", recipient);
            emailData.put("subject", subject);
            emailData.put("message", message);

            doSendEmail(emailData);

        } catch (Exception e) {
            logger.info("Error while checking");
            e.printStackTrace();
        }


        return new ModelAndView("courseassignment", "share", share);
    }

    public void doSendEmail(HashMap<String, String> emailData) {
        // takes input from e-mail form
        String recipientAddress = emailData.get("recipient");
        String subject = emailData.get("subject");
        String message = emailData.get("message");

        // prints debug info
        logger.info("To: " + recipientAddress);
        logger.info("Subject: " + subject);
        logger.info("Message: " + message);

        // creates a simple e-mail object
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message);
        mailSender.send(email);
    }


    public String convertDate(String date) {
        String[] parts = date.split("/");
        String month = parts[0];
        String day = parts[1];
        String year = parts[2];
        logger.info(year + "-" + month + "-" + day);
        return year + "-" + month + "-" + day;
    }
}
