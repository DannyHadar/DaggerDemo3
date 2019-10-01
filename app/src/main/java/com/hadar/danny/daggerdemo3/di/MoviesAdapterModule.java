package com.hadar.danny.daggerdemo3.di;

import android.content.Context;
import android.view.LayoutInflater;

import com.hadar.danny.daggerdemo3.R;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class MoviesAdapterModule {
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
    static int provideResource() {
        return R.layout.movie_item_list_row;
    }
}
