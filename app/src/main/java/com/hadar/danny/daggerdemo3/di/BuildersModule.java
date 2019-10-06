package com.hadar.danny.daggerdemo3.di;

import com.hadar.danny.daggerdemo3.movies.MoviesActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector(modules = MoviesModule.class)
    abstract MoviesActivity contributeMoviesActivity();
}
