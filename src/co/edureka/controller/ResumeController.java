package co.edureka.controller;

import co.edureka.service.ResumeService;
import co.edureka.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ResumeController {

    public static final String FILE_PATH = "/home/shawn.zhou/tmpUpload";
    private TrainerService trainerService;
    private ResumeService resumeService;
    private List<Trainer> trainerList = new ArrayList<Trainer>();
    private List<Resume> resumeList = new ArrayList<Resume>();

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

            model.addAttribute("msg", String.format("Resume file %s uploaded for trainer %s", name, trainerName));
            return "addResume";
        } catch (Exception e) {
            return "You failed to upload " + name + " => " + e.getMessage();
        }
    }

    //download resume
    @RequestMapping("downloadResume")
    public String downloadResume(Model model) {
        resumeList = (ArrayList<Resume>) resumeService.getResumes();
        for (Resume r : resumeList) {
            System.out.println(r.getFileName() + " - " + r.getFileName());
        }
        model.addAttribute("resumesArray", resumeList);
        return "downloadResume";
    }

    @RequestMapping("downloadResumeFile")
    public void downloadResumeFile(
            HttpServletResponse response,
            @RequestParam("resume") String fileName
    ) throws IOException {
        System.out.println("Inside downloadResumeFile");
        System.out.println(fileName);
        File dir = new File(FILE_PATH);
        if (!dir.exists()) {
            System.out.print("no resume directory");
        }

        File serverFile = new File(dir.getAbsolutePath() + File.separator + fileName);

        if (!serverFile.exists()) {
            String errorMessage = "Sorry. The file you are looking for does not exist";
            System.out.println(errorMessage);
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }

        String mimeType = URLConnection.guessContentTypeFromName(serverFile.getName());
        if (mimeType == null) {
            System.out.println("mimetype is not detectable, will take default");
            mimeType = "application/octet-stream";
        }
        System.out.println("mimetype : " + mimeType);
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + serverFile.getName() + "\""));
        response.setContentLength((int) serverFile.length());
        System.out.println(serverFile.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(serverFile));
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }


}
