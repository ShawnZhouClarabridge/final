package co.edureka.controller;

import co.edureka.service.ResumeService;
import co.edureka.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ResumeController {

    public static final String FILE_PATH = "/home/shawn.zhou/tmpUpload";
    private TrainerService trainerService;
    private ResumeService resumeService;
    private List<Trainer> trainerList = new ArrayList<Trainer>();

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
        if(trainerName.equals("--- Select Trainer ---")) {
            model.addAttribute("msgT", "Must select a valid trainer");
            return "addResume";
        }
        if(file.isEmpty()) {
            model.addAttribute("msgF", "Must select a valid resume");
            return "addResume";
        }

        String name= file.getOriginalFilename();
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

            Resume resume = new Resume(trainerName,file.getOriginalFilename());
            resumeService.insertResume(resume);

            model.addAttribute("msg", String.format("Resume file %s uploaded for trainer %s", name, trainerName));
            return "addResume";
        } catch (Exception e) {
            return "You failed to upload " + name + " => " + e.getMessage();
        }
    }


}
