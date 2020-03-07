package BDC.service;

import db.CitiesInMemory;
import domain.City;

import java.util.List;

public class ServiceWeather {

    private CitiesInMemory cities;


    public ServiceWeather() {
        cities = new CitiesInMemory();
    }


    public void addCity(City city) {
        cities.addCity(city);
    }

    public void removeCity(City city) {
        cities.removeCity(city);
    }

    public List<City> getCities() {
        return cities.getCities();
    }


    public City getCity(String cityString) {
        return this.cities.getCity(cityString);
    }

    public double getTopWindSpeed() {
        return cities.gettopWindSpeed();
    }

    public List<City> getCitiesWithTopWindSpeed() {
        return cities.getCitiesWithTopWindSpeed();
    }

    public List<String> getCitiesNames() {
        return this.cities.getCitiesNames();
    }


}
