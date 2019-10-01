package com.hadar.danny.daggerdemo3.di;

import android.content.Context;
import android.view.LayoutInflater;

import com.hadar.danny.daggerdemo3.R;
import com.hadar.danny.daggerdemo3.api.MoviesApiService;
import com.hadar.danny.daggerdemo3.api.MoviesDataSource;
import com.hadar.danny.daggerdemo3.api.MoviesRepository;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class MoviesViewModelModule {

    @Provides
    static CompositeDisposable provideDisposables() {
        return new CompositeDisposable();
    }

    @Binds
    abstract MoviesDataSource bindDataSource(MoviesRepository moviesRepository);
}
