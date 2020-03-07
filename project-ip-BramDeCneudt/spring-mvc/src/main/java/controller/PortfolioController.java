/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.model.Photo;
import domain.model.Project;
import domain.model.Type;
import domain.upload.UploaderException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.Service;

/**
 *
 * @author Bram
 */
@Controller
@MultipartConfig
@RequestMapping(value = "/portfolio")
public class PortfolioController {

    private final Service service;

    public PortfolioController(@Autowired Service service) {
        this.service = service;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getFirstProject(Model model) {
        model.addAttribute("projects", service.getProjects());
        if (service.getProjects().size() > 0) {
            model.addAttribute("project", service.getProjects().get(0));
        }
        model.addAttribute("prefix", service.getPrefix());
        return new ModelAndView("home", "model", model);
    }

    @RequestMapping(method = RequestMethod.GET, value = "all/getproject/{id}")
    public ModelAndView getProjectFromId(Model model, @PathVariable long id) {
        model.addAttribute("projects", service.getProjects());
        model.addAttribute("project", service.getProject(id));
        model.addAttribute("prefix", service.getPrefix());
        return new ModelAndView("home", "model", model);
    }

    @RequestMapping(value = "admin/editportfolio", method = RequestMethod.GET)
    public ModelAndView EditProjects(Model model) {
        model.addAttribute("projects", service.getProjects());
        model.addAttribute("prefix", service.getPrefix());
        return new ModelAndView("editportfolio", "model", model);
    }

    @RequestMapping(value = "admin/addphoto", method = RequestMethod.GET)
    public ModelAndView getAddPhotoForm(Model model) {
        Photo photo = new Photo();
        photo.setType(service.getType("foto"));
        model.addAttribute("photo", photo);
        model.addAttribute("projects", service.getProjects());
        model.addAttribute("types", service.getTypes());
        return new ModelAndView("addphoto", "model", model);
    }

    @RequestMapping(value = "admin/megaupload", method = RequestMethod.GET)
    public ModelAndView getMegaUploadForm(Model model) {
        model.addAttribute("projects", service.getProjects());
        model.addAttribute("types", service.getTypes());
        return new ModelAndView("megaupload", "model", model);
    }

    @RequestMapping(value = "admin/addphoto/json", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void addPhotoJSON(Model model, HttpServletRequest request,
            @RequestParam("type") Type type,
            @RequestParam(value = "oldproject", required = false) Project oldProject,
            @RequestParam("project") Project project,
            @RequestParam("image") MultipartFile image,
            @Valid @ModelAttribute("photo") Photo photo, BindingResult result) {

        if (result.hasErrors()) {
            throw new ControllerException();
        }

        photo.setType(type);
        String beginPath = request.getServletContext().getRealPath("/images/");
        if (photo.getId() > 0) {
            try {

                photo.setImagePath(this.service.getPhoto(photo.getId()).getImagePath());
                service.editUpload(photo, beginPath, image);
                if (project != oldProject) {
                    service.removePhotoFormProject(oldProject, photo);
                    service.addPhotoToProject(project, photo);

                    service.editProject(oldProject);
                    service.editProject(project);
                }
                service.editPhoto(photo);

            } catch (UploaderException e) {
                result.rejectValue("imagePath", "error.photo.imagepath.File");
            }
        } else {
            try {
                service.upload(photo, beginPath, image);
                service.addPhoto(photo);
                service.addPhotoToProject(project, photo);

                service.editProject(project);
            } catch (UploaderException e) {
                result.rejectValue("imagePath", "error.photo.imagepath.File");
            }
        }

        if (result.hasErrors()) {
            throw new ControllerException();
        }

    }

    @RequestMapping(value = "admin/addphoto", method = RequestMethod.POST)
    public ModelAndView addPhoto(Model model, HttpServletRequest request,
            @RequestParam("type") Type type,
            @RequestParam(value = "oldproject", required = false) Project oldProject,
            @RequestParam("project") Project project,
            @RequestParam("image") MultipartFile image,
            @Valid @ModelAttribute("photo") Photo photo, BindingResult result) {

        if (result.hasErrors()) {
            return handleErrorPhoto(model, result);
        }

        photo.setType(type);
        String beginPath = request.getServletContext().getRealPath("/images/");
        if (photo.getId() > 0) {
            try {

                photo.setImagePath(this.service.getPhoto(photo.getId()).getImagePath());
                service.editUpload(photo, beginPath, image);
                if (project != oldProject) {
                    service.removePhotoFormProject(oldProject, photo);
                    service.addPhotoToProject(project, photo);

                    service.editProject(oldProject);
                    service.editProject(project);
                }
                service.editPhoto(photo);

            } catch (UploaderException e) {
                result.rejectValue("imagePath", "error.photo.imagepath.File");
            }
        } else {
            try {
                service.upload(photo, beginPath, image);
                service.addPhoto(photo);
                service.addPhotoToProject(project, photo);

                service.editProject(project);
            } catch (UploaderException e) {
                result.rejectValue("imagePath", "error.photo.imagepath.File");
            }
        }

        if (result.hasErrors()) {
            return handleErrorPhoto(model, result);
        }

        return new ModelAndView("redirect:/portfolio/admin/editportfolio.htm");
    }

    private ModelAndView handleErrorPhoto(Model model, BindingResult result) {
        model.addAttribute("photo", new Photo());
        model.addAttribute("projects", service.getProjects());
        model.addAttribute("types", service.getTypes());
        model.addAllAttributes(result.getModel());
        return new ModelAndView("addphoto", "model", model);
    }

    @RequestMapping(value = "admin/deletephoto/{id}/{projectid}", method = RequestMethod.GET)
    public String getRemoveForm(@PathVariable long id, @PathVariable long projectid) {
        Project project = service.getProject(projectid);
        Photo photo = service.getPhoto(id);

        project.removePhoto(photo);
        service.editProject(project);
        service.deletePhoto(id);
        return "redirect:/portfolio/admin/editportfolio.htm";
    }

    @RequestMapping(value = "getphoto/{id}", method = RequestMethod.GET)
    public ModelAndView getEditFormPhoto(@PathVariable long id, Model model) {
        model.addAttribute("photo", service.getPhoto(id));
        model.addAttribute("projects", service.getProjects());
        model.addAttribute("types", service.getTypes());
        return new ModelAndView("addphoto", "model", model);
    }

    @RequestMapping(value = "admin/addproject", method = RequestMethod.GET)
    public ModelAndView getAddProjectForm() {
        return new ModelAndView("addproject", "project", new Project());
    }

    @RequestMapping(value = "admin/addproject", method = RequestMethod.POST)
    public String addProject(@ModelAttribute("project") @Valid Project project, BindingResult result) {
        if (result.hasErrors()) {
            return "addproject";
        }
        if (project.getId() > 0) {
            project.setPhotos(service.getProject(project.getId()).getPhotos());
            service.editProject(project);
        } else {
            service.addProject(project);
        }

        return "redirect:/portfolio/admin/editportfolio.htm";
    }

    @RequestMapping(value = "admin/addtype", method = RequestMethod.GET)
    public ModelAndView getAddTypeForm(Model model) {
        model.addAttribute("type", new Type());
        model.addAttribute("types", service.getTypes());
        return new ModelAndView("addtype", "model", model);
    }

    @RequestMapping(value = "admin/addtype", method = RequestMethod.POST)
    public ModelAndView addType(Model model, @Valid @ModelAttribute("type") Type type, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("type", new Type());
            model.addAttribute("types", service.getTypes());
            model.addAllAttributes(result.getModel());
            return new ModelAndView("addtype", "model", model);
        }

        try {
            service.addType(type);
        } catch (Exception e) {
            result.rejectValue("name", "error.type.name.Exists");
        }

        if (result.hasErrors()) {
            model.addAttribute("type", new Type());
            model.addAttribute("types", service.getTypes());
            model.addAllAttributes(result.getModel());
            return new ModelAndView("addtype", "model", model);
        }

        return new ModelAndView("redirect:/portfolio/admin/editportfolio.htm");
    }

    @RequestMapping(value = "admin/getcolors/{id}", method = RequestMethod.GET)
    public ModelAndView getColors(@PathVariable long id, Model model, HttpServletRequest request) {
        if (this.service.getPrefix().trim().isEmpty()) {
            model.addAttribute("colors", service.getPhoto(id).getColors(""));
        } else {
            String scheme = request.getScheme();
            String serverName = request.getServerName();
            int serverPort = request.getServerPort();
            String contextPath = request.getContextPath();

            String basicUrl = scheme + "://" + serverName + ":" + serverPort + "" + contextPath + "" + this.service.getPrefix();
            model.addAttribute("colors", this.service.getPhoto(id).getColors(basicUrl));
        }

        return new ModelAndView("getcolors", "model", model);
    }

    @RequestMapping(value = "admin/deletetype/{id}", method = RequestMethod.GET)
    public ModelAndView deleteType(@PathVariable String id) {
            service.removeType(id);
        return new ModelAndView("redirect:/portfolio/admin/addtype.htm");
    }

    @RequestMapping(value = "admin/deleteproject/{id}", method = RequestMethod.GET)
    public ModelAndView deleteProject(@PathVariable long id) {
        service.deleteProject(id);
        return new ModelAndView("redirect:/portfolio/admin/editportfolio.htm");
    }

    @RequestMapping(value = "admin/editproject/{id}", method = RequestMethod.GET)
    public ModelAndView editProject(@PathVariable long id, Model model) {
        model.addAttribute("project", service.getProject(id));
        return new ModelAndView("addproject", "model", model);
    }

}
