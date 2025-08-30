package com.embarkx.firstjobapp.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("companies/{companyId}")
public class ReviewController {
    private ReviewService reviewservice;
    public ReviewController(ReviewService reviewservice)
    {
        this.reviewservice=reviewservice;
    }
    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewservice.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId,@RequestBody Review review) {
        boolean isReviewSaved=reviewservice.addReview(companyId,review);
        if(isReviewSaved)
        {
            return new ResponseEntity<>("Review created successfully",HttpStatus.CREATED);
        }
        else
        {
            return new ResponseEntity<>("Review not created",HttpStatus.BAD_REQUEST);
        }
    }

}
