/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import domain.model.Photo;
import domain.model.Project;
import domain.model.Type;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Bram
 */
public class ServiceTest {
    private Service service;
    private Project project;
    private Photo photo;
    
    public ServiceTest() {
        service = new ProjectService();
    }
    
    @Before
    public void setUp() {
        Type type = new Type("default", "def");
        project = new Project("testProject", "description");
        photo = new Photo("testPhoto", "description", type);
        service.addProject(project);
        service.addPhoto(photo);
    }
    
    @After
    public void tearDown() {
    }
    

}
