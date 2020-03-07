/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Bram
 */
@Entity
public class Type implements Serializable {

    @Id
    @NotNull(message = "{error.type.name.NotNull}")
    @NotBlank(message = "{error.type.name.NotBlank}")
    private String name;

    @NotNull(message = "{error.type.abbreviation.NotNull}")
    @NotBlank(message = "{error.type.abbreviation.NotBlank}")
    @Size(min = 2, max = 3, message = "{error.type.abbreviation.Size}")
    private String abbreviation;

    public Type() {

    }


    public Type(String name, String abbreviation) {
        setName(name);
        setAbbreviation(abbreviation);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the abbreviation
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param abbreviation the abbreviation to set
     */
    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation.toUpperCase();
    }
    
    @Override
    public boolean equals(Object object) {
        if (object instanceof Type) {
            Type type = (Type) object;
            return this.name.equals(type.getName());
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

}
