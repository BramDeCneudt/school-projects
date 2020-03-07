/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.db;

import domain.model.Photo;
import domain.model.Project;
import java.util.List;
/**
 *
 * @author Bram
 */
public interface ProjectRepository {
    
    /*************
     * TODO:
     * - editproject: id wegdoen
     * 
     * @param project 
     */
    
    void addProject(Project project);
    void removeProject(long id);
    void editProject(Project project);

    
    Project getProject(long index);
    List<Project> getProjects();
    
    void closeConnection();
    
}
