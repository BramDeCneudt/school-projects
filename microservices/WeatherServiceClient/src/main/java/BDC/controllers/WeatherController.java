package BDC.controllers;

import BDC.service.ServiceWeather;
import domain.City;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Component
//@CrossOrigin
public class WeatherController {

    ServiceWeather serviceWeather;

    public WeatherController() {
        serviceWeather = new ServiceWeather();
    }


    @RequestMapping(value = "/test")
    public String test() {
        return "hello World";
    }

    @RequestMapping("city/{cityString}")
    ResponseEntity<City> GetCity(@PathVariable String cityString) {


        City city = serviceWeather.getCity(cityString);

        if (city == null) {
            return  ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }
        return  ResponseEntity.status(HttpStatus.ACCEPTED).body(city);

    }


    @RequestMapping("cities")
    List<City> getCities() {
        return serviceWeather.getCities();
    }

    @RequestMapping(value = "api/addcity", method = RequestMethod.POST)
    ResponseEntity<String> addCity(@RequestBody City city) {
        try {
            serviceWeather.addCity(city);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @RequestMapping("wind/topspeed")
    double getTopSpeed() {
        return serviceWeather.getTopWindSpeed();
    }

    @RequestMapping("wind/cities")
    List<City> getCitiesWithTopSpeed() {
        return serviceWeather.getCitiesWithTopWindSpeed();
    }

    @RequestMapping("names")
    List<String> getCitiesNames() {
        return this.serviceWeather.getCitiesNames();
    }

}
