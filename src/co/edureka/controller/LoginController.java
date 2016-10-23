package co.edureka.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import co.edureka.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    private static final Logger logger = Logger .getLogger(LoginController.class);

    private static final int BUFFER_SIZE = 4096;
    private String filePath = "/logs/server.log";
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    // first page is to login ?
    @RequestMapping("/")
    public String firstScreen(Model model) {
        logger.debug("Inside firstScreen Method");
        model.addAttribute("user", new User());
        return "login";
    }

    // login page
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              @RequestParam(value = "username", required = false) String username) {
        ModelAndView model = new ModelAndView();
        if (error != null) {
            logger.info("Invalid username or password");
            model.addObject("error", "Invalid username or password!");
        }
        if (logout != null) {
            logger.info("user logout successfully");
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");
        model.addObject("user", new User());
        return model;
    }

    // 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView accesssDenied() {
        ModelAndView model = new ModelAndView();
        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            model.addObject("username", userDetail.getUsername());
            logger.info("UserName: " + userDetail.getUsername() + " Authority: " + userDetail.getAuthorities() + " is denied");
        }
        model.setViewName("403");
        return model;
    }

    // home page, visitor can only access this page
    // @RequestMapping(value = {"/","/home"},method = RequestMethod.GET)
    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String homeScreen() {
        return "home";
    }

    // sign up
    @RequestMapping(value = "signUp")
    public String signUp(Model model, @Valid User user, BindingResult result) {
        logger.info(user);
        if(result.hasErrors()) {
            model.addAttribute("user", user);
            return "login";
        }

        if(userService.usedName(user.getUsername())) {
            model.addAttribute("user", user);
            model.addAttribute("msgS", "user name has been used");
            return "login";
        }

        if(userService.usedEmail(user.getEmail())) {
            model.addAttribute("user", user);
            model.addAttribute("msgS", "Email has been used");
            return "login";
        }

        userService.insertUser(user);
        logger.info("added user:" + user);
        String msgNice = "Course Inserted Successfully";
        model.addAttribute("msgNice", msgNice);
        model.addAttribute("user", new User());
        return "login";
    }



    // Why a downloaFile request here?
    @RequestMapping(value = "/downloadFile", method = RequestMethod.GET)
    public void doDownload(HttpServletRequest request,
                           HttpServletResponse response) throws IOException {

        // get absolute path of the application

        ServletContext context = request.getSession().getServletContext();// request.getServletContext();
        String appPath = context.getRealPath("");
        System.out.println("appPath = " + appPath);

        // construct the complete absolute path of the file
        String fullPath = appPath + filePath;
        File downloadFile = new File(fullPath);
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(downloadFile);

            // get MIME type of the file
            String mimeType = context.getMimeType(fullPath);
            if (mimeType == null) {
                // set to binary type if MIME mapping not found
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);

            // set content attributes for the response
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());

            // set headers for the response
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"",
                    downloadFile.getName());
            response.setHeader(headerKey, headerValue);

            // get output stream of the response
            OutputStream outStream = response.getOutputStream();

            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead = -1;

            // write bytes read from the input stream into the output stream
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outStream.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}