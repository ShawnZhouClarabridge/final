package co.edureka.service;

import co.edureka.controller.Resume;
import co.edureka.dao.ResumeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ResumeService {
    private ResumeDAO resumeDAO;

    @Autowired
    public void setResumeDAO(ResumeDAO resumeDAO) {
        this.resumeDAO = resumeDAO;
    }

    public void insertResume(Resume resume)
    {
        resumeDAO.inserResume(resume);
    }
}
