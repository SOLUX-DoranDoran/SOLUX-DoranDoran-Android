package com.solux.dorandoran.app.di

import com.solux.dorandoran.data.service.ArgumentApiService
import com.solux.dorandoran.data.service.BookApiService
import com.solux.dorandoran.data.service.DiscussApiService
import com.solux.dorandoran.data.service.ExampleApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Singleton
    fun provideExampleService(
        @DoranDoranRetrofit retrofit: Retrofit
    ): ExampleApiService = retrofit.create(ExampleApiService::class.java)

    @Provides
    @Singleton
    fun provideDiscussService(
        @DoranDoranRetrofit retrofit: Retrofit
    ): DiscussApiService = retrofit.create(DiscussApiService::class.java)

    @Provides
    @Singleton
    fun provideArgumentService(
        @DoranDoranRetrofit retrofit: Retrofit
    ): ArgumentApiService = retrofit.create(ArgumentApiService::class.java)

    @Provides
    @Singleton
    fun provideBookService(
        @DoranDoranRetrofit retrofit: Retrofit
    ): BookApiService = retrofit.create(BookApiService::class.java)

}
