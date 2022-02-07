package com.garcia.image_management.di

import com.garcia.image_management.common.Constants
import com.garcia.image_management.data.remote.client.PhotoAPI
import com.garcia.image_management.data.repository.PhotoRepositoryImpl
import com.garcia.image_management.domain.repository.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    //Binds (interface instances)

    @Binds
    @Singleton
    abstract fun bindPhotoRepository(
        coinRepositoryImpl: PhotoRepositoryImpl
    ): PhotoRepository

    companion object {
        // Providers (class instances)

        @Provides
        @Singleton
        fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        @Provides
        @Singleton
        fun providePaprikaApi(retrofit: Retrofit): PhotoAPI {
            return retrofit.create(PhotoAPI::class.java)
        }
    }
}