package bdc.controllers;

import bdc.domain.Information;
import bdc.service.InfoService;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class InfoController {

    private InfoService infoService;

    public InfoController() {
        infoService = new InfoService();
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "hello World";
    }


    @RequestMapping(value = "/city/{city}", method = RequestMethod.GET)
    public ResponseEntity<Information> getCity(@PathVariable String city) {

        Information info = infoService.getInformation(city);

        if (info == null) {
            return  ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(info);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<Information> getCities() {
        return this.infoService.getInformations();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addInformation(@RequestBody Information information) {
        infoService.addInformation(information);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void updateInformation(@RequestBody Information information) {
        infoService.updateInformation(information);
    }

    @RequestMapping(value = "/remove/{city}")
    public void removeInformation(@PathVariable String city) {
        infoService.removeInformation(city);
    }

}
