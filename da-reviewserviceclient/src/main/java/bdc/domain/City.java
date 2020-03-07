package bdc.domain;


import java.util.HashSet;

public class City {

    private String city;
    private HashSet<Review> reviews;

    public City(String city) {
        reviews = new HashSet<Review>();
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

    public void addReview(Review review) {

        if (review == null || reviews.contains(review)) {
            throw new DomainException();
        }

        reviews.add(review);
    }


    public void removeReview(Review review) {

        if (review == null || !reviews.contains(review)) {
            throw new DomainException();
        }

        reviews.remove(review);
    }


    public void updateReview(Review review) {

        if (review == null || !reviews.contains(review)) {
            throw new DomainException();
        }

        reviews.add(review);
    }

}
