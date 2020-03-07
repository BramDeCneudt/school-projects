package bdc.db;

import bdc.domain.City;
import bdc.domain.Review;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Cities {

    public HashMap<String, City> cities;

    public Cities() {
        cities = new HashMap<>();
    }

    public City getCity(String city) {

        if (city == null || city.trim().isEmpty()) {
            throw new DBException();
        }

        return cities.get(city);
    }

    public void addCity(String cityString) {

        if (cityString == null || cityString.trim().isEmpty()) {
            throw new DBException();
        }

        City city = new City(cityString);
        cities.put(city.getCity(), city);

    }

    public void addReviewToCityity(String cityString, Review review) {
        if (cityString == null || cityString.trim().isEmpty() || !cities.containsKey(cityString) || review == null) {
            throw new DBException();
        }

        cities.get(cityString).addReview(review);
    }

    public List<City> getCities() {
        return new ArrayList<>(cities.values());
    }

    public void removeReviewFromCity(String cityString, Review review) {
        if (cityString == null || cityString.trim().isEmpty() || !cities.containsKey(cityString) || review == null) {
            throw new DBException();
        }

        cities.get(cityString).removeReview(review);

    }

}
