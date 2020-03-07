package bdc.controllers;

import bdc.db.CitiesNames;
import bdc.domain.Pojo;
import bdc.domain.Rest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RibbonClient(name = "CompositeServiceClient")
@CrossOrigin
public class CompositeController {

    @Autowired
    private RestTemplate restTemplate;

    private String weatherURl = "http://WeatherClient/";
    private String reviewUrl = "http://ReviewClient/";
    private String infoUrl = "http://InfoClient/";

    private  CitiesNames names = new CitiesNames();



    @RequestMapping(value = "/test")
    public String test() {
        return getWeatherForCity("Leuven");
    }

    @RequestMapping(value="/city/{name}")
    public Pojo getCity(@PathVariable String name) {

        return getForCity(name);
    }

    @RequestMapping(value="/cities")
    public List<Pojo> getCities() {

        return getAllCities();
    }

    @RequestMapping("/names")
    public List<String> getNamesJson() {
        return this.getNames();
    }

    public Pojo getForCity(String name) {

        return this.setPojo(name);
    }

    private Pojo setPojo(String name) {
        Pojo pojo = new Pojo();
        pojo.setName(name);
        pojo.setWeather(this.getWeatherForCity(name));
        pojo.setReviews(this.getReviewsFromCity(name));
        pojo.setInfo(this.getInfoFromCity(name));

        return pojo;

    }

    public List<Pojo> getAllCities() {

        List<Pojo> pojos = new ArrayList<>();

        for (String name: this.names.getNames()) {
            Pojo pojo = this.setPojo(name);

            pojos.add(pojo);
        }
        return pojos;
    }

    public List<String> getNames() {
        return this.names.getNames();
    }

    public String getWeatherForCity(String city) {
        try {
            return restTemplate.getForObject(weatherURl + "city/" + city, String.class );
        } catch (Exception name) {
            return null;
        }

    }

    public String getReviewsFromCity(String city) {
        try {
            return restTemplate.getForObject(reviewUrl + "city/" + city, String.class);
        } catch (Exception name) {
            return null;
        }

    }


    public String getInfoFromCity(String city) {
        try {
            return restTemplate.getForObject(infoUrl + "city/" + city, String.class);
        } catch (Exception name) {
            return null;
        }
    }


}
