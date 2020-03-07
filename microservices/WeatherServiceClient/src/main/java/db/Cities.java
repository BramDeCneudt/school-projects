package db;

import domain.City;

import java.util.List;

public interface Cities {

    void addCity(City city);
    void removeCity(City city);
    City getCity(String name);
    void updateCity(City city);

    double gettopWindSpeed();
    List<City> getCitiesWithTopWindSpeed();

    List<City> getCities();
}
