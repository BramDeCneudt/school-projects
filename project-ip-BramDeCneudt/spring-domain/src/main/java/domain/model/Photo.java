/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import java.util.Base64;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Bram
 */
@Entity
public class Photo implements Serializable {

    /*TODO 
    - private String type --> nog implementeren maak string met types dat je kan toevoegen
    - private long sequence;
     */
    @Id
    @GeneratedValue
    @Min(value = 0, message = "{error.photo.id.Min}")
    private long id;

    @NotNull(message = "{error.photo.name.NotNull}")
    @NotBlank(message = "{error.photo.name.NotBlank}")
    private String name;
    @NotNull(message = "{error.photo.description.NotNull}")
    private String description;
    private String imagePath;
    private Type type;

    public Photo() {
    }

    public Photo(String name, String description, Type type) {
        setName(name);
        setDescription(description);
        setId(0);
        setType(type);
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
            throw new DomainException("Fout in setId: " + id);
        }
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        if (description == null) {
            throw new DomainException("Fout in Foto-setDescription: " + description);
        }
        this.description = description;
    }

    public void setImagePath(String imagePath) {
        if (imagePath == null || imagePath.trim().isEmpty()) {
            throw new DomainException("imagepath is null of leeg");
        }
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    @Override
    public boolean equals(Object object) {
        boolean test = object instanceof Photo;
        if ((object instanceof Photo)) {
            Photo photo = (Photo) object;
            return this.getId() == photo.getId();
        }
        return false;
    }

    @Override
    public int hashCode() {
        return new Long(id).hashCode();
    }

    public String getColors(String baseURL) {

        String url = "https://api.imagga.com/v1/colors?url=" + baseURL + imagePath;

        String api_key = "acc_f42bba920083d77";
        String api_secret = "434682dc2a8005477d927c7a8299a1bd";

        String encode = api_key + ":" + api_secret;

        byte[] encoded = Base64.getEncoder().encode(encode.getBytes());
        String encoding = new String(encoded);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encoding);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, request, String.class);

        return response.getBody();
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        if (type == null) {
            throw new DomainException("type is null");
        }
        this.type = type;
    }

}
