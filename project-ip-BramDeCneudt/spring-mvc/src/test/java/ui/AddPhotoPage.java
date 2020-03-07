/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author Bram
 */
public class AddPhotoPage {
    
    private WebDriver driver;
    private static final String url="http://localhost:8080/ipdemo/portfolio/addphoto.htm";
    
    public AddPhotoPage() {
        driver = new FirefoxDriver();
        driver.get(url);
    }
    
}
