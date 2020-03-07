package bdc.service;

import bdc.db.Cities;
import bdc.db.Reviews;
import bdc.domain.City;
import bdc.domain.Review;

import java.util.List;

public class ReviewService {

    private Reviews reviews;

    public ReviewService() {
        reviews = new Reviews();
    }

    public void addReview(Review review) {
        reviews.addReview(review);
    }

    public void removeReview(String id) {
        reviews.removeReview(id);
    }

    public List<Review> getReviewsFromCity(String cityString) {
        return reviews.getReviewsFromCity(cityString);
    }


    public Review getReview(String id) {
        return getReview(id);
    }

    public List<Review> getReviews() {return  this.reviews.getReviews();}

}
