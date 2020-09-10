package com.byethost12.carltonnoronha.movies.customclass;

public class Movies {

    private int movieID;
    private String movieName;
    private String movieImageURL;
    private String overview;

    public Movies(int movieID, String movieName, String movieImageURL, String overview) {
        this.movieID = movieID;
        this.movieName = movieName;
        this.movieImageURL = movieImageURL;
        this.overview = overview;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getMovieImageURL() {
        return movieImageURL;
    }

    public void setMovieImageURL(String movieImageURL) {
        this.movieImageURL = movieImageURL;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}
