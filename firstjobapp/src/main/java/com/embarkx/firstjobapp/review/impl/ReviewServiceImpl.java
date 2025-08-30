package com.embarkx.firstjobapp.review.impl;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyService;
import com.embarkx.firstjobapp.review.Review;
import com.embarkx.firstjobapp.review.ReviewRepository;
import com.embarkx.firstjobapp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {


    private ReviewRepository reviewRepository;
    private CompanyService companyservice;
    public ReviewServiceImpl(ReviewRepository reviewRepository,CompanyService companyservice)
    {
        this.reviewRepository=reviewRepository;
        this.companyservice=companyservice;
    }



    @Override
    public List<Review> getAllReviews(Long CompanyId) {
        List<Review> reviews=reviewRepository.findByCompanyId((CompanyId));
        return reviews;
    }

    @Override
    public boolean addReview(Long ComapnyId,Review review) {
        Company comapny=companyservice.getCompanyById(ComapnyId);
        if(comapny!=null)
        {
            review.setCompany(comapny);
            reviewRepository.save(review);
            return true;
        }
        else{
            return false;
        }
    }

}
