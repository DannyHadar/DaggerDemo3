package com.hadar.danny.daggerdemo3.api;

import com.hadar.danny.daggerdemo3.api.models.MoviesResult;

import io.reactivex.Single;

public interface MoviesDataSource {
    Single<MoviesResult> getMovies(String movieQuery);
}
