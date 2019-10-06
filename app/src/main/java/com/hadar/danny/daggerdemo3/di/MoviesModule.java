package com.hadar.danny.daggerdemo3.di;

import android.content.Context;
import android.view.LayoutInflater;

import com.hadar.danny.daggerdemo3.R;
import com.hadar.danny.daggerdemo3.api.MoviesDataSource;
import com.hadar.danny.daggerdemo3.api.MoviesRepository;
import com.hadar.danny.daggerdemo3.movies.MoviesViewModel;

import javax.inject.Named;

import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class MoviesModule {

    @Binds
    abstract MoviesDataSource bindDataSource(MoviesRepository moviesRepository);

    @Provides
    static LayoutInflater provideInflater(Context context) {
        return LayoutInflater.from(context);
    }

    @Provides
    @Named("image_url_format")
    static String provideUrlFormat(Context context) {
        return context.getString(R.string.images_url_format);
    }

    @Provides
    @Named("movies_layout_resource")
    static int provideResource() {
        return R.layout.movie_item_list_row;
    }

    @Binds
    @IntoMap
    @ViewModelsKey(MoviesViewModel.class)
    abstract ViewModel bindMoviesViewModel(MoviesViewModel viewModel);
}
