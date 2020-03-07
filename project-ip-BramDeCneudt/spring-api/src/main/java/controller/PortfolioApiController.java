
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.model.Project;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import service.Service;

/**
 *
 * @author Bram
 */
@RestController
@RequestMapping(value = "/")
public class PortfolioApiController {

    private final Service service;

    public PortfolioApiController(@Autowired Service service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getIndex() {
        return new ModelAndView("redirect:/html");
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getproject/{id}", method = RequestMethod.GET)
    public Project getProject(@PathVariable long id) {
        return this.service.getProject(id);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/getprojects", method = RequestMethod.GET)
    public List<Project> getProjects() {
        return this.service.getProjects();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/admin/addproject", method = RequestMethod.POST)
    public ResponseEntity addProject(@Valid Project project, BindingResult result) {

        if (result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getFieldError());
        }

        this.service.addProject(project);
        return ResponseEntity.ok("[{\"message\":\"operation received and processed\"}]");
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/admin/editproject", method = RequestMethod.POST)
    public ResponseEntity editProject(@Valid Project project, BindingResult result) {
        project.setPhotos(service.getProject(project.getId()).getPhotos());
        service.editProject(project);
        return ResponseEntity.ok("[{\"message\":\"operation received and processed\"}]");
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/admin/deleteproject/{id}", method = RequestMethod.GET)
    public ResponseEntity deleteproject(@PathVariable long id) {
        service.deleteProject(id);
        return ResponseEntity.ok("[{\"message\":\"operation received and processed\"}]");
    }

}
