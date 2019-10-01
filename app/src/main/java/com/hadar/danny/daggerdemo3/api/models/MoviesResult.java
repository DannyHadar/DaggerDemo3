package com.hadar.danny.daggerdemo3.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviesResult {
    @SerializedName("results")
    private List<Movie> mMovieList;

    public List<Movie> getMovieList() {
        return mMovieList;
    }
}
