package domain;

import BDC.pojo.Weather;
import BDC.pojo.Wind;

public class City {
    private String name;
    private Weather weather;
    private Wind wind;


    public City() {

    }

    public City(String name, Weather weather, Wind wind) {
        setName(name);
        setWeather(weather);
        setWind(wind);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new DomainException();
        }

        this.name = name;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        if (weather == null) {
            throw new DomainException();
        }
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {

        if (wind == null) {
            throw new DomainException();
        }

        this.wind = wind;
    }

    public double getSpeedWind() {
        return this.wind.getSpeed();
    }

    public int getDegreeWind() {
        return this.wind.getDeg();
    }

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof City)) {
            return false;
        }
        City city = (City) obj;

        return city.getName().equals(this.getName());
    }
}