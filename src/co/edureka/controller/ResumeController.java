package co.edureka.controller;

import co.edureka.service.ResumeService;
import co.edureka.service.TrainerService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ResumeController {
    private static final Logger logger = Logger.getLogger(ResumeController.class);
    public static final String FILE_PATH = "/home/shawn.zhou/tmpUpload";
    private TrainerService trainerService;
    private ResumeService resumeService;
    private List<Trainer> trainerList = new ArrayList<Trainer>();
    private List<Resume> resumeList = new ArrayList<Resume>();
    private Resume toDelete = new Resume();

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }

    @Autowired
    public void setResumeService(ResumeService resumeService) {
        this.resumeService = resumeService;
    }

    //add resume
    @RequestMapping(value = "addResume")
    public String addResume(Model model) {
        trainerList = (ArrayList<Trainer>) trainerService.getTrainers();
        model.addAttribute("trainersArray", trainerList);
        return "addResume";
    }

    //create db record and save resume file
    @RequestMapping("uploadFile")
    public String updateResume(Model model,
                               @RequestParam("trainer") String trainerName,
                               @RequestParam("file") MultipartFile file) {
        model.addAttribute("trainersArray", trainerList);
        if (trainerName.equals("--- Select Trainer ---")) {
            model.addAttribute("msgT", "Must select a valid trainer");
            return "addResume";
        }
        if (file.isEmpty()) {
            model.addAttribute("msgF", "Must select a valid resume");
            return "addResume";
        }

        String name = file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            File dir = new File(FILE_PATH);
            if (!dir.exists())
                dir.mkdirs();

            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()
                    + File.separator + name);
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();

            Resume resume = new Resume(trainerName, file.getOriginalFilename());
            resumeService.insertResume(resume);
            logger.info(CheckUserAuth.checkUserAndAuth() + " add resume: " + resume);
            model.addAttribute("msg", String.format("Resume file %s uploaded for trainer %s", name, trainerName));
            return "addResume";
        } catch (Exception e) {
            logger.error(e.getMessage());
            return "You failed to upload " + name + " => " + e.getMessage();
        }
    }

    //delete resume
    @RequestMapping("deleteResume")
    public String selectTrainer(Model model) {
        resumeList = (ArrayList<Resume>) resumeService.getResumes();
        model.addAttribute("model", resumeList);
        return "deleteResume";
    }

    @RequestMapping("selectTrainer")
    public String selectResume(Model model, @RequestParam(value = "trainer", required = false) String trainer) {
        model.addAttribute("model", resumeList);
        if(trainer==null || trainer.isEmpty()) {
            model.addAttribute("msg", "must select a trainer!");
            return "deleteResume";
        }
        toDelete.setTrainerName(trainer);
        List<Resume> curResumeList = resumeService.getResumesByTrainer(trainer);
        model.addAttribute("selectedTrainer",trainer);
        model.addAttribute("curList",curResumeList);
        return "deleteResume";
    }

    @RequestMapping("deletedResume")
    public String deleteResume(Model model,
                                     @RequestParam(value = "resume", required = false) String resume) {
        if(resume==null || resume.isEmpty()) {
            model.addAttribute("model", resumeList);
            model.addAttribute("msg", "must select a resume!");
            return "deleteResume";
        }
        toDelete.setFileName(resume);
        resumeService.deleteResume(toDelete.getTrainerName(),toDelete.getFileName());
        logger.info(CheckUserAuth.checkUserAndAuth() + "deleted resume: " + toDelete);
        model.addAttribute("model", toDelete );
        return "resumeDeletion";
    }

    //download resume
    @RequestMapping("downloadResume")
    public String downloadResume(Model model) {
        resumeList = (ArrayList<Resume>) resumeService.getResumes();
        for (Resume r : resumeList) {
            logger.info(r.getFileName() + " - " + r.getFileName());
        }
        model.addAttribute("resumesArray", resumeList);
        return "downloadResume";
    }

    @RequestMapping("downloadResumeFile")
    public void downloadResumeFile(
            HttpServletResponse response,
            @RequestParam("resume") String fileName
    ) throws IOException {
        logger.info("Inside downloadResumeFile");
        logger.info(fileName);
        File dir = new File(FILE_PATH);
        if (!dir.exists()) {
            System.out.print("no resume directory");
        }

        File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);

        if (!serverFile.exists()) {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            logger.info(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType = URLConnection.guessContentTypeFromName(serverFile.getName());
        if (mimeType == null) {
            logger.info("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
        logger.info("mimetype : " + mimeType);
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + serverFile.getName() + "\""));
        response.setContentLength((int) serverFile.length());
        logger.info(serverFile.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(serverFile));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }


}
