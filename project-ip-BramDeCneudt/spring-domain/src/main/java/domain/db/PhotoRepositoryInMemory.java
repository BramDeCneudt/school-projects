/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.db;

import domain.model.Photo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Bram
 */
public class PhotoRepositoryInMemory implements PhotoRepository {
    
    public static final HashMap<Long, Photo> photos = new HashMap<>();
    
    public PhotoRepositoryInMemory() {
    }

    @Override
    public void addPhoto(Photo photo) {
        photo.setId(this.getNextId());
        while (photos.containsKey(photo.getId())) {
            photo.setId(photo.getId()+1);
        }
        photos.put(photo.getId(), photo);
    }

    @Override
    public void removePhoto(long id) {
        photos.remove(id);
    }

    @Override
    public void editPhoto(Photo photo) {
        long id = photo.getId();
        if (!photos.containsKey(id)) {
            throw new DbException("fout id bestaat niet");
        }
        photos.put(id, photo);
    }

    @Override
    public List<Photo> getPhotos() {
        return new ArrayList<>(PhotoRepositoryInMemory.photos.values());
    }

    @Override
    public Photo getPhoto(long id) {
        return PhotoRepositoryInMemory.photos.get(id);
    }
    public long getNextId() {
        ArrayList<Photo> photoArray = new ArrayList<>(photos.values());
        //als er een foto is return die zijn index +1 zoniet return 1
        int index = photoArray.size();
        index--;
        return photoArray.size() > 0 ? photoArray.get(index).getId()+1 : 1;
    }

    @Override
    public void closeConnection() {
    }
    
}
