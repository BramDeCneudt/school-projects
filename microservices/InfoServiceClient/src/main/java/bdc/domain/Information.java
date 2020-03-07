package bdc.domain;


public class Information {

    private String city;
    private String description;

    public Information(String city, String description) {
        setCity(city);
        setDescription(description);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {

        if (city == null || city.trim().isEmpty()) {
            throw new DomainException();
        }

        this.city = city;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {

        if (description == null || description.trim().isEmpty()) {
            throw new DomainException();
        }

        this.description = description;
    }



}
