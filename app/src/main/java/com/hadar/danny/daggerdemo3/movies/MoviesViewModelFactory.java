package com.hadar.danny.daggerdemo3.movies;

import com.hadar.danny.daggerdemo3.api.MoviesDataSource;
import com.hadar.danny.daggerdemo3.movies.MoviesViewModel;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import io.reactivex.disposables.CompositeDisposable;

/**
 * This factory can only deal with a specific viewModel
 * and should not be used in real big projects
 * but since this is a tiny project with one viewModel it will do
 * and the current work around to make a generic viewModelFactory is a little messy
 */
public class MoviesViewModelFactory implements ViewModelProvider.Factory {

    private MoviesDataSource mMoviesDataSource;
    private CompositeDisposable mDisposable;

    @Inject
    MoviesViewModelFactory(MoviesDataSource moviesDataSource, CompositeDisposable disposable) {
        mMoviesDataSource = moviesDataSource;
        mDisposable = disposable;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MoviesViewModel.class)) {
            // noinspection unchecked
            return (T) new MoviesViewModel(mMoviesDataSource, mDisposable);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
