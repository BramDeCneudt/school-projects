/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.db;

import domain.model.Photo;
import domain.model.Project;
import domain.model.Type;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Bram
 */
public class PhotoRepositoryInDB implements PhotoRepository {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private String name = "DerbyPersistence";

    public PhotoRepositoryInDB() {
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
    public void addPhoto(Photo photo) {
        try {
            this.createConnection();
            manager.getTransaction().begin();
            manager.persist(photo);
            manager.getTransaction().commit();

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            closeConnectionManger();
        }
    }

    @Override
    public void removePhoto(long id) {
        try {
            this.createConnection();
            manager.getTransaction().begin();
            Photo photo = manager.find(Photo.class, id);
            manager.remove(photo);
            manager.getTransaction().commit();

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            closeConnectionManger();
        }
    }

    @Override
    public void editPhoto(Photo photo) {

        long id = photo.getId();
        String namePhoto = photo.getName();
        String description = photo.getDescription();
        String imagePath = photo.getImagePath();
        Type type = photo.getType();

        try {
            this.createConnection();
            manager.getTransaction().begin();
            Photo originalPhoto = manager.find(Photo.class, id);
            originalPhoto.setName(namePhoto);
            originalPhoto.setDescription(description);
            originalPhoto.setImagePath(imagePath);
            originalPhoto.setType(type);
            manager.getTransaction().commit();

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            closeConnectionManger();
        }
    }

    @Override
    public List<Photo> getPhotos() {
        try {
            this.createConnection();
            Query query = manager.createQuery("SELECT x FROM Photo x");
            return query.getResultList();
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            this.closeConnectionManger();
        }
    }

    @Override
    public Photo getPhoto(long id) {
        try {
            this.createConnection();
            return manager.find(Photo.class, id);
        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            this.closeConnectionManger();
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
