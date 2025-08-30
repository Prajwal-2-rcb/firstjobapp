package com.embarkx.firstjobapp.review;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("company/{companyId}")
public class ReviewController {
    private ReviewService reviewservice;
    public ReviewController(ReviewService reviewservice)
    {
        this.reviewservice=reviewservice;
    }
    @RequestMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewservice.getAllReviews(companyId), HttpStatus.OK);
    }

}
