/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.db;

import domain.model.Project;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Bram
 */
public class ProjectRepositoryInDB implements ProjectRepository {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private String name = "DerbyPersistence";

    public ProjectRepositoryInDB() {
        factory = Persistence.createEntityManagerFactory(name);
    }

    private void createConnection() {
        manager = factory.createEntityManager();
    }

    private void closeConnectionManger() {
        manager.clear();
        if (manager.isOpen()) {
            manager.close();
        }
    }

    @Override
    public void addProject(Project project) {
        try {
            this.createConnection();
            manager.getTransaction().begin();
            manager.persist(project);
            manager.getTransaction().commit();

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            closeConnectionManger();
        }
    }

    @Override
    public void removeProject(long id) {
        try {
            this.createConnection();
            manager.getTransaction().begin();
            Project project = manager.find(Project.class, id);
            manager.remove(project);
            manager.getTransaction().commit();

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            closeConnectionManger();
        }
    }

    @Override
    public void editProject(Project project) {
        long id = project.getId();
        try {
            this.createConnection();
            Project projectOriginal = manager.find(Project.class, id);
            manager.getTransaction().begin();
            projectOriginal.setName(project.getName());
            projectOriginal.setDescription(project.getDescription());
            projectOriginal.setPhotos(project.getPhotos());
            manager.getTransaction().commit();

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            closeConnectionManger();
        }
    }

    @Override
    public Project getProject(long index) {

        try {
            this.createConnection();
            return manager.find(Project.class, index);

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            closeConnectionManger();
        }
    }

    @Override
    public List<Project> getProjects() {

        try {
            this.createConnection();
            Query query = manager.createQuery("SELECT x FROM Project x");
            return query.getResultList();

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            closeConnectionManger();
        }

    }

    @Override
    public void closeConnection() {
        try {
            closeConnectionManger();
            factory.close();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        }
    }

}
