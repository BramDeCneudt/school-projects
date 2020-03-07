/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.db;

import domain.model.DomainException;
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
public class TypeRepositoryInDB implements TypeRepository {

    private EntityManagerFactory factory;
    private EntityManager manager;
    private String name = "DerbyPersistence";

    public TypeRepositoryInDB() {
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
    public void addType(Type type) {
        if (this.typeExists(type.getName())) {
            throw new DomainException("naam bestaat al!");
        }
        try {
            this.createConnection();
            manager.getTransaction().begin();
            manager.persist(type);
            manager.getTransaction().commit();

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            closeConnectionManger();
        }
    }

    @Override
    public void removeType(String name) {
        if (name.equals("foto")) {
            throw new DbException("default waarde mag niet verwijderd worden");
        }
        try {
            this.createConnection();
            manager.getTransaction().begin();
            Type type = manager.find(Type.class, name);
            manager.remove(type);
            manager.getTransaction().commit();

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            closeConnectionManger();
        }
    }

    @Override
    public Type getType(String name) {
        try {
            this.createConnection();
            return manager.find(Type.class, name);

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            closeConnectionManger();
        }
    }

    @Override
    public boolean typeExists(String name) {
        try {
            this.createConnection();
            return manager.find(Type.class, name) != null;

        } catch (Exception e) {
            throw new DatabaseException(e.getMessage());
        } finally {
            closeConnectionManger();
        }
    }

    @Override
    public List<Type> getTypes() {
        try {
            this.createConnection();
            Query query = manager.createQuery("SELECT x FROM Type x");
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
