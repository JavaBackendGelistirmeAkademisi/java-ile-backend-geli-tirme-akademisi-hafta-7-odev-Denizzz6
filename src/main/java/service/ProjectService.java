package service;

import exception.OutOfException;
import model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.ProjectRepository;

import java.util.List;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;


    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createProject(Project project) throws OutOfException {
        if (createProject(project)==null){
            throw new OutOfException("Proje bulunamadı.");
        }
        projectRepository.save(project);
        return project;
    }

    public Project updateSituation(Project project) throws OutOfException{

        return projectRepository.save(project);
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proje bulunamadı, ID: " + id));
    }

    public List<Project> getAllProject(){
        return projectRepository.findAll();
    }

}
