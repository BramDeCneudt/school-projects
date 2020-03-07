/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.model.Photo;
import domain.model.Project;
import domain.model.Type;
import java.io.IOException;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;


/**
 *
 * @author Bram
 */
public interface Service {
    
    
    Project getProject(long id);
    void addProject(Project project);
    void editProject(Project project);
    void deleteProject(long id);
    List<Project> getProjects();
    
    void addPhoto(Photo photo);
    Photo getPhoto(long indexPhoto);
    void editPhoto(Photo photo);
    void deletePhoto(long indexPhoto);
    
    void addPhotoToProject(Project project, Photo photo);
    void removePhotoFormProject(Project project, Photo photo);
    
    void addType(Type type);
    void removeType(String name);
    Type getType(String name);
    boolean typeExists(String name);
    List<Type> getTypes();
    
    void upload(Photo photo, String beginPath, MultipartFile image);
    void editUpload(Photo photo, String beginPath, MultipartFile image);
    
    String getPrefix();
    void close();
    
}
