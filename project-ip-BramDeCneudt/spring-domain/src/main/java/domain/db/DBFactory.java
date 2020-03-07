/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.db;

/**
 *
 * @author Bram
 */
public class DBFactory {
    
    public static PhotoRepository getPhotoRepository(String type) {
        if (type == null) {return new PhotoRepositoryInMemory();}
        switch(type) {
            case "derby": return new PhotoRepositoryInDB();
            default: return new PhotoRepositoryInMemory();
            
        }
    }
    
    public static ProjectRepository getProjectRepository(String type) {
        if (type == null) {return new ProjectRepositoryInMemory();}
        switch(type) {
            case "derby" : return new ProjectRepositoryInDB();
            default: return new ProjectRepositoryInMemory();
        }
    }
    
    public static TypeRepository getTypeRepository(String type) {
        if (type == null) {return new TypeRepositoryInMemory();}
        switch (type) {
            case "derby": return new TypeRepositoryInDB();
            default: return new TypeRepositoryInMemory();
        }
    }
    
}
