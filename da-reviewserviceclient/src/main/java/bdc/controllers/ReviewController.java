package bdc.controllers;

import bdc.domain.Review;
import bdc.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController() {
        reviewService = new ReviewService();
    }

    @RequestMapping(value = "/test")
    public String test() {
        return "hello World";
    }

    @RequestMapping(value = "/get/{id}")
    public ResponseEntity<Review> getReview(@PathVariable String id) {

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.getReview(id));
    }

    @RequestMapping(value = "/city/{name}")
    public ResponseEntity<List<Review>> getCity(@PathVariable String name) {

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.getReviewsFromCity(name));
    }

    @RequestMapping(value = "/get/all")
    public ResponseEntity<List<Review>> getReviews() {

        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.getReviews());
    }

    @RequestMapping(value = "/add")
    public void addReview(@RequestBody Review review) {
        reviewService.addReview(review);
    }

    @RequestMapping(value = "/remove")
    public void removeReview(String id) {
        reviewService.removeReview(id);
    }


}
