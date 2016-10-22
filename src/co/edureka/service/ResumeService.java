package co.edureka.service;

import co.edureka.controller.Resume;
import co.edureka.dao.ResumeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ResumeService {
    private ResumeDAO resumeDAO;

    @Autowired
    public void setResumeDAO(ResumeDAO resumeDAO) {
        this.resumeDAO = resumeDAO;
    }

    public void insertResume(Resume resume)
    {
        resumeDAO.insertResume(resume);
    }

    public List<Resume> getResumes() {
        return resumeDAO.getResumes();
    }

    public void deleteResume(String trainer, String resume) {
        resumeDAO.deleteResume(trainer,resume);
    }

    public List<Resume> getResumesByTrainer(String t) {
        return resumeDAO.getResumesByTrainer(t);
    }
}
