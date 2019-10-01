package com.hadar.danny.daggerdemo3.api;

import com.hadar.danny.daggerdemo3.api.models.MoviesResult;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MoviesApiService {
    @GET("search/movie")
    Single<MoviesResult> searchMovies(@Query("api_key") String apiKey, @Query("query") String query);
}
