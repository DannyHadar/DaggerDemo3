package com.hadar.danny.daggerdemo3.di;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
}
