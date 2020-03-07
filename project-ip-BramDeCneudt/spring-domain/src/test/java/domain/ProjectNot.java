/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.model.Photo;
import domain.model.Project;
import domain.model.Type;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bram
 */
public class ProjectNot {
    
    Project project;
    Photo foto;
    Photo foto1;
    
    public ProjectNot() {
    }
    
    
    @Before
    public void setUp() {
        Type type = new Type("default", "def");
        project = new Project("project", "description");
        foto = new Photo("name", "description", type);
        foto1 = new Photo("name1", "desciption1", type);
        
        project.addPhoto(foto);
        
        
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void Project_voegt_Photo_toe() {
        project.addPhoto(foto1);
        assertTrue( project.getPhotos().contains(foto1));
    }
}
