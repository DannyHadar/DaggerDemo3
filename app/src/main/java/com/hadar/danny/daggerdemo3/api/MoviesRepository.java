package com.hadar.danny.daggerdemo3.api;

import com.hadar.danny.daggerdemo3.api.models.MoviesResult;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Single;

public class MoviesRepository implements MoviesDataSource {
    private final MoviesApiService mApi;
    private final String mApiKey;

    @Inject
    MoviesRepository(@Named("api_key") String apiKey, MoviesApiService moviesApi) {
        mApi = moviesApi;
        mApiKey = apiKey;
    }

    @Override
    public Single<MoviesResult> getMovies(String movieQuery) {
        return mApi.searchMovies(mApiKey, movieQuery);
    }
}
