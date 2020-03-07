/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import domain.model.DomainException;
import domain.model.Photo;
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
public class PhotoNot {
    
    Type type;
    
    public PhotoNot() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        type = new Type("default", "def");
    }
    
    @After
    public void tearDown() {
    }
    
    @Test(expected = DomainException.class)
    public void Photo_Met_Lege_Naam_gooit_Exception() {
        Photo photo = new Photo("", "description", type);
    }
    
    public void Photo_Met_Lege_Description_gooit_geen_Exception() {
        Photo photo = new Photo("naam", "", type);
    }    
    
    @Test(expected = DomainException.class)
    public void Photo_Met_Null_Naam_gooit_Exception() {
        Photo photo = new Photo(null, "description", type);
    }

    @Test(expected = DomainException.class)
    public void Photo_Met_Null_Description_gooit_Exception() {
        Photo photo = new Photo("naam", null, type);
    }    
    
    @Test
    public void Photo_Met_Juist_Naam_En_Description_Maakt_Photo() {
        Photo photo = new Photo("naam", "hello", type);
        assertEquals(photo.getName(), "naam");
        assertEquals(photo.getDescription(), "hello");
        assertEquals(photo.getId(), 0);
    }

}
