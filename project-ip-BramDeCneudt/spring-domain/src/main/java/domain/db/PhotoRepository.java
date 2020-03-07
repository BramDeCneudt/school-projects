/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.db;

import domain.model.Photo;
import java.util.List;

/**
 *
 * @author Bram
 */
public interface PhotoRepository {
    
    void addPhoto(Photo photo);
    void removePhoto(long id);
    void editPhoto(Photo photo);
    
    List<Photo> getPhotos();
    Photo getPhoto(long id);
    
    void closeConnection();
    
}
