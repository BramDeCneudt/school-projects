package bdc.domain;

import org.springframework.web.client.RestTemplate;

public class Pojo {

    private String name;
    private String weather;
    private String reviews;
    private String info;


    public void setName(String name) {
            this.name = name;
    }

    public void setWeather(String weather) {
            this.weather = weather;
    }

    public void setReviews(String reviews) {
        if (reviews != null && reviews.equals("[]")) {
            reviews = null;
        }
            this.reviews = reviews;
    }

    public void setInfo(String info) {
            this.info = info;
    }

    public String getName() {
        return name;
    }

    public String getWeather() {
        return weather;
    }

    public String getReviews() {
        return reviews;
    }

    public String getInfo() {
        return info;
    }


}
