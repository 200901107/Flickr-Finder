package com.flickrfinder.android.di

import com.flickr4java.flickr.Flickr
import com.flickr4java.flickr.REST
import com.flickrfinder.android.AppImageLoader
import com.flickrfinder.android.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideFlickr(): Flickr {
        return Flickr("1508443e49213ff84d566777dc211f2a", "1508443e49213ff84d566777dc211f2a", REST())
    }

    @Singleton
    @Provides
    fun provideImageLoader(): ImageLoader = AppImageLoader()

}
