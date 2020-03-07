/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.db;

import domain.model.Photo;
import domain.model.Project;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Bram
 */
public class ProjectRepositoryInMemory implements ProjectRepository {
    
    /*TODO
        
    */
    
    private static final HashMap<Long, Project> projects = new HashMap<>();
    
    public ProjectRepositoryInMemory() {
    }
    /**
     *
     * @param id
     * @return
     */
    @Override
    public Project getProject(long id) {
        return ProjectRepositoryInMemory.projects.get(id);
    }

    @Override
    public List<Project> getProjects() {
        return new ArrayList<>(ProjectRepositoryInMemory.projects.values());
    }

    @Override
    public void addProject(Project project) {
        project.setId(this.getNextId());
        while (ProjectRepositoryInMemory.projects.containsKey(project.getId())) {
            project.setId(project.getId()+1);
        }
        ProjectRepositoryInMemory.projects.put(project.getId(), project);
    }

    @Override
    public void removeProject(long id) {
        ProjectRepositoryInMemory.projects.remove(id);
    }

    @Override
    public void editProject(Project project) {
        long id = project.getId();
        if (!ProjectRepositoryInMemory.projects.containsKey(id)) {
            throw new DbException("fout in editProject-projectRepoInMemo: " + id + " " + project);
        }
        ProjectRepositoryInMemory.projects.put(id, project);
    }


    private long getNextId() {
        ArrayList<Project> projectArray = new ArrayList<>(ProjectRepositoryInMemory.projects.values());
        //als er een foto is return die zijn index +1 zoniet return 1
        int index = projectArray.size();
        index--;
        return projectArray.size() > 0 ? projectArray.get(index).getId()+1 : 1;
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
