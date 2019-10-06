package com.hadar.danny.daggerdemo3.di;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.hadar.danny.daggerdemo3.R;
import com.hadar.danny.daggerdemo3.api.MoviesApiService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class AppModule {

    @Singleton
    @Provides
    static Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    static RequestManager provideRequestManager(Context context) {
        return Glide.with(context);
    }

    @Provides
    static MoviesApiService provideApiService(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.movies_api_base_url))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(MoviesApiService.class);
    }

    @Provides
    @Named("api_key")
    static String provideApiKey(Context context) {
        return context.getString(R.string.movies_api_key);
    }
}
