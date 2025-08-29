package com.embarkx.firstjobapp.review;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.embarkx.firstjobapp.review.ReviewService;

@RestController
@RequestMapping("company/{companyId}")
public class ReviewController {
    private ReviewService reviewservice;
    public ReviewController(ReviewService reviewservice)
    {
        this.reviewservice=reviewservice;
    }

}
