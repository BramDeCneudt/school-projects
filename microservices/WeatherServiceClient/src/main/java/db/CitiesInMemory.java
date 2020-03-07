package db;

import BDC.pojo.Weather;
import BDC.pojo.Wind;
import domain.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CitiesInMemory implements Cities {

    private HashMap<String, City> cities;
    private double topWindSpeed;
    private List<City> citiesTopWindSpeed;

    public CitiesInMemory() {
        cities = new HashMap<>();
        topWindSpeed = -999;
        citiesTopWindSpeed = new ArrayList<>();

        Wind wind = new Wind();
        wind.setSpeed(5.7);
        wind.setDeg(210);

        Weather weather = new Weather();
        weather.setId(302);
        weather.setMain("Drizzle");
        weather.setDescription("heavy intensity drizzle");
        weather.setIcon("09n");

        City city = new City("Leuven", weather, wind);
        this.addCity(city);

        wind = new Wind();
        weather = new Weather();

        wind.setSpeed(5.7);
        wind.setDeg(210);

        weather.setId(300);
        weather.setMain("Drizzle");
        weather.setDescription("heavy intensity drizzle");
        weather.setIcon("09n");

        city = new City("Haasrode", weather, wind);
        this.addCity(city);

        wind = new Wind();
        weather = new Weather();

        wind.setSpeed(5.7);
        wind.setDeg(210);

        weather.setId(300);
        weather.setMain("Drizzle");
        weather.setDescription("heavy intensity drizzle");
        weather.setIcon("09n");

        city = new City("Blanden", weather, wind);
        this.addCity(city);

    }


    @Override
    public void addCity(City city) {
        if (city == null) {
            throw new DatabaseException("fout in add city: " + city);
        }
        cities.put(city.getName(), city);
        this.checkTopWindSpeed(city);

    }

    @Override
    public void removeCity(City city) {
        if (city == null || !cities.containsKey(city.getName())) {
            throw new DatabaseException("fout in removeCity: " + city);
        }
        cities.remove(city);
    }

    @Override
    public City getCity(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new DatabaseException("fout in getCity: " + name);
        }
        return cities.get(name);
    }

    @Override
    public void updateCity(City city) {
        if (city == null || !cities.containsKey(city.getName())) {
            throw new DatabaseException("fout in updateCity: " + city);
        }
        cities.put(city.getName(), city);
    }

    @Override
    public double gettopWindSpeed() {
        return this.topWindSpeed;
    }

    @Override
    public List<City> getCitiesWithTopWindSpeed() {
        return this.citiesTopWindSpeed;
    }

    private void checkTopWindSpeed(City city) {
        if (this.topWindSpeed == -999) {
            this.topWindSpeed = city.getSpeedWind();
        }
        if (this.topWindSpeed < city.getSpeedWind()) {
            this.topWindSpeed = city.getSpeedWind();
            this.newWindSpeed(city);
        }
        if (this.topWindSpeed == city.getSpeedWind()) {
            this.citiesTopWindSpeed.add(city);
        }
    }

    private void newWindSpeed(City city) {
        this.citiesTopWindSpeed = new ArrayList<>();
        this.citiesTopWindSpeed.add(city);
    }

    public List<String> getCitiesNames() {
        return new ArrayList<>(this.cities.keySet());
    }

    @Override
    public List<City> getCities() {
        return new ArrayList<>(this.cities.values());
    }

    @Override
    public String toString() {
        String toString = "";
        for (City city: cities.values() ) {
            toString += city.getName();
        }
        return toString;
    }
}
