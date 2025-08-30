package com.embarkx.firstjobapp.review;



import java.util.List;


public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long comapnyId,Review review);
    Review getReview(Long CompanyId,Long ReviewId);
    boolean deleteReview(Long CompanyId,Long ReviewId);
    boolean updateReview(Long CompanyId,Long ReviewId,Review review);

}
