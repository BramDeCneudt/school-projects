/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Bram
 */
@Entity
public class Project implements Serializable {

    @Id
    @GeneratedValue
    @Min(value = 0, message = "{error.project.id.Min}")
    private long id;

    @NotNull(message = "{error.project.name.NotNull}")
    @NotBlank(message = "{error.project.name.NotBlank}")
    private String name;
    @NotNull(message = "{error.project.description.NotNull}")
    private String description;
    @OneToMany
    private Set<Photo> photos;

    public Project() {
        photos = new HashSet<>();
        setId(0);
    }

    public Project(String name, String description) {
        this();
        setName(name);
        setDescription(description);

    }

    public Project(String name) {
        this();
        setName(name);
        setDescription("");
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        if (id < 0) {
            throw new DomainException("fout in setId-Project: " + id);
        }
        this.id = id;
    }

    public void addPhoto(Photo photo) {
        if (photo == null) {
            throw new DomainException("photo mag niet null zjn");
        }
            this.photos.add(photo);

    }

    public void removePhoto(Photo photo) {
        if (!(this.photos.contains(photo))) {
            throw new DomainException("foto bestaat niet in dit project!");
        }
        this.photos.remove(photo);
    }

    @Override
    public int hashCode() {
        return new Long(id).hashCode();
    }

    public void setPhotos(List<Photo> photos) {
        if (photos == null) {
            throw new DomainException("fout in setPhotos");
        }
        this.photos = new HashSet<>(photos);
    }

    public List<Photo> getPhotos() {
        return new ArrayList<Photo>(photos);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || !(object instanceof Project)) {
            return false;
        }

        Project project = (Project) object;
        if (project.getId() == this.getId()) {
            return true;
        }
        return false;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
