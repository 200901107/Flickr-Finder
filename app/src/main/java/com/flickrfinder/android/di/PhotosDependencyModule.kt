package com.flickrfinder.android.di

import com.flickrfinder.android.search.data.PhotosSearchRepository
import com.flickrfinder.android.search.data.PhotosSearchRepositoryImpl
import com.flickrfinder.android.search.data.RemoteDataSource
import com.flickrfinder.android.search.data.RemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PhotosDependencyModule {

    @Binds
    abstract fun provideMainRepository(photosSearchRepositoryImpl: PhotosSearchRepositoryImpl): PhotosSearchRepository

    @Binds
    abstract fun provideRemoteDataSource(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource

}