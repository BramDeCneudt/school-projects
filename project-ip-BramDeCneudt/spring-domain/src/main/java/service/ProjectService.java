/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.db.DBFactory;
import domain.db.PhotoRepository;
import domain.db.ProjectRepository;
import domain.db.TypeRepository;
import domain.model.Photo;
import domain.model.Project;
import domain.model.Type;
import domain.upload.Uploader;
import domain.upload.UploaderFactory;
import java.util.Iterator;
import java.util.List;
import org.springframework.core.env.Environment;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Bram
 */
public class ProjectService implements Service {

    /* TODO:
     *  close connection vervolledigen
     * 
     */
    private ProjectRepository projects;
    private PhotoRepository photos;
    private TypeRepository types;
    private Uploader uploader;
    private String prefix;

    public ProjectService(Environment env) {
        photos = DBFactory.getPhotoRepository(env.getProperty("photosDB"));
        projects = DBFactory.getProjectRepository(env.getProperty("projectsDB"));
        types = DBFactory.getTypeRepository(env.getProperty("typesDB"));
        uploader = UploaderFactory.createUploader(env.getProperty("uploader"), env.getProperty("cloud_name"), env.getProperty("api_key"), env.getProperty("api_secret"));

        prefix = env.getProperty("prefix");

        if (prefix == null) {
            prefix = "";
        }
        basicData();
    }

    public ProjectService() {
        photos = DBFactory.getPhotoRepository("local");
        projects = DBFactory.getProjectRepository("local");
        types = DBFactory.getTypeRepository("local");
        uploader = UploaderFactory.createUploader("local", null, null, null);
        basicData();
    }

    private void basicData() {
        if (projects.getProjects().size() == 0) {
            Project project = new Project("Bear Saga", "this is a bear saga description");
            projects.addProject(project);

            Type type = new Type("foto", "DEF");
            types.addType(type);
        }
    }

    @Override
    public Project getProject(long id) {
        return this.projects.getProject(id);
    }

    @Override
    public void addProject(Project project) {

        this.projects.addProject(project);
    }

    @Override
    public void deleteProject(long id) {
        Project project = this.getProject(id);
        Iterator<Photo> it = project.getPhotos().iterator();
        while (it.hasNext()) {
            Photo photo = it.next();
            project.removePhoto(photo);
            projects.editProject(project);
            photos.removePhoto(photo.getId());
        }

        this.projects.removeProject(id);
    }

    @Override
    public List<Project> getProjects() {
        return this.projects.getProjects();
    }

    @Override
    public Photo getPhoto(long indexPhoto) {
        return this.photos.getPhoto(indexPhoto);
    }

    //nog refactoren!
    @Override
    public void deletePhoto(long indexPhoto) {
        Photo photo = photos.getPhoto(indexPhoto);

        this.uploader.deleteUpload(photo, prefix);
        this.photos.removePhoto(indexPhoto);
    }

    @Override
    public void editPhoto(Photo photo) {
        this.photos.editPhoto(photo);
    }

    @Override
    public void addType(Type type) {
        types.addType(type);
    }

    @Override
    public void removeType(String name) {
        Type type = types.getType(name);
        Type defaultType = types.getType("foto");

        for (Photo photo : photos.getPhotos()) {
            if (photo.getType().equals(type)) {
                photo.setType(defaultType);
                photos.editPhoto(photo);
            }
        }
        types.removeType(name);
    }

    @Override
    public Type getType(String name) {
        return types.getType(name);
    }

    @Override
    public boolean typeExists(String name) {
        return types.typeExists(name);
    }

    @Override
    public List<Type> getTypes() {
        return types.getTypes();
    }

    @Override
    public void addPhoto(Photo photo) {
        this.photos.addPhoto(photo);
    }

    @Override
    public void editProject(Project project) {
        this.projects.editProject(project);
    }

    @Override
    public void upload(Photo photo, String beginPath, MultipartFile image) {
        uploader.upload(photo, beginPath, image);
    }

    @Override
    public void editUpload(Photo photo, String beginPath, MultipartFile image) {
        uploader.editUpload(photo, beginPath, image);
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public void close() {
        //geeft vreemde errors heb het weggecommend
//        photos.closeConnection();
//        projects.closeConnection();
//        types.closeConnection();
    }

    @Override
    public void addPhotoToProject(Project project, Photo photo) {
        project.addPhoto(photo);
    }

    @Override
    public void removePhotoFormProject(Project project, Photo photo) {
        project.removePhoto(photo);
    }

}
