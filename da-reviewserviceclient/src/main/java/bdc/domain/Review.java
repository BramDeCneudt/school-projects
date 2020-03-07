package bdc.domain;


import java.util.UUID;

public class Review {

    private String ID;
    private String name;
    private String review;
    private String city;

    public Review(String name, String review, String city) {
        ID = UUID.randomUUID().toString();
        setName(name);
        setReview(review);
        setCity(city);
    }

    public String getName() {
        return name;
    }


    public String getID() {
        return this.ID;
    }

    public void setName(String name) {

        if (name == null || name.trim().isEmpty()) {
            throw new DomainException();
        }

        this.name = name;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {

        if (review == null || review.trim().isEmpty()) {
            throw new DomainException();
        }

        this.review = review;
    }

    @Override
    public boolean equals(Object obj) {
        if ( !(obj instanceof Review) ) {
            return false;
        }
        Review review = (Review) obj;
        return this.ID.equals(review.getID());
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return this.city;
    }
}
