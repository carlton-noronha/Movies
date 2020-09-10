package com.byethost12.carltonnoronha.movies.customclass;

public class Review {
    private String review;
    private String reviewerName;

    public Review(String review, String reviewerName) {
        this.review = review;
        this.reviewerName = reviewerName;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }
}
