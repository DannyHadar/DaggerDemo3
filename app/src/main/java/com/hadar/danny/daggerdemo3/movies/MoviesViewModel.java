package com.hadar.danny.daggerdemo3.movies;

import com.hadar.danny.daggerdemo3.api.MoviesDataSource;
import com.hadar.danny.daggerdemo3.api.models.ApiResponse;
import com.hadar.danny.daggerdemo3.api.models.MoviesResult;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MoviesViewModel extends ViewModel {
    private MutableLiveData<ApiResponse<MoviesResult>> mApiResponse;

    private final MoviesDataSource mMoviesDataSource;
    private final CompositeDisposable mDisposables;

    MoviesViewModel(MoviesDataSource moviesDataSource, CompositeDisposable disposables) {
        mMoviesDataSource = moviesDataSource;
        mDisposables = disposables;
    }

    LiveData<ApiResponse<MoviesResult>> apiResponse() {
        if (mApiResponse == null) {
            mApiResponse = new MutableLiveData<>();
        }
        return mApiResponse;
    }

    void fetchMoviesClicked(String movieQuery) {
        mMoviesDataSource.getMovies(movieQuery)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getApiObserver());
    }

    private SingleObserver<MoviesResult> getApiObserver() {
        return new SingleObserver<MoviesResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                mDisposables.add(d);
                mApiResponse.setValue(ApiResponse.loading());
            }

            @Override
            public void onSuccess(MoviesResult moviesResult) {
                mApiResponse.setValue(ApiResponse.success(moviesResult));
            }

            @Override
            public void onError(Throwable e) {
                mApiResponse.setValue(ApiResponse.error(e));
            }
        };
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        mDisposables.clear();
    }
}
